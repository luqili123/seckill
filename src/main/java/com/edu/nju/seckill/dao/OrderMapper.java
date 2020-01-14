package com.edu.nju.seckill.dao;

import com.edu.nju.seckill.domain.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Long oid);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long oid);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}