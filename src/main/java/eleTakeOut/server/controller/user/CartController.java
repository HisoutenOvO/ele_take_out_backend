package eleTakeOut.server.controller.user;

import eleTakeOut.common.result.Result;
import eleTakeOut.pojo.dto.CartDTO;
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

    /**
     * 添加购物车
     * @return
     */
    @PostMapping
    @Operation(summary = "添加购物车")
    public Result addCart(@RequestBody CartDTO cartDTO){
        log.info("添加购物车");
        cartService.add(cartDTO);
        return Result.success();
    }

    /**
     * 删除购物车
     * @param cartDTO
     * @return
     */
    @DeleteMapping
    @Operation(summary = "删除购物车")
    public Result deleteCart(CartDTO cartDTO){
        log.info("删除购物车{}",cartDTO.getDishId());
        cartService.delete(cartDTO);
        return Result.success();
    }
}
