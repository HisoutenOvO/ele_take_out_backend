package eleTakeOut.server.controller.shop;

import eleTakeOut.common.result.PageResult;
import eleTakeOut.common.result.Result;
import eleTakeOut.pojo.dto.DishPageQueryDTO;
import eleTakeOut.server.service.DishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("shopDishController")
@RequestMapping("/shop/dishes")
@Tag(name = "店铺端-菜品管理")
@Slf4j
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;
    /*/
    菜品条件分页查询
     */
    @GetMapping
    @Operation(summary = "菜品列表条件分页查询")
    public Result<PageResult> pageQuery(@RequestBody DishPageQueryDTO dishPageQueryDTO){
        log.info("菜品列表查询");
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }
}
