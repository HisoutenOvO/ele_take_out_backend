package eleTakeOut.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import eleTakeOut.pojo.entity.Cart;
import eleTakeOut.pojo.vo.CartVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {
    /**
     * 根据店铺id查询购物车
     * @param shopId
     * @return
     */
    @Select("select * from cart where shop_id = #{shopId}")
    List<CartVO> selectCartByShopId(Long shopId);

    /**
     * 根据菜品id和用户id查询购物车
     * @param dishId
     * @param userId
     * @return
     */
    @Select("select * from cart where dish_id = #{dishId} and user_id = #{userId}")
    Cart getByDishIdAndUserId(Long dishId, Long userId);
}
