package eleTakeOut.server.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import eleTakeOut.common.result.PageResult;
import eleTakeOut.pojo.dto.ShopDTO;
import eleTakeOut.pojo.dto.ShopPageQueryDTO;
import eleTakeOut.pojo.entity.Shop;
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
        shopMapper.insert(shop);
    }
}
