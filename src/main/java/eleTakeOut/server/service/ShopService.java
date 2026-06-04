package eleTakeOut.server.service;

import eleTakeOut.common.result.PageResult;
import eleTakeOut.pojo.dto.*;
import eleTakeOut.pojo.vo.*;

import java.util.List;

public interface ShopService {
    /**
     * 店铺分页查询
     * @param shopPageQueryDTO
     * @return
     */
    PageResult pageQuery(ShopPageQueryDTO shopPageQueryDTO);

    /**
     * 新增店铺
     * @param shopAddDTO
     */
    void add(ShopAddDTO shopAddDTO);

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
    void update(Long id, ShopUpdateDTO shopUpdateDTO);

    /**
     * 删除店铺
     * @param ids
     */
    void deleteByIds(List<Long> ids);

    /**
     * 获取店铺列表
     * @param shopPageQueryDTO
     * @return
     */
    List<ShopVO> getShopList(ShopPageQueryDTO shopPageQueryDTO);

    /**
     * 获取店铺分类
     * @param id
     * @return
     */
    List<CategoryVO> getCategories(Long id);

    /**
     * 商家登录
     * @param loginDTO
     * @return
     */
    LoginVO login(LoginDTO loginDTO);

    /**
     * 修改店铺信息
     * @param currentShopId
     * @param shopUpdateSelfDTO
     */
    void updateBySelf(Long currentShopId, ShopUpdateSelfDTO shopUpdateSelfDTO);

    /**
     * 店铺获取店铺自己信息
     * @param currentShopId
     * @return
     */
    ShopSelfVO getByIdSelf(Long currentShopId);

    /**
     * 商家获取店铺信息
     * @param id
     * @return
     */
    ShopAdminVO getByIdWithAdmin(Long id);
}
