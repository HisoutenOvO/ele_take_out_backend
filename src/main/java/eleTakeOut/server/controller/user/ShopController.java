package eleTakeOut.server.controller.user;


import eleTakeOut.common.result.Result;
import eleTakeOut.pojo.dto.ShopListDTO;
import eleTakeOut.pojo.dto.ShopPageQueryDTO;
import eleTakeOut.pojo.vo.ShopVO;
import eleTakeOut.pojo.vo.CategoryVO;
import eleTakeOut.server.service.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userShopController")
@RequestMapping("/user/shops")
@Tag(name = "用户-店铺管理")
@Slf4j
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;

    /**
     * 获取商家列表
     * @param shopListDTO
     * @return
     */
    @GetMapping
    @Operation(summary = "查询店铺列表")
    public Result<List<ShopVO>> getShopList(ShopListDTO shopListDTO){
        log.info("查询店铺列表");
        List<ShopVO> shopVO = shopService.getShopList(shopListDTO);
        return Result.success(shopVO);
    }

    /**
     * 查询店铺信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "查询店铺信息")
    public Result<ShopVO> getById(@PathVariable Long id){
        log.info("查询店铺信息:{}",id);
        ShopVO shopVO = shopService.getById(id);
        return Result.success(shopVO);
    }

    /**
     * 查询店铺分类
     * @param id
     * @return
     */
    @GetMapping("/categories/{id}")
    @Operation(summary = "查询店铺分类")
    public Result<List<CategoryVO>> getCategory(@PathVariable Long id){
        log.info("查询店铺分类:{}",id);
        List<CategoryVO> categoryVOList = shopService.getCategories(id);
        return Result.success(categoryVOList);
    }
}
