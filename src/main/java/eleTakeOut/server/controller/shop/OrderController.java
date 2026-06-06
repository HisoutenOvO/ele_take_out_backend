package eleTakeOut.server.controller.shop;

import eleTakeOut.common.result.PageResult;
import eleTakeOut.common.result.Result;
import eleTakeOut.pojo.dto.OrderPageQueryDTO;
import eleTakeOut.pojo.vo.OrderShopDetailVO;
import eleTakeOut.server.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("shopOrderController")
@RequestMapping("/shop/orders")
@Tag(name = "店铺端-订单管理")
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    /**
     * 订单列表
     * @return
     */
    @GetMapping("/list")
    @Operation(summary = "订单列表")
    public Result<PageResult> PageQuery(OrderPageQueryDTO orderPageQueryDTO) {
        log.info("订单列表");
        PageResult pageResult = orderService.pageQuery(orderPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 订单详情
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "订单详情")
    public Result<OrderShopDetailVO> orderDetail(@PathVariable Long id){
        log.info("查询订单详情");
        OrderShopDetailVO orderShopDetailVO = orderService.getByIdWithShop(id);
        return Result.success(orderShopDetailVO);
    }
}
