package com.edu.nju.seckill.service;

import com.edu.nju.seckill.domain.Address;
import com.edu.nju.seckill.domain.dto.AddAddressParam;
import com.edu.nju.seckill.domain.dto.GetAddressResult;

import java.util.List;

/**
 * @author lql
 * @date 2020/1/11 20:18
 */
public interface AddressService {
    /**
     * 新增收货地址
     * @param uid
     * @param param
     * @return
     */
    public boolean addAddress(Long uid, AddAddressParam param);

    /**
     * 获取收货地址
     * @param uid
     * @return
     */
    public List<GetAddressResult> getAddress(Long uid);
}
