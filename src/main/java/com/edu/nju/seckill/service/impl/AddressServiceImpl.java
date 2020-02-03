package com.edu.nju.seckill.service.impl;

import com.edu.nju.seckill.dao.AddressMapper;
import com.edu.nju.seckill.domain.dto.AddAddressParam;
import com.edu.nju.seckill.domain.dto.GetAddressResult;
import com.edu.nju.seckill.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lql
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
    public boolean addAddress(Long uid, AddAddressParam param) {
        int res = addressMapper.addAddress(uid, param.getPostcode(), param.getAddress(),
                param.getReceiver_name(), param.getReceiver_phone());
        if (res > 0)
            return true;
        else
            return false;

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
}
