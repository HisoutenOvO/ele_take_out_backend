package eleTakeOut.server.service;

import eleTakeOut.common.result.PageResult;
import eleTakeOut.pojo.dto.DishPageQueryDTO;
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
}
