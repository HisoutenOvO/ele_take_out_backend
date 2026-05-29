package eleTakeOut.server.service;

import eleTakeOut.pojo.vo.DishVO;

import java.util.List;

public interface DishService {

    /**
     * 获取菜品详情
     * @param id
     * @return
     */
    DishVO getDetail(Long id);
}
