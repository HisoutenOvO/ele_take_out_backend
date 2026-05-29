package eleTakeOut.server.controller.user;

import eleTakeOut.common.result.Result;
import eleTakeOut.pojo.vo.DishVO;
import eleTakeOut.server.service.CategoryService;
import eleTakeOut.server.service.DishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userCategoryController")
@RequestMapping("/user/categories")
@Tag(name = "用户-分类管理")
@Slf4j
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final DishService dishService;
    /**
     * 查询店铺分类下的菜品
     * @param id
     * @return
     */
    @GetMapping("/dishes/{id}")
    @Operation(summary = "查询店铺分类下的菜品")
    public Result<List<DishVO>> getDishes(@PathVariable Long id){
        log.info("查询店铺分类下的菜品:{}",id);
        List<DishVO> dishVOList = categoryService.getDishList(id);
        return Result.success(dishVOList);
    }
}
