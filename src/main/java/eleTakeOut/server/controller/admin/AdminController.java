package eleTakeOut.server.controller.admin;

import eleTakeOut.common.result.Result;
import eleTakeOut.pojo.dto.LoginDTO;
import eleTakeOut.pojo.vo.LoginVO;
import eleTakeOut.server.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Slf4j
@Tag(name = "管理员")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    /**
     * 管理员登录
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    @Operation(summary = "管理员登录")
    public Result<LoginVO> login(@RequestBody LoginDTO loginDTO){
        log.info("管理员登录");
        LoginVO loginVO = adminService.login(loginDTO);
        return Result.success(loginVO);
    }
}
