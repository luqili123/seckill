package com.edu.nju.seckill.service;

import com.edu.nju.seckill.domain.Address;
import com.edu.nju.seckill.domain.dto.AddressOperationParam;
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
     * @param postcode
     * @param address
     * @param receiver_name
     * @param receiver_phone
     * @return
     */
    public boolean addAddress(Long uid, String postcode,String address,String receiver_name,String receiver_phone);

    /**
     * 获取收货地址
     * @param uid
     * @return
     */
    public List<GetAddressResult> getAddress(Long uid);

    /**
     * 修改收货地址
     * @param aid
     * @param uid
     * @param postcode
     * @param address
     * @param receiver_name
     * @param receiver_phone
     * @return
     */
    public boolean updateAddress(Integer aid,Long uid, String postcode,String address,String receiver_name,String receiver_phone);

    /**
     * 删除收货地址
     * @param aid
     * @param uid
     * @return
     */
    public boolean deleteAddress(Integer aid,Long uid);
    
    /**
    * @Description: 设置默认地址
    * @Param: [uid, aid]
    * @return: boolean
    * @Author: whn
    * @Date: 2020/2/7
    */
    public boolean updateDefaultAddress(Long uid,Integer aid);

    boolean addAddress(Long uid, AddressOperationParam param);

    /**
     * @description:重载修改地址方法
     * @param aid
     * @param uid
     * @param param
     * @Author: beverly
     * @return
     */
    boolean updateAddress(Integer aid, Long uid, AddressOperationParam param);

    /**
     * 根据id获取地址信息
     * @param uid 用户id
     * @param aid 地址id
     * @return 地址信息
     */
    Address getAddressById(Long uid, Integer aid);
}
