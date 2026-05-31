package eleTakeOut.server.service.serviceImpl;

import eleTakeOut.common.context.BaseContext;
import eleTakeOut.pojo.dto.CartDTO;
import eleTakeOut.pojo.entity.Cart;
import eleTakeOut.pojo.entity.Dish;
import eleTakeOut.pojo.vo.CartVO;
import eleTakeOut.server.mapper.CartMapper;
import eleTakeOut.server.mapper.DishMapper;
import eleTakeOut.server.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartMapper cartMapper;
    private final DishMapper dishMapper;

    /**
     * 获取购物车列表
     * @return
     */
    @Override
    public List<CartVO> list(Long id) {
        //根据用户所在店铺id获取购物车列表
        return cartMapper.selectCartByShopId(id);
    }

    /**
     * 添加购物车
     * @param cartDTO
     */
    @Override
    public void add(CartDTO cartDTO) {
        //先判断购物车中是否有该商品，如果有则数量加一，如果没有则添加
        Cart cart = cartMapper.getByDishIdAndUserId(cartDTO.getDishId(), BaseContext.getCurrentUserId());
        if(cart == null){
            //没有该商品，则创建该商品
            cart = new Cart();
            //先通过菜品id查询菜品
            Dish dish = dishMapper.selectById(cartDTO.getDishId());
            cart.setName(dish.getName());
            cart.setImage(dish.getImage());
            cart.setAmount(dish.getPrice());
            cart.setNumber(1);
            cart.setUserId(BaseContext.getCurrentUserId());
            cart.setShopId(cartDTO.getShopId());
            cart.setDishId(cartDTO.getDishId());
            cartMapper.insert(cart);
        }else {
            //购物车中已经有该商品，则数量加一
            cart.setNumber(cart.getNumber() + 1);
            cartMapper.updateById(cart);
        }
    }
}
