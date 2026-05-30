package eleTakeOut.server.controller.admin;

import eleTakeOut.common.result.Result;
import eleTakeOut.pojo.vo.CartVO;
import eleTakeOut.server.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/cart")
@Tag(name = "用户-购物车管理")
@Slf4j
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    /**
     * 获取购物车列表
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取购物车列表")
    public Result<List<CartVO>> list(@PathVariable Long id){
        log.info("获取购物车列表");
        List<CartVO> cartVOList = cartService.list(id);
        return Result.success(cartVOList);
    }
}
