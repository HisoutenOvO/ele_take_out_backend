package eleTakeOut.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import eleTakeOut.pojo.entity.Orders;
import eleTakeOut.pojo.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
    /**
     * 根据用户id查询订单
     * @param userId
     * @return
     */
    @Select("select * from orders where user_id = #{userId}")
    List<OrderVO> getListByUserId(Long userId);

    /**
     * 仅添加基本信息占位
     * @param orderId
     */
    @Select("insert into orders(number,user_id,shop_id) values(#{orderId},#{userId},#{shopId})")
    void justAddBasic(String orderId,Long userId,Long shopId);
}
