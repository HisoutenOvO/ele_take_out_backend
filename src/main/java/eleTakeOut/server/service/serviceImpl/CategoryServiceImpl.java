package eleTakeOut.server.service.serviceImpl;

import eleTakeOut.pojo.vo.DishVO;
import eleTakeOut.server.mapper.CategoryMapper;
import eleTakeOut.server.mapper.DishMapper;
import eleTakeOut.server.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final DishMapper dishMapper;

    /**
     * 获取菜品列表
     * @param id
     * @return
     */
    @Override
    public List<DishVO> getDishList(Long id) {
        return dishMapper.getDishListByCategoryId(id);
    }
}
