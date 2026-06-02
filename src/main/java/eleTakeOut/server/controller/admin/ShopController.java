package eleTakeOut.server.controller.admin;

import eleTakeOut.common.result.PageResult;
import eleTakeOut.common.result.Result;
import eleTakeOut.pojo.dto.ShopAddDTO;
import eleTakeOut.pojo.dto.ShopPageQueryDTO;
import eleTakeOut.pojo.dto.ShopUpdateDTO;

import eleTakeOut.pojo.vo.ShopSelfVO;
import eleTakeOut.server.service.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@RequestMapping("/admin/shops")
@Slf4j
@Tag(name = "管理员-店铺管理")
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;

    /**
     * 分页查询
     * @param shopPageQueryDTO
     * @return
     */
    @GetMapping("/list")
    @Operation(summary = "分页查询店铺信息")
    public Result<PageResult> ShopPageQuery(ShopPageQueryDTO shopPageQueryDTO){
        log.info("分页查询店铺信息");
        PageResult pageResult = shopService.pageQuery(shopPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 新增店铺
     * @param shopAddDTO
     * @return
     */
    @PostMapping
    @Operation(summary = "新增店铺")
    public Result add(@RequestBody ShopAddDTO shopAddDTO){
        log.info("新增店铺");
        shopService.add(shopAddDTO);
        return Result.success();
    }

    /**
     * 查询店铺信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "查询店铺信息")
    public Result<ShopSelfVO> getById(@PathVariable Long id){
        log.info("查询店铺信息:{}",id);
        ShopSelfVO shopVO = shopService.getByIdSelf(id);
        return Result.success(shopVO);
    }

    /**
     * 修改店铺信息
     * @param id
     * @param shopUpdateDTO
     * @return
     */
    @PutMapping("/{id}")
    @Operation(summary = "修改店铺信息")
    public Result updateShop(@PathVariable Long id,@RequestBody ShopUpdateDTO shopUpdateDTO){
        log.info("修改店铺信息:{}",id);
        shopService.update(id,shopUpdateDTO);
        return Result.success();
    }

    /**
     * 删除店铺信息
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除店铺信息")
    public Result delete(@PathVariable Long id){
        log.info("删除店铺信息:{}",id);
        shopService.deleteById(id);
        return Result.success();
    }



}
