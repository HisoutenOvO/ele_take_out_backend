package eleTakeOut.server.controller.user;

import eleTakeOut.common.result.Result;
import eleTakeOut.pojo.vo.AddressVO;
import eleTakeOut.server.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/addresses")
@Tag(name = "用户-地址管理")
@Slf4j
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    /**
     * 查询用户地址列表
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "查询用户地址列表")
    public Result<List<AddressVO>> addressList(@PathVariable Long id){
        log.info("查询用户地址信息:{}",id);
        List<AddressVO> addressVOList = addressService.getAddress(id);
        return Result.success(addressVOList);
    }
}
