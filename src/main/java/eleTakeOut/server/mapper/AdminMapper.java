package eleTakeOut.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import eleTakeOut.pojo.entity.Admin;
import org.apache.ibatis.annotations.Select;


public interface AdminMapper extends BaseMapper<Admin> {
    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    @Select("select * from admin where username = #{username}")
    Admin getByUserName(String username);
}
