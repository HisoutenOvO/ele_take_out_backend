package eleTakeOut.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import eleTakeOut.pojo.entity.Address;
import eleTakeOut.pojo.vo.AddressVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
