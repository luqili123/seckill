package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.domain.Order;
import com.edu.nju.seckill.domain.User;
import com.edu.nju.seckill.domain.dto.CurrentUser;
import com.edu.nju.seckill.domain.dto.Order2Param;
import com.edu.nju.seckill.domain.dto.OrderInfoResult;
import com.edu.nju.seckill.domain.dto.OrderParam;
import com.edu.nju.seckill.domain.dto.OrderSearchResult;
import com.edu.nju.seckill.service.OrderService;
import com.edu.nju.seckill.utils.GuardThread;
import com.edu.nju.seckill.utils.OrderIdUtils;
import com.edu.nju.seckill.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.server.UID;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author lql
 * @date 2020/1/11 20:25
 */
@RestController
@Api(tags = "订单控制类")
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisUtil redisUtil;


    /**
     * @Description: 订单页-搜索订单项，根据keyword查询用户的订单，如果keyword为空，则显示全部订单
     * @Param: [currentUser, keyword]
     * @return: com.edu.nju.seckill.common.CommonResult<java.util.Map>
     * @Author: whn
     * @Date: 2020/2/7
     */
    @ApiOperation(value = "订单页-搜索订单项，根据keyword查询用户的订单，如果keyword为空，则显示全部订单")
    @GetMapping({"/list/{status}/{keyword}", "/list/{status}"})
    public CommonResult<Map<String, List<OrderSearchResult>>> searchOrder(CurrentUser currentUser,
                                                                          @PathVariable Integer status,
                                                                          @PathVariable(required = false) String keyword) {
        List<OrderSearchResult> orderList = orderService.getOrderList(currentUser.getUser().getUid(), status, keyword);
        Map<String, List<OrderSearchResult>> res = new HashMap<>();
        res.put("ordItems", orderList);
        return CommonResult.success(res);
    }

    @ApiModelProperty("创建普通订单")
    @PostMapping("/")
    public CommonResult<Boolean> createOrder(CurrentUser currentUser, @RequestBody Order2Param order2Param) {
        boolean res = orderService.createOrder(currentUser.getUser().getUid(), order2Param);
        return CommonResult.success(res, "订单创建成功");
    }

    /**
     * @Description:
     * @Param: [currentUser, orderParam]
     * @return: com.edu.nju.seckill.common.CommonResult<?>
     * @Author: lql
     * @Date: 2020/2/12
     */
    @ApiOperation("创建秒杀订单")
    @PostMapping("/seckill")
    public CommonResult<Boolean> createSeckillOrder(CurrentUser currentUser, @RequestBody Order order) {
       boolean res = orderService.createSecKillOrder(currentUser.getUser().getUid(), order);
       return CommonResult.success(true);
    }

    @ApiOperation("根据oid删除订单")
    @DeleteMapping("/{oid}")
    public CommonResult<?> deleteOrder(@PathVariable(name = "oid", required = true) String oid) {
        if (orderService.deleteByOid(oid)) {
            return CommonResult.success("删除成功！");
        } else {
            return CommonResult.validateFailed("该订单已被删除！");
        }
    }

    @ApiOperation("根据oid获取订单详情")
    @GetMapping("/{oid}")
    public CommonResult<Map<String, OrderInfoResult>> getOrderInfo(CurrentUser currentUser, @PathVariable("oid") String oid) {
        OrderInfoResult orderInfo = orderService.getOrderInfo(currentUser.getUser().getUid(), oid);
        Map<String, OrderInfoResult> res = new HashMap<>();
        res.put("ordItemsInfo", orderInfo);
        return CommonResult.success(res);
    }
}
