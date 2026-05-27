package eleTakeOut.server.controller.admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("adminShopController")
@RequestMapping("/admin/shops")
@Slf4j
@Tag(name = "管理员-店铺管理")
@RequiredArgsConstructor
public class ShopController {


}
