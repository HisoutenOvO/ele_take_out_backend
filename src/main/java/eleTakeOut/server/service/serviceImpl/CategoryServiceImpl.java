package eleTakeOut.server.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import eleTakeOut.common.context.BaseContext;
import eleTakeOut.common.result.PageResult;
import eleTakeOut.pojo.dto.CategoryPageQueryDTO;
import eleTakeOut.pojo.entity.Category;
import eleTakeOut.pojo.vo.CategoryVO;
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

    /**
     * 店铺分页查询分类
     * @return
     */
    @Override
    public PageResult getList(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getPage(),categoryPageQueryDTO.getPageSize());
        Page<CategoryVO> page = categoryMapper.getCategoryList(categoryPageQueryDTO, BaseContext.getCurrentShopId());
        Long total = page.getTotal();
        List<CategoryVO> categoryVOList = page.getResult();
        return new PageResult(total,categoryVOList);
    }
}
