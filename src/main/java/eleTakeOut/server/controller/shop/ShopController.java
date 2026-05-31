package eleTakeOut.server.controller.shop;

import eleTakeOut.common.result.Result;
import eleTakeOut.pojo.dto.LoginDTO;
import eleTakeOut.pojo.vo.LoginVO;
import eleTakeOut.server.service.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("shopShopController")
@RequestMapping("/shop")
@Tag(name = "店铺-店铺管理")
@Slf4j
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;

    @PostMapping("/login")
    @Operation(summary = "店铺登录")
    public Result<LoginVO> login(@RequestBody LoginDTO loginDTO){
        log.info("店铺登录");
        LoginVO loginVO = shopService.login(loginDTO);
        return Result.success(loginVO);
    }
}
