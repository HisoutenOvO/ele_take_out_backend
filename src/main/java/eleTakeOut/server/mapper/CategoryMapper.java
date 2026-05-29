package eleTakeOut.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import eleTakeOut.pojo.entity.Category;
import eleTakeOut.pojo.vo.CategoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category>{
    /**
     * 根据店铺id查询分类
     * @param shopId
     * @return
     */
    @Select("select * from category where shop_id = #{shopId}")
    List<CategoryVO> getCategoriesByShopId(Long shopId);
}
