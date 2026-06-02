package eleTakeOut.server.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import eleTakeOut.common.exception.BaseException;
import eleTakeOut.common.properties.JwtProperties;
import eleTakeOut.common.result.PageResult;
import eleTakeOut.common.utils.JwtUtils;
import eleTakeOut.pojo.dto.LoginDTO;
import eleTakeOut.pojo.dto.ShopAddDTO;
import eleTakeOut.pojo.dto.ShopPageQueryDTO;
import eleTakeOut.pojo.dto.ShopUpdateDTO;
import eleTakeOut.pojo.dto.ShopUpdateSelfDTO;
import eleTakeOut.pojo.entity.Shop;
import eleTakeOut.pojo.vo.ShopVO;
import eleTakeOut.pojo.vo.CategoryVO;
import eleTakeOut.pojo.vo.LoginVO;
import eleTakeOut.pojo.vo.ShopSelfVO;
import eleTakeOut.server.mapper.CategoryMapper;
import eleTakeOut.server.mapper.DishMapper;
import eleTakeOut.server.mapper.ShopMapper;
import eleTakeOut.server.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    private final ShopMapper shopMapper;
    private final CategoryMapper categoryMapper;
    private final DishMapper dishMapper;
    private final JwtProperties jwtProperties;

    /**
     * 店铺分页查询
     * @param shopPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(ShopPageQueryDTO shopPageQueryDTO) {
        PageHelper.startPage(shopPageQueryDTO.getPage(),shopPageQueryDTO.getPageSize());
        Page<Shop> page = shopMapper.pageQuery(shopPageQueryDTO);

        Long total = page.getTotal();;
        List<Shop> records = page.getResult();

        return new PageResult(total,records);

    }

    /**
     * 新增店铺
     * @param shopAddDTO
     */
    @Override
    public void add(ShopAddDTO shopAddDTO) {
        Shop shop = new Shop();
        BeanUtils.copyProperties(shopAddDTO,shop);
        // 默认为未营业
        shop.setStatus(0);
        shopMapper.insert(shop);
    }

    /**
     * 查询店铺
     * @param id
     * @return
     */
    @Override
    public ShopSelfVO getByIdSelf(Long id) {
        Shop shop = shopMapper.selectById(id);
        ShopSelfVO shopSelfVO = new ShopSelfVO();
        BeanUtils.copyProperties(shop,shopSelfVO);
        return shopSelfVO;
    }
    /**
     * 查询店铺
     * @param id
     * @return
     */
    @Override
    public ShopVO getById(Long id) {
        Shop shop = shopMapper.selectById(id);
        ShopVO shopVO = new ShopVO();
        BeanUtils.copyProperties(shop,shopVO);
        return shopVO;
    }

    /**
     * 修改店铺
     * @param id
     */
    @Override
    public void update(Long id, ShopUpdateDTO shopUpdateDTO) {
        Shop shop = shopMapper.selectById(id);
        BeanUtils.copyProperties(shopUpdateDTO,shop);
        shop.setId(id);
        shopMapper.updateById(shop);
    }

    /**
     * 删除店铺
     * @param id
     */
    @Transactional
    @Override
    public void deleteById(Long id) {
        Shop shop = shopMapper.selectById(id);
        if(shop.getStatus() == 1){
            throw new BaseException("不可删除营业状态中的店铺，若要删除请先将状态改为非营业");
        }
        //删除店铺后的级联删除相关分类和菜品，使用事务管理
        shopMapper.deleteById(shop);
        categoryMapper.deleteByShopId(id);
        dishMapper.deleteByShopId(id);
    }

    /**
     * 获取店铺列表
     * @param shopPageQueryDTO
     * @return
     */
    @Override
    public List<ShopVO> getShopList(ShopPageQueryDTO shopPageQueryDTO) {
        String keywords = shopPageQueryDTO.getKeyword();
        return shopMapper.getShopList(keywords);
    }

    /**
     * 获取店铺分类
     * @param id
     * @return
     */
    @Override
    public List<CategoryVO> getCategories(Long id) {
        return categoryMapper.getCategoriesByShopId(id);
    }

    /**
     * 店铺登录
     * @param loginDTO
     * @return
     */
    @Override
    public LoginVO login(LoginDTO loginDTO) {
        String shopName = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        // 查询
        Shop shop = shopMapper.getByShopName(shopName);
        if(shop == null){
            throw new BaseException("商家不存在");
        }
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        if(!password.equals(shop.getPassword())){
            throw new BaseException("商家名或密码错误");
        }
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",shop.getId());
        claims.put("shop_name",shopName);
        String token = JwtUtils.createJwt(jwtProperties.getSecretKey(), jwtProperties.getTtl(), claims);

        LoginVO loginVO = new LoginVO();
        loginVO.setToken(token);
        loginVO.setUsername(shopName);
        loginVO.setId(shop.getId());
        return loginVO;
    }

    /**
     * 店铺修改自己店铺信息
     * @param id
     * @param shopUpdateSelfDTO
     */
    @Override
    public void updateBySelf(Long id, ShopUpdateSelfDTO shopUpdateSelfDTO) {
        Shop shop = shopMapper.selectById(id);
        BeanUtils.copyProperties(shopUpdateSelfDTO,shop);
        shop.setId(id);
        shopMapper.updateById(shop);
    }
}
