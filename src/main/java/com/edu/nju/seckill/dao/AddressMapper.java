package com.edu.nju.seckill.dao;

import com.edu.nju.seckill.domain.Address;
import com.edu.nju.seckill.domain.dto.GetAddressResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressMapper {

    int deleteByPrimaryKey(Integer aid);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer aid);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    int addAddress(@Param("uid") Long uid,@Param("postcode")String postcode,
                   @Param("address") String address,@Param("receiver_name")String receiver_name,
                   @Param("receiver_phone")String receiver_phone);

    List<GetAddressResult> getAddress(@Param("uid") Long uid);

    /*
        该uid是否属于该aid
     */
    int isBelongToCurrentUser(@Param("uid") Long uid,@Param("aid") Integer aid);

    int updateAddress(@Param("aid") Integer aid,@Param("uid") Long uid,@Param("postcode")String postcode,
                      @Param("address") String address,@Param("receiver_name")String receiver_name,
                      @Param("receiver_phone")String receiver_phone);

    int deleteAddress(@Param("aid") Integer aid,@Param("uid") Long uid);

    int updateDefaultAddress(@Param("uid") Long uid,@Param("aid") Integer aid);

    Address selectAddress(Long uid, Integer aid);
}