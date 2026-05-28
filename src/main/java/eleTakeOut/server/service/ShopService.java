package eleTakeOut.server.service;

import eleTakeOut.common.result.PageResult;
import eleTakeOut.pojo.dto.ShopPageQueryDTO;

public interface ShopService {
    /**
     * 店铺分页查询
     * @param shopPageQueryDTO
     * @return
     */
    PageResult pageQuery(ShopPageQueryDTO shopPageQueryDTO);
}
