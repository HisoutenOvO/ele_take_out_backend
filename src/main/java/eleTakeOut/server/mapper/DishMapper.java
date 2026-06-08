package eleTakeOut.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import eleTakeOut.pojo.dto.DishPageQueryDTO;
import eleTakeOut.pojo.entity.Dish;
import eleTakeOut.pojo.vo.DishDetailVO;
import eleTakeOut.pojo.vo.DishListVO;
import eleTakeOut.pojo.vo.DishOrderVO;
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
    List<DishListVO> getDishListByCategoryId(Long categoryId);

    /**
     * 根据店铺id删除菜品
     * @param shopIds
     */
    void deleteByShopIds(List<Long> shopIds);

    /**
     * 菜品条件分页查询
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishDetailVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 根据订单id查询菜品详情
     * @param orderId
     * @return
     */
    List<DishOrderVO> getDishListByOrderId(Long orderId);
}
