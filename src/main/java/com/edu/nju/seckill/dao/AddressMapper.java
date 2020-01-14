package com.edu.nju.seckill.dao;

import com.edu.nju.seckill.domain.Address;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressMapper {

    int deleteByPrimaryKey(Integer aid);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer aid);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);
}