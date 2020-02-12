package com.edu.nju.seckill.dao;

import com.edu.nju.seckill.domain.Order;
import com.edu.nju.seckill.domain.dto.OrderSearchResult;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

    int deleteByPrimaryKey(Long oid);

    int insert(Order order);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long oid);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<OrderSearchResult> searchOrder(@Param("uid") Long uid, @Param("status") Integer status,
                                        @Param("keyword") String keyword);

    List<OrderSearchResult> searchOrderViaStatus(@Param("uid") Long uid, @Param("status") Integer status,
                                        @Param("keyword") String keyword);

    List<Order> selectByStatus(int status);
}