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
        List<Cart> cartList = cartMapper.getByDishIdAndUserIdAndShopId(
                cartDTO.getDishId(),
                BaseContext.getCurrentUserId(),
                cartDTO.getShopId()
        );
        if (cartList.isEmpty()) {
            // 购物车没有该商品，新增
            Cart cart = new Cart();
            Dish dish = dishMapper.selectById(cartDTO.getDishId());
            cart.setName(dish.getName());
            cart.setImage(dish.getImage());
            cart.setAmount(dish.getPrice());
            cart.setNumber(1);
            cart.setUserId(BaseContext.getCurrentUserId());
            cart.setShopId(cartDTO.getShopId());
            cart.setDishId(cartDTO.getDishId());
            cartMapper.insert(cart);
        } else {
            // 已有该商品，数量加一
            Cart cart = cartList.get(0);
            cart.setNumber(cart.getNumber() + 1);
            cartMapper.updateById(cart);
        }
    }

    /**
     * 删除购物车
     * @param cartDTO
     */
    @Override
    public void delete(CartDTO cartDTO) {
        //先判断购物车中该商品数量是否为1
        Cart cart = cartMapper.getByDishIdAndUserIdAndShopId(cartDTO.getDishId(), BaseContext.getCurrentUserId(),cartDTO.getShopId()).get(0);
        if(cart.getNumber() != 1){
            //直接减1
            cart.setNumber(cart.getNumber() - 1);
            cartMapper.updateById(cart);
        }else {
            //否则直接删除
            cartMapper.deleteById(cart);
        }
    }
}
