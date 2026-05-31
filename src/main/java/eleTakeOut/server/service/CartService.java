package eleTakeOut.server.service;

import eleTakeOut.pojo.dto.CartDTO;
import eleTakeOut.pojo.vo.CartVO;

import java.util.List;

public interface CartService {
    /**
     * 获取购物车列表
     * @return
     */
    List<CartVO> list(Long id);

    /**
     * 添加购物车
     * @param cartDTO
     */
    void add(CartDTO cartDTO);

    /**
     * 删除购物车
     * @param cartDTO
     */
    void delete(CartDTO cartDTO);
}
