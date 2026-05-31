package eleTakeOut.server.service;


import eleTakeOut.pojo.dto.OrderSubmitDTO;
import eleTakeOut.pojo.vo.OrderSubmitVO;
import eleTakeOut.pojo.vo.OrderVO;

import java.util.List;

public interface OrderService {
    /**
     * 获取订单列表
     * @return
     */
    List<OrderVO> getList();

    /**
     * 提交订单
     * @param orderSubmitDTO
     * @return
     */
    OrderSubmitVO submit(OrderSubmitDTO orderSubmitDTO);

    /**
     * 支付订单
     * @param id
     * @param payMethod
     */
    void payment(Long id, Integer payMethod);

    /**
     * 获取订单详情
     * @param id
     * @return
     */
    OrderVO getDetail(Long id);
}
