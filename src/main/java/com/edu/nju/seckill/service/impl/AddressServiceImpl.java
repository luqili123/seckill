package com.edu.nju.seckill.service.impl;

import com.edu.nju.seckill.dao.AddressMapper;
import com.edu.nju.seckill.domain.Address;
import com.edu.nju.seckill.domain.dto.AddressOperationParam;
import com.edu.nju.seckill.domain.dto.GetAddressResult;
import com.edu.nju.seckill.exception.AddressNotFoundException;
import com.edu.nju.seckill.exception.DataBaseException;
import com.edu.nju.seckill.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author  lql
 * @date 2020/1/11 20:20
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    /**
     * @Description: 新增收货地址
     * @Param: [uid, param]
     * @return: boolean
     * @Author: whn
     * @Date: 2020/2/3
     */
    @Override
    public boolean addAddress(Long uid, String postcode, String address, String receiver_name, String receiver_phone) {
       return addressMapper.addAddress(uid, postcode, address, receiver_name, receiver_phone) == 1;
    }

    /**
     * @Description: 获取已添加的收藏地址
     * @Param: [uid]
     * @return: java.util.List<com.edu.nju.seckill.domain.dto.GetAddressResult>
     * @Author: whn
     * @Date: 2020/2/3
     */
    @Override
    public List<GetAddressResult> getAddress(Long uid) {
        return addressMapper.getAddress(uid);
    }

    /**
     * @Description: 修改收货地址
     * @Param: [aid, uid, postcode, address, receiver_name, receiver_phone]
     * @return: boolean
     * @Author: whn
     * @Date: 2020/2/3
     */
    @Override
    public boolean updateAddress(Integer aid, Long uid, String postcode, String address, String receiver_name, String receiver_phone) {
        return addressMapper.updateAddress(aid, uid, postcode, address, receiver_name, receiver_phone) == 1;
    }

    /**
     * @Description: 删除收货地址
     * @Param: [aid, uid]
     * @return: boolean
     * @Author: whn
     * @Date: 2020/2/3
     */
    @Override
    public boolean deleteAddress(Integer aid, Long uid) {
        if(addressMapper.deleteAddress(aid, uid) == 1) return true;
        throw new DataBaseException("删除地址失败");
    }

    /**
     * @Description: 修改默认收货地址
     * @Param: [uid, aid]
     * @return: boolean
     * @Author: whn
     * @Date: 2020/2/7
     */
    @Override
    public boolean updateDefaultAddress(Long uid, Integer aid) {
        //先判断该uid是否持有该aid
        if (addressMapper.isBelongToCurrentUser(uid, aid) == 1 &&
                addressMapper.updateDefaultAddress(uid, aid) == 1)
                return true;
        throw new DataBaseException("修改默认地址失败，请稍后重试");
    }

    @Override
    public boolean addAddress(Long uid, AddressOperationParam param) {
        if (addAddress(uid,param.getPostcode(),param.getAddress(),
                param.getReceiver_name(),param.getReceiver_phone())){
            return true;
        }
        throw new DataBaseException("地址添加失败，请稍后重试");
    }

    /**
     *
     * @param aid
     * @param uid
     * @author beverly
     * @return
     */
    @Override
    public boolean updateAddress(Integer aid, Long uid, AddressOperationParam param) {
        if (updateAddress(aid,uid,param.getPostcode(),param.getAddress(),
                param.getReceiver_name(),param.getReceiver_phone())){
            return true;
        }
        throw new DataBaseException("地址修改失败，请稍后再试");
    }

    @Override
    public Address getAddressById(Long uid, Integer aid) {
        Address address = addressMapper.selectAddress(uid, aid);
        if (address != null) {
            return address;
        }
        throw new AddressNotFoundException("地址异常，请刷新后重试");
    }
}
