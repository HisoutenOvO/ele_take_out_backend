package eleTakeOut.server.service;

import eleTakeOut.pojo.dto.AddressDTO;
import eleTakeOut.pojo.vo.AddressVO;

import java.util.List;

public interface AddressService {
    /**
     * 查询用户地址列表
     * @return
     */
    List<AddressVO> getAddress();

    /**
     * 新增地址
     * @param addressDTO
     */
    void add(AddressDTO addressDTO);

    /**
     * 根据id查询地址
     * @param id
     * @return
     */
    AddressVO getById(Long id);

    /**
     * 修改地址
     * @param addressDTO
     */
    void update(AddressDTO addressDTO,Long id);
}
