package eleTakeOut.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import eleTakeOut.pojo.dto.ShopPageQueryDTO;
import eleTakeOut.pojo.entity.Shop;
import eleTakeOut.pojo.entity.ShopVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShopMapper extends BaseMapper<Shop> {
    /**
     * 分页查询
     * @param shopPageQueryDTO
     * @return
     */
    Page<Shop> pageQuery(ShopPageQueryDTO shopPageQueryDTO);

    /**
     * 获取店铺
     * @param keywords
     * @return
     */
    List<ShopVO> getShopList(String keywords);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("select * from shop where id = #{id}")
    Shop getById(Long id);

    /**
     * 根据名称查询
     * @param shopName
     * @return
     */
    @Select("select * from shop where shop_name = #{shopName}")
    Shop getByShopName(String shopName);
}
