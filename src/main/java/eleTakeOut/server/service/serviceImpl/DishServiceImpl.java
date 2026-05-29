package eleTakeOut.server.service.serviceImpl;

import eleTakeOut.pojo.entity.Dish;
import eleTakeOut.pojo.vo.DishVO;
import eleTakeOut.server.mapper.DishMapper;
import eleTakeOut.server.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {
    private final DishMapper dishMapper;

    /**
     * 获取菜品详情
     * @param id
     * @return
     */
    @Override
    public DishVO getDetail(Long id) {
        Dish dish = dishMapper.selectById(id);
        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish,dishVO);
        return dishVO;
    }
}
