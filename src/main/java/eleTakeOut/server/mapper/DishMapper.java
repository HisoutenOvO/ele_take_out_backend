package eleTakeOut.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import eleTakeOut.pojo.entity.Dish;
import eleTakeOut.pojo.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {

    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    @Select("select * from dish where category_id = #{categoryId}")
    List<DishVO> getDishListByCategoryId(Long categoryId);

    /**
     * 根据店铺id删除菜品
     * @param shopId
     */
    @Delete("delete from dish where shop_id = #{shopId}")
    void deleteByShopId(Long shopId);
}
