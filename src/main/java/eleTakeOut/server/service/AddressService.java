package eleTakeOut.server.service;

import eleTakeOut.pojo.dto.AddressDTO;
import eleTakeOut.pojo.vo.AddressVO;

import java.util.List;

public interface AddressService {
    /**
     * 查询用户地址列表
     * @param id
     * @return
     */
    List<AddressVO> getAddress(Long id);

    /**
     * 新增地址
     * @param addressDTO
     */
    void add(AddressDTO addressDTO);
}
