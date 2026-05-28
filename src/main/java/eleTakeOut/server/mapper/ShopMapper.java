package eleTakeOut.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import eleTakeOut.pojo.dto.ShopPageQueryDTO;
import eleTakeOut.pojo.entity.Shop;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopMapper extends BaseMapper<Shop> {
    /**
     * 分页查询
     * @param shopPageQueryDTO
     * @return
     */
    Page<Shop> pageQuery(ShopPageQueryDTO shopPageQueryDTO);
}
