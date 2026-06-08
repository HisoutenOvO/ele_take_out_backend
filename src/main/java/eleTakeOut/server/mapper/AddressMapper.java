package eleTakeOut.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import eleTakeOut.pojo.entity.Address;
import eleTakeOut.pojo.vo.AddressVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Collection;
import java.util.List;

@Mapper
public interface AddressMapper extends BaseMapper<Address> {
    /**
     * 根据用户id查询地址
     * @param userId
     * @return
     */
    @Select("select * from address where user_id = #{userId}")
    List<AddressVO> getAddressListByUserId(Long userId);

    /**
     * 将所有地址默认值全设为0
     */
    @Update("update address set is_default = 0")
    void changeDefault();

    /**
     * 根据地址id查询地址
     * @param id,userId
     * @return
     */
    @Select("select * from address where id = #{id} and user_id = #{userId}")
    Address getByIdAndUserId(Long id,Long userId);


    /**
     * 根据用户id和地址查询地址
     * @param currentUserId
     * @param detail
     * @return
     */
    @Select("select * from address where user_id = #{currentUserId} and detail = #{detail}")
    Address selectByUserIdAndDetail(Long currentUserId, String detail);
}
