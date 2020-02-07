package com.edu.nju.seckill.service;

import com.edu.nju.seckill.domain.dto.OrderSearchResult;

import java.util.List;

/**
 * @author lql
 * @date 2020/1/11 20:19
 */
public interface OrderService {
    /**
    * @Description: 搜索用户订单 通过可选参数keyword进行搜索
    * @Param: [uid, keyword]
    * @return: java.util.List<com.edu.nju.seckill.domain.dto.OrderSearchResult>
    * @Author: whn
    * @Date: 2020/2/7
    */
    List<OrderSearchResult> searchOrder(Long uid, String keyword);
}
