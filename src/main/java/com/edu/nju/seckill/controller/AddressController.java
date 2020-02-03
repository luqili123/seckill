package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.domain.User;
import com.edu.nju.seckill.domain.dto.AddAddressParam;
import com.edu.nju.seckill.domain.dto.GetAddressResult;
import com.edu.nju.seckill.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lql
 * @date 2020/1/11 20:25
 */
@RestController
@Api(tags = "收货地址控制类")
public class AddressController {

    @Autowired
    private AddressService addressService;


    /**
    * @Description: 新增收货地址 并对邮编和电话号码进行验证
    * @Param: [user, param, bindingResult]
    * @return: com.edu.nju.seckill.common.CommonResult<java.lang.String>
    * @Author: whn
    * @Date: 2020/2/3
    */
    @ApiOperation(value = "添加收货地址")
    @PostMapping("/address/add")
    public CommonResult<String> addAddress(User user, @Validated AddAddressParam param,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            String errorInfo="";
            List<ObjectError> list = bindingResult.getAllErrors();
            for (ObjectError error : list) {
                System.out.println(error.toString());
                errorInfo+=error.toString();
            }
            return CommonResult.validateFailed(errorInfo);
        }
        if(addressService.addAddress(user.getUid(),param))
            return CommonResult.success(null,"新增地址成功");
        else
            return CommonResult.failed("新增地址失败");
    }

    /**
    * @Description: 获取收货地址列表
    * @Param: [user]
    * @return: com.edu.nju.seckill.common.CommonResult<java.util.List<com.edu.nju.seckill.domain.dto.GetAddressResult>>
    * @Author: whn
    * @Date: 2020/2/3
    */
    @ApiOperation(value = "获取收货地址列表")
    @GetMapping("/address/list")
    public CommonResult<List<GetAddressResult>> getAddress(User user){
        List<GetAddressResult> res = addressService.getAddress(user.getUid());
        if(res.size()>0)
            return CommonResult.success(res,"获取收货地址成功");
        else {
            return CommonResult.failed("您似乎还没有可用的收藏地址");
        }
    }
}
