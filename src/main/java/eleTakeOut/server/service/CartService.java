package eleTakeOut.server.service;

import eleTakeOut.pojo.vo.CartVO;

import java.util.List;

public interface CartService {
    /**
     * 获取购物车列表
     * @return
     */
    List<CartVO> list(Long id);
}
