package eleTakeOut.server.controller.shop;

import eleTakeOut.common.result.PageResult;
import eleTakeOut.common.result.Result;
import eleTakeOut.pojo.dto.DishSaveDTO;
import eleTakeOut.pojo.dto.DishPageQueryDTO;
import eleTakeOut.pojo.vo.DishShopVO;
import eleTakeOut.server.service.DishService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController("shopDishController")
@RequestMapping("/shop/dishes")
@Tag(name = "店铺端-菜品管理")
@Slf4j
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    /**
     * 菜品条件分页查询
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping
    @Operation(summary = "菜品列表条件分页查询")
    public Result<PageResult> pageQuery(@RequestBody DishPageQueryDTO dishPageQueryDTO){
        log.info("菜品列表查询");
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 新增菜品
     * @return
     */
    @PostMapping
    @Operation(summary = "新增菜品")
    public Result add(@RequestBody DishSaveDTO dishSaveDTO){
        log.info("新增菜品");
        dishService.add(dishSaveDTO);
        return Result.success();
    }

    /**
     * 修改菜品回显
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "修改菜品回显")
    public Result<DishShopVO> getById(@PathVariable Long id){
        log.info("修改菜品回显:{}",id);
        DishShopVO dishShopVO = dishService.getById(id);
        return Result.success(dishShopVO);
    }

    /**
     * 修改菜品
     * @return
     */
    @PutMapping("/{id}")
    @Operation(summary = "修改菜品")
    public Result update(@RequestBody DishSaveDTO dishSaveDTO, @PathVariable Long id){
        log.info("修改菜品:{}",dishSaveDTO);
        dishService.update(dishSaveDTO,id);
        return Result.success();
    }
}
