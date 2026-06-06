package eleTakeOut.server.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import eleTakeOut.common.context.BaseContext;
import eleTakeOut.common.exception.BaseException;
import eleTakeOut.common.result.PageResult;
import eleTakeOut.pojo.dto.CategorySaveDTO;
import eleTakeOut.pojo.dto.CategoryPageQueryDTO;
import eleTakeOut.pojo.entity.Category;
import eleTakeOut.pojo.vo.CategoryDetailVO;
import eleTakeOut.pojo.vo.CategoryDishVO;
import eleTakeOut.pojo.vo.CategoryVO;
import eleTakeOut.pojo.vo.DishVO;
import eleTakeOut.server.mapper.CategoryMapper;
import eleTakeOut.server.mapper.DishMapper;
import eleTakeOut.server.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        categoryPageQueryDTO.setShopId(BaseContext.getCurrentShopId());
        Page<CategoryDetailVO> page = categoryMapper.getCategoryList(categoryPageQueryDTO);
        Long total = page.getTotal();
        List<CategoryDetailVO> categoryVOList = page.getResult();
        return new PageResult(total,categoryVOList);
    }

    /**
     * 新增分类
     * @param categorySaveDTO
     */
    @Override
    public void add(CategorySaveDTO categorySaveDTO) {
        Category category = new Category();
        category.setName(categorySaveDTO.getName());
        category.setEmoji(categorySaveDTO.getEmoji());
        category.setShopId(BaseContext.getCurrentShopId());
        categoryMapper.insert(category);
    }

    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    @Override
    public CategoryVO getById(Long id) {
        Category category = categoryMapper.selectById(id);
        CategoryVO categoryVO = new CategoryVO();
        categoryVO.setId(category.getId());
        categoryVO.setName(category.getName());
        categoryVO.setEmoji(category.getEmoji());
        return categoryVO;
    }

    /**
     * 修改分类
     * @param categorySaveDTO
     * @param id
     */
    @Override
    public void update(CategorySaveDTO categorySaveDTO, Long id) {
        Category category = categoryMapper.selectById(id);
        BeanUtils.copyProperties(categorySaveDTO,category);
        category.setShopId(BaseContext.getCurrentShopId());
        category.setId(id);
        categoryMapper.updateById(category);
    }

    /**
     * 删除分类
     * @param id
     */
    @Override
    public void delete(Long id) {
        //若发现分类下有菜品，则禁止删除
        if(!dishMapper.getDishListByCategoryId(id).isEmpty()){
            throw new BaseException("该分类下关联其他菜品，请移除菜品后再删除该分类");
        }
        categoryMapper.deleteById(id);
    }

    /**
     * 回显分类名称和id
     * @return
     */
    @Override
    public List<CategoryDishVO> getCategoryByDish() {
        List<CategoryVO> categoriesByShopId = categoryMapper.getCategoriesByShopId(BaseContext.getCurrentShopId());
        List<CategoryDishVO> categoryDishVOList = new ArrayList<>();
        for(CategoryVO categoryVO:categoriesByShopId){
            CategoryDishVO categoryDishVO = new CategoryDishVO();
            categoryDishVO.setId(categoryVO.getId());
            categoryDishVO.setName(categoryVO.getName());
            categoryDishVOList.add(categoryDishVO);
        }
        return categoryDishVOList;
    }
}
