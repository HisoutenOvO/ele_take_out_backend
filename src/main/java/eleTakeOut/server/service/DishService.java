package eleTakeOut.server.service;

import eleTakeOut.common.result.PageResult;
import eleTakeOut.pojo.dto.DishSaveDTO;
import eleTakeOut.pojo.dto.DishPageQueryDTO;
import eleTakeOut.pojo.vo.DishShopVO;
import eleTakeOut.pojo.vo.DishVO;

import java.util.List;

public interface DishService {

    /**
     * 获取菜品详情
     * @param id
     * @return
     */
    DishVO getDetail(Long id);

    /**
     * 菜品条件分页查询
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 新增菜品
     * @param dishSaveDTO
     */
    void add(DishSaveDTO dishSaveDTO);

    /**
     * 根据id查询菜品
     * @param id
     */
    DishShopVO getById(Long id);

    /**
     * 修改菜品
     * @param dishSaveDTO
     * @param id
     */
    void update(DishSaveDTO dishSaveDTO, Long id);

    /**
     * 删除菜品
     * @param ids
     */
    void delete(List<Long> ids);
}
