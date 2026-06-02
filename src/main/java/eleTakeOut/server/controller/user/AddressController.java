package eleTakeOut.server.controller.user;

import eleTakeOut.common.result.Result;
import eleTakeOut.pojo.dto.AddressDTO;
import eleTakeOut.pojo.vo.AddressVO;
import eleTakeOut.server.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping
    @Operation(summary = "查询用户地址列表")
    public Result<List<AddressVO>> addressList(){
        log.info("查询用户地址信息");
        List<AddressVO> addressVOList = addressService.getAddress();
        return Result.success(addressVOList);
    }

    /**
     * 新增地址
     * @param addressDTO
     * @return
     */
    @PostMapping
    @Operation(summary = "新增地址")
    public Result add(@RequestBody AddressDTO addressDTO){
        log.info("新增地址");
        addressService.add(addressDTO);
        return Result.success();
    }

    /**
     * 根据地址id查询地址
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据地址id查询地址")
    public Result<AddressVO> getById(@PathVariable Long id){
        log.info("查询id:{}的地址",id);
        AddressVO addressVO = addressService.getById(id);
        return Result.success(addressVO);
    }

    /**
     * 修改地址
     * @return
     */
    @PutMapping("/{id}")
    @Operation(summary = "修改地址")
    public Result update(@RequestBody AddressDTO addressDTO,@PathVariable Long id){
        log.info("修改地址id:{}",id);
        addressService.update(addressDTO,id);
        return Result.success();
    }

    /**
     * 删除地址
     * @return
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除地址")
    public Result delete(@PathVariable Long id){
        log.info("删除地址:{}",id);
        addressService.delete(id);
        return Result.success();
    }
}
