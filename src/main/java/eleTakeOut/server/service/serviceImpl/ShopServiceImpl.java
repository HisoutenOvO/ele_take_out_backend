package eleTakeOut.server.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import eleTakeOut.common.exception.BaseException;
import eleTakeOut.common.properties.JwtProperties;
import eleTakeOut.common.result.PageResult;
import eleTakeOut.common.utils.JwtUtils;
import eleTakeOut.pojo.dto.*;
import eleTakeOut.pojo.entity.Shop;
import eleTakeOut.pojo.vo.*;
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
        if(shopAddDTO.getPassword()!=null){
            shopAddDTO.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes(StandardCharsets.UTF_8)));
        }
        shop.setPassword(DigestUtils.md5DigestAsHex(shopAddDTO.getPassword().getBytes(StandardCharsets.UTF_8)));
        //设置查重
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
     * 商家获取店铺信息
     * @param id
     * @return
     */
    @Override
    public ShopAdminVO getByIdWithAdmin(Long id) {
        Shop shop = shopMapper.selectById(id);
        ShopAdminVO shopAdminVO = new ShopAdminVO();
        BeanUtils.copyProperties(shop,shopAdminVO);
        return shopAdminVO;
    }

    /**
     * 商家修改密码
     * @param shopId
     * @param dto
     */
    @Override
    public void changePassword(Long shopId, ChangePasswordDTO dto) {
        Shop shop = shopMapper.selectById(shopId);
        String oldPasswordMd5 = DigestUtils.md5DigestAsHex(dto.getOldPassword().getBytes(StandardCharsets.UTF_8));
        if (!oldPasswordMd5.equals(shop.getPassword())) {
            throw new BaseException("旧密码错误");
        }
        shop.setPassword(DigestUtils.md5DigestAsHex(dto.getPassword().getBytes(StandardCharsets.UTF_8)));
        shopMapper.updateById(shop);
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
     * 管理员修改店铺
     * @param id
     */
    @Override
    public void update(Long id, ShopUpdateDTO shopUpdateDTO) {
        Shop shop = new Shop();
        BeanUtils.copyProperties(shopUpdateDTO,shop);
        shop.setId(id);
        shopMapper.updateById(shop);
    }

    /**
     * 删除店铺
     * @param ids
     */
    @Transactional
    @Override
    public void deleteByIds(List<Long> ids) {
        List<Shop> shopList = shopMapper.selectByIds(ids);
        for (Shop shop : shopList) {
            if(shop.getStatus() == 1){
                throw new BaseException("不可删除营业状态中的店铺，若要删除请先将状态改为非营业");
            }
        }

        //删除店铺后的级联删除相关分类和菜品，使用事务管理
        shopMapper.deleteBatchIds(ids);
        categoryMapper.deleteByShopIds(ids);
        dishMapper.deleteByShopIds(ids);
    }

    /**
     * 获取店铺列表
     * @param shopListDTO
     * @return
     */
    @Override
    public List<ShopVO> getShopList(ShopListDTO shopListDTO) {
        String keywords = shopListDTO.getKeyword();
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
        Shop shop = new Shop();
        BeanUtils.copyProperties(shopUpdateSelfDTO,shop);
        shop.setId(id);
        shopMapper.updateById(shop);
    }
}
