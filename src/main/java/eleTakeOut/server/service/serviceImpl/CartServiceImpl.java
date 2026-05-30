package eleTakeOut.server.service.serviceImpl;

import eleTakeOut.pojo.vo.CartVO;
import eleTakeOut.server.mapper.CartMapper;
import eleTakeOut.server.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartMapper cartMapper;

    /**
     * 获取购物车列表
     * @return
     */
    @Override
    public List<CartVO> list(Long id) {
        //根据用户所在店铺id获取购物车列表
        return cartMapper.selectCartByShopId(id);
    }
}
