package eleTakeOut.server.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import eleTakeOut.common.result.PageResult;
import eleTakeOut.pojo.dto.ShopDTO;
import eleTakeOut.pojo.dto.ShopPageQueryDTO;
import eleTakeOut.pojo.entity.Shop;
import eleTakeOut.pojo.entity.ShopVO;
import eleTakeOut.server.mapper.ShopMapper;
import eleTakeOut.server.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    private final ShopMapper shopMapper;

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
     * @param shopDTO
     */
    @Override
    public void add(ShopDTO shopDTO) {
        Shop shop = new Shop();
        BeanUtils.copyProperties(shopDTO,shop);
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
    public ShopVO getById(Long id) {
        Shop shop = shopMapper.selectById(id);
        ShopVO shopVO = new ShopVO();
        BeanUtils.copyProperties(shop,shopVO);
        // 不返回创建时间，防止被修改
        shopVO.setCreateTime(null);
        return shopVO;
    }

    /**
     * 修改店铺
     * @param id
     */
    @Override
    public void update(Long id,ShopDTO shopDTO) {
        Shop shop = shopMapper.selectById(id);
        BeanUtils.copyProperties(shopDTO,shop);
        shop.setId(id);
        shopMapper.updateById(shop);
    }
}
