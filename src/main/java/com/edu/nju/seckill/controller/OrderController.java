package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.domain.User;
import com.edu.nju.seckill.domain.dto.CurrentUser;
import com.edu.nju.seckill.domain.dto.OrderSearchResult;
import com.edu.nju.seckill.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lql
 * @date 2020/1/11 20:25
 */
@RestController
@Api(tags = "订单控制类")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
    * @Description: 订单页-搜索订单项 如果keyword为空，则显示全部订单
    * @Param: [currentUser, keyword]
    * @return: com.edu.nju.seckill.common.CommonResult<java.util.List<com.edu.nju.seckill.domain.dto.OrderSearchResult>>
    * @Author: whn
    * @Date: 2020/2/7
    */
    @ApiOperation(value = "订单页-搜索订单项，根据keyword查询用户的订单，如果keyword为空，则显示全部订单")
    @GetMapping({"/order/list/{keyword}","/order/list"})
    public CommonResult<List<OrderSearchResult>> searchOrder(CurrentUser currentUser,
                                                             @PathVariable(required = false) String keyword) {
        if (null == keyword)
            keyword = "";
        User user = currentUser.getUser();
        List<OrderSearchResult> res = orderService.searchOrder(user.getUid(), keyword);
        if (res.size() > 0)
            return CommonResult.success(res, "操作成功");
        else
            return CommonResult.failed("无有效订单数据");

    }
}
