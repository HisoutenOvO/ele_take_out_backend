package eleTakeOut.server.controller.shop;

import eleTakeOut.common.context.BaseContext;
import eleTakeOut.common.result.PageResult;
import eleTakeOut.common.result.Result;
import eleTakeOut.pojo.dto.CategoryPageQueryDTO;
import eleTakeOut.server.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("shopCategoryController")
@RequestMapping("/shop/categories")
@Tag(name = "店铺-分类管理")
@Slf4j
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    /**
     * 店铺分页查询分类
     * @return
     */
    @GetMapping("/categories")
    @Operation(summary = "店铺分页查询分类")
    public Result<PageResult> getCategory(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("店铺分类查询:{}", BaseContext.getCurrentShopId());
        PageResult pageResult =  categoryService.getList(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

}
