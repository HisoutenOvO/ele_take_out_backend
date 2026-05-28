package eleTakeOut.server.controller.admin;

import eleTakeOut.common.result.PageResult;
import eleTakeOut.common.result.Result;
import eleTakeOut.pojo.dto.ShopPageQueryDTO;
import eleTakeOut.server.service.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
