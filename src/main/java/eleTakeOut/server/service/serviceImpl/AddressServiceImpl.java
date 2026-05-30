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
     * @return
     */
    @Override
    public List<AddressVO> getAddress() {
        //TODO 暂时把用户id设为1，登录功能实现后清除该语句
        BaseContext.setCurrentUserId(1L);
        return addressMapper.getAddressListByUserId(BaseContext.getCurrentUserId());
    }

    /**
     * 新增地址
     * @param addressDTO
     */
    @Override
    public void add(AddressDTO addressDTO) {
        Address address = new Address();
        address.setUserId(BaseContext.getCurrentUserId());
        BeanUtils.copyProperties(addressDTO,address);
        // 设置默认地址为0
        if(address.getIsDefault() == null){
            address.setIsDefault(1);
        }
        addressMapper.insert(address);
    }

    /**
     * 根据id查询地址
     * @param id
     * @return
     */
    @Override
    public AddressVO getById(Long id) {
        Address address = addressMapper.selectById(id);
        AddressVO addressVO = new AddressVO();
        BeanUtils.copyProperties(address,addressVO);
        return addressVO;
    }

    /**
     * 修改地址
     * @param addressDTO
     */
    @Override
    public void update(AddressDTO addressDTO,Long id) {
        Address address = addressMapper.selectById(id);
        BeanUtils.copyProperties(addressDTO,address);
        //如果设置了此地址为默认地址，则将其他地址的默认地址设置为0
        if(address.getIsDefault() == 1){
            addressMapper.changeDefault();
        }
        address.setId(id);
        addressMapper.updateById(address);
    }

    /**
     * 删除地址
     * @param id
     */
    @Override
    public void delete(Long id) {
        //如果将默认地址删去，则将第一个地址设置为默认地址
        if(addressMapper.selectById(id).getIsDefault() == 1){
            List<AddressVO> addressList = addressMapper.getAddressListByUserId(BaseContext.getCurrentUserId());
            AddressVO addressVO =  addressList.get(0);
            Address address = new Address();
            BeanUtils.copyProperties(addressVO,address);
            addressMapper.updateById(address);
        }
        addressMapper.deleteById(id);
    }


}
