package com.edu.nju.seckill.dao;

import com.edu.nju.seckill.domain.Order;
import com.edu.nju.seckill.domain.dto.OrderInfoResult;
import com.edu.nju.seckill.domain.dto.OrderSearchResult;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

    int deleteByPrimaryKey(String oid);

    int insert(Order order);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String oid);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<OrderSearchResult> searchOrder(@Param("uid") Long uid, @Param("status") Integer status,
                                        @Param("keyword") String keyword);

    List<OrderSearchResult> searchOrderViaStatus(@Param("uid") Long uid, @Param("status") Integer status,
                                        @Param("keyword") String keyword);

    List<Order> selectByStatus(int status);

    OrderInfoResult selectOrderDetailInfo(@Param("uid") Long uid,@Param("oid") String oid);

    List<OrderSearchResult> selectOrderList(Long uid, Integer status, String keyword);
}