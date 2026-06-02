package eleTakeOut.server.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import eleTakeOut.common.context.BaseContext;
import eleTakeOut.common.result.PageResult;
import eleTakeOut.pojo.dto.DishPageQueryDTO;
import eleTakeOut.pojo.entity.Dish;
import eleTakeOut.pojo.vo.DishDetailVO;
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
}
