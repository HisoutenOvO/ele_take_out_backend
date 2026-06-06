package eleTakeOut.server.controller.shop;

import eleTakeOut.common.context.BaseContext;
import eleTakeOut.common.result.Result;
import eleTakeOut.pojo.dto.ChangePasswordDTO;
import eleTakeOut.pojo.dto.LoginDTO;
import eleTakeOut.pojo.dto.ShopUpdateSelfDTO;
import eleTakeOut.pojo.vo.LoginVO;
import eleTakeOut.pojo.vo.ShopSelfVO;
import eleTakeOut.server.service.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("shopShopController")
@RequestMapping("/shop/shops")
@Tag(name = "店铺-店铺管理")
@Slf4j
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;
    /**
     * 店铺登录
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    @Operation(summary = "店铺登录")
    public Result<LoginVO> login(@RequestBody LoginDTO loginDTO){
        log.info("店铺登录");
        LoginVO loginVO = shopService.login(loginDTO);
        return Result.success(loginVO);
    }

    /**
     * 获取店铺信息
     * @return
     */
    @GetMapping("/info")
    @Operation(summary = "获取店铺信息")
    public Result<ShopSelfVO> getInfo(){
        log.info("获取店铺信息:{}", BaseContext.getCurrentShopId());
        ShopSelfVO shopVO = shopService.getByIdSelf(BaseContext.getCurrentShopId());
        return Result.success(shopVO);
    }

    /**
     * 修改店铺信息
     * @return
     */
    @PutMapping("/info")
    @Operation(summary = "修改店铺信息")
    public Result updateInfo(@RequestBody ShopUpdateSelfDTO shopUpdateSelfDTO){
        log.info("修改店铺信息:{}", BaseContext.getCurrentShopId());
        shopService.updateBySelf(BaseContext.getCurrentShopId(),shopUpdateSelfDTO);
        return Result.success();
    }

    /**
     * 商铺修改密码
     * @param dto
     * @return
     */
    @PutMapping("/changePassword")
    @Operation(summary = "商家修改密码")
    public Result changePassword(@RequestBody ChangePasswordDTO dto) {
        log.info("商家修改密码");
        shopService.changePassword(BaseContext.getCurrentShopId(), dto);
        return Result.success();
    }
}
