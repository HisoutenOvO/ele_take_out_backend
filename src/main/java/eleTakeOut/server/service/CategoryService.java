package eleTakeOut.server.service;

import eleTakeOut.pojo.vo.DishVO;

import java.util.List;

public interface CategoryService {
    /**
     * 获取店铺分类下的菜品
     * @param id
     * @return
     */
    List<DishVO> getDishList(Long id);
}
