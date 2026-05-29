package eleTakeOut.server.controller.user;

import eleTakeOut.common.result.Result;
import eleTakeOut.pojo.vo.DishVO;
import eleTakeOut.server.service.DishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("userDishController")
@RequestMapping("/user/dishes")
@Tag(name = "用户-菜品管理")
@Slf4j
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    /**
     * 查询菜品详情
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "查询菜品详情")
    public Result<DishVO> getDishDetail(@PathVariable Long id){
        log.info("查询菜品详情:{}",id);
        DishVO dishVO = dishService.getDetail(id);
        return Result.success(dishVO);
    }
}
