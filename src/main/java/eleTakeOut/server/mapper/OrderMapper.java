package eleTakeOut.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import eleTakeOut.pojo.dto.OrderPageQueryDTO;
import eleTakeOut.pojo.entity.OrderDetail;
import eleTakeOut.pojo.entity.Orders;
import eleTakeOut.pojo.vo.OrderListVO;
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
    List<Orders> getListByUserId(Long userId);

    /**
     * 批量插入订单详情
     * @param orderDetailList
     */
    void insertOrderDetailList(List<OrderDetail> orderDetailList);

    /**
     * 根据订单id查询订单详情
     * @param orderId
     * @return
     */
    @Select("select * from order_detail where order_id = #{orderId}")
    List<OrderDetail> getDetailListByOrderId(Long orderId);

    /**
     * 订单列表
     * @param orderPageQueryDTO
     * @return
     */
    Page<OrderListVO> pageQuery(OrderPageQueryDTO orderPageQueryDTO);
}
