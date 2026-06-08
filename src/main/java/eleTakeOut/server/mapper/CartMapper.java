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
     * 根据菜品id和用户id查询购物车
     * @param dishId
     * @param userId
     * @return
     */
    List<Cart> getByDishIdAndUserIdAndShopId(Long dishId, Long userId,Long shopId);

    /**
     * 清空购物车
     * @param currentUserId
     * @param shopId
     */
    @Select("delete from cart where user_id = #{currentUserId} and shop_id = #{shopId}")
    void clearByUserIdAndShopId(Long currentUserId, Long shopId);

    /**
     * 根据店铺id和用户id查询购物车
     * @param id
     * @param currentUserId
     * @return
     */
    @Select("select * from cart where shop_id = #{id} and user_id = #{currentUserId}")
    List<CartVO> selectCartByShopIdAndUserId(Long id, Long currentUserId);
}
