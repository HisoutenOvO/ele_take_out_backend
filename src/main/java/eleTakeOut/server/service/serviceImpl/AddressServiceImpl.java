package eleTakeOut.server.service.serviceImpl;

import eleTakeOut.pojo.vo.AddressVO;
import eleTakeOut.server.mapper.AddressMapper;
import eleTakeOut.server.service.AddressService;
import lombok.RequiredArgsConstructor;
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
}
