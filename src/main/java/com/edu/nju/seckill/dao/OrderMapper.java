package com.edu.nju.seckill.dao;

import com.edu.nju.seckill.domain.Order;
import com.edu.nju.seckill.domain.dto.OrderSearchResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Long oid);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long oid);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<OrderSearchResult> searchOrder(@Param("uid") Long uid, @Param("keyword") String keyword);
}