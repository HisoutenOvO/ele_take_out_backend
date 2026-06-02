package eleTakeOut.server.service;

import eleTakeOut.common.result.PageResult;
import eleTakeOut.pojo.dto.CategoryPageQueryDTO;
import eleTakeOut.pojo.vo.CategoryVO;
import eleTakeOut.pojo.vo.DishVO;

import java.util.List;

public interface CategoryService {
    /**
     * 获取店铺分类下的菜品
     *
     * @param id
     * @return
     */
    List<DishVO> getDishList(Long id);

    /**
     * 店铺分页查询分类
     * @return
     */
    PageResult getList(CategoryPageQueryDTO categoryPageQueryDTO);
}
