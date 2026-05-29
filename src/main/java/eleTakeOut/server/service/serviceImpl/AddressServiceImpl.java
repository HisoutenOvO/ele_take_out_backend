package eleTakeOut.server.service.serviceImpl;

import eleTakeOut.common.context.BaseContext;
import eleTakeOut.pojo.dto.AddressDTO;
import eleTakeOut.pojo.entity.Address;
import eleTakeOut.pojo.vo.AddressVO;
import eleTakeOut.server.mapper.AddressMapper;
import eleTakeOut.server.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressMapper addressMapper;

    /**
     * 获取用户地址列表
     * @param id
     * @return
     */
    @Override
    public List<AddressVO> getAddress(Long id) {
        return addressMapper.getAddressListByUserId(id);
    }

    /**
     * 新增地址
     * @param addressDTO
     */
    @Override
    public void add(AddressDTO addressDTO) {
        Address address = new Address();
        address.setUserId(BaseContext.getCurrentId());
        BeanUtils.copyProperties(addressDTO,address);
        // 设置默认地址为0
        if(address.getIsDefault() == null){
            address.setIsDefault(1);
        }
        addressMapper.insert(address);
    }
}
