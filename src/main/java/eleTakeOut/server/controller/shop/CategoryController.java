package eleTakeOut.server.controller.shop;

import eleTakeOut.common.context.BaseContext;
import eleTakeOut.common.result.PageResult;
import eleTakeOut.common.result.Result;
import eleTakeOut.pojo.dto.CategorySaveDTO;
import eleTakeOut.pojo.dto.CategoryPageQueryDTO;
import eleTakeOut.pojo.vo.CategoryDishVO;
import eleTakeOut.pojo.vo.CategoryVO;
import eleTakeOut.server.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/list")
    @Operation(summary = "店铺分页查询分类")
    public Result<PageResult> getCategory(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("店铺分类查询:{}", BaseContext.getCurrentShopId());
        PageResult pageResult =  categoryService.getList(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 新增分类
     * @return
     */
    @PostMapping
    @Operation(summary = "新增分类")
    public Result add(@RequestBody CategorySaveDTO categorySaveDTO){
        log.info("新增分类");
        categoryService.add(categorySaveDTO);
        return Result.success();
    }

    /**
     * 分类回显
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据id查询分类")
    public Result<CategoryVO> getCategoryById(@PathVariable Long id ){
        log.info("分类回显:{}", id);
        CategoryVO categoryVO = categoryService.getById(id);
        return Result.success(categoryVO);
    }

    /**
     * 修改分类
     * @param categorySaveDTO
     * @return
     */
    @PutMapping("/{id}")
    @Operation(summary = "分类修改")
    public Result update(@RequestBody CategorySaveDTO categorySaveDTO,@PathVariable Long id){
        log.info("修改分类:{}", id);
        categoryService.update(categorySaveDTO,id);
        return Result.success();
    }

    /**
     * 删除分类
     * @return
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除分类")
    public Result delete(@PathVariable Long id){
        log.info("删除分类:{}",id);
        categoryService.delete(id);
        return Result.success();
    }

    /**
     * 回显分类名称和id
     * @return
     */
    @GetMapping("/dish")
    @Operation(summary = "回显分类名称和id")
    public Result<List<CategoryDishVO>> getByDish(){
        log.info("回显分类名称和id");
        List<CategoryDishVO> categoryDishVOList = categoryService.getCategoryByDish();
        return Result.success(categoryDishVOList);
    }
}
