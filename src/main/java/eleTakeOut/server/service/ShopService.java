package eleTakeOut.server.service;

import eleTakeOut.common.result.PageResult;
import eleTakeOut.pojo.dto.ShopDTO;
import eleTakeOut.pojo.dto.ShopPageQueryDTO;
import eleTakeOut.pojo.entity.ShopVO;

public interface ShopService {
    /**
     * 店铺分页查询
     * @param shopPageQueryDTO
     * @return
     */
    PageResult pageQuery(ShopPageQueryDTO shopPageQueryDTO);

    /**
     * 新增店铺
     * @param shopDTO
     */
    void add(ShopDTO shopDTO);

    /**
     * 根据id查询店铺信息
     * @param id
     * @return
     */
    ShopVO getById(Long id);

    /**
     * 修改店铺信息
     * @param id
     */
    void update(Long id,ShopDTO shopDTO);

    /**
     * 删除店铺
     * @param id
     */
    void deleteById(Long id);
}
