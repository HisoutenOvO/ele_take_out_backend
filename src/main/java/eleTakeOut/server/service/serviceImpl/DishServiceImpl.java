package eleTakeOut.server.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import eleTakeOut.common.context.BaseContext;
import eleTakeOut.common.result.PageResult;
import eleTakeOut.pojo.dto.DishSaveDTO;
import eleTakeOut.pojo.dto.DishPageQueryDTO;
import eleTakeOut.pojo.entity.Dish;
import eleTakeOut.pojo.vo.DishDetailVO;
import eleTakeOut.pojo.vo.DishShopVO;
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

    /**
     * 菜品条件分页查询
     * @param dishPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(),dishPageQueryDTO.getPageSize());
        dishPageQueryDTO.setShopId(BaseContext.getCurrentShopId());
        Page<DishDetailVO> page = dishMapper.pageQuery(dishPageQueryDTO);

        Long total = page.getTotal();
        List<DishDetailVO> dishDetailVOList = page.getResult();
        return new PageResult(total,dishDetailVOList);
    }

    /**
     * 新增菜品
     * @param dishSaveDTO
     */
    @Override
    public void add(DishSaveDTO dishSaveDTO) {
        Dish dish = new Dish();
        dish.setShopId(BaseContext.getCurrentShopId());
        BeanUtils.copyProperties(dishSaveDTO,dish);
        dishMapper.insert(dish);
    }

    /**
     * 根据id查询菜品
     * @param id
     * @return
     */
    @Override
    public DishShopVO getById(Long id) {
        Dish dish = dishMapper.selectById(id);
        DishShopVO dishShopVO = new DishShopVO();
        BeanUtils.copyProperties(dish,dishShopVO);
        return dishShopVO;
    }

    /**
     * 修改菜品
     * @param dishSaveDTO
     * @param id
     */
    @Override
    public void update(DishSaveDTO dishSaveDTO, Long id) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishSaveDTO,dish);
        dish.setId(id);
        dish.setShopId(BaseContext.getCurrentShopId());
        dishMapper.updateById(dish);
    }

    /**
     * 删除菜品
     * @param ids
     */
    @Override
    public void delete(List<Long> ids) {
        dishMapper.deleteBatchIds(ids);
    }
}
