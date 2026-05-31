package eleTakeOut.server.controller.user;

import eleTakeOut.common.result.Result;
import eleTakeOut.pojo.dto.OrderSubmitDTO;
import eleTakeOut.pojo.vo.OrderSubmitVO;
import eleTakeOut.pojo.vo.OrderVO;
import eleTakeOut.server.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/orders")
@Tag(name = "用户-订单管理")
@Slf4j
@RequiredArgsConstructor
public class OrdersController {
    private final OrderService orderService;

    /**
     * 获取订单列表
     * @return
     */
    @GetMapping
    @Operation(summary = "获取订单列表")
    public Result<List<OrderVO>> list(){
        log.info("获取订单列表");
        List<OrderVO> orderVOList = orderService.getList();
        return Result.success(orderVOList);
    }

    /**
     * 提交订单
     * @return
     */
    @PostMapping
    @Operation(summary = "提交订单")
    public Result<OrderSubmitVO> submit(@RequestBody OrderSubmitDTO orderSubmitDTO){
        log.info("提交订单");
        OrderSubmitVO ordersSubmitVO = orderService.submit(orderSubmitDTO);
        return Result.success(ordersSubmitVO);
    }
}
