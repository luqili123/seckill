package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.domain.User;
import com.edu.nju.seckill.domain.dto.AddressOperationParam;
import com.edu.nju.seckill.domain.dto.CurrentUser;
import com.edu.nju.seckill.domain.dto.GetAddressResult;
import com.edu.nju.seckill.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public CommonResult<String> addAddress(CurrentUser currentUser, @Validated @RequestBody AddressOperationParam param, BindingResult bindingResult){
        User user=currentUser.getUser();
        if(bindingResult.hasErrors()){
            String errorInfo="";
            List<ObjectError> list = bindingResult.getAllErrors();
            for (ObjectError error : list) {
                System.out.println(error.toString());
                errorInfo+=error.toString();
            }
            return CommonResult.validateFailed(errorInfo);
        }
        if(addressService.addAddress(user.getUid(),param.getPostcode(),param.getAddress(),param.getReceiver_name(),param.getReceiver_phone()))
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
    public CommonResult<List<GetAddressResult>> getAddress(CurrentUser currentUser){
        User user=currentUser.getUser();
        List<GetAddressResult> res = addressService.getAddress(user.getUid());
        if(res.size()>0)
            return CommonResult.success(res,"获取收货地址成功");
        else {
            return CommonResult.failed("您似乎还没有可用的收藏地址");
        }
    }


    /**
    * @Description: 修改收货地址
    * @Param: [user, aid, param, bindingResult]
    * @return: com.edu.nju.seckill.common.CommonResult<java.lang.String>
    * @Author: whn
    * @Date: 2020/2/3
    */
    @ApiOperation(value = "修改收货地址")
    @PostMapping("/address/update/{aid}")
    public CommonResult<String> updateAddress(CurrentUser currentUser,@PathVariable Integer aid,@Validated @RequestBody AddressOperationParam param,BindingResult bindingResult){
        User user=currentUser.getUser();
        if(bindingResult.hasErrors()){
            String errorInfo="";
            List<ObjectError> list = bindingResult.getAllErrors();
            for (ObjectError error : list) {
                System.out.println(error.toString());
                errorInfo+=error.toString();
            }
            return CommonResult.validateFailed(errorInfo);
        }
        if(addressService.updateAddress(aid,user.getUid(),param.getPostcode(),param.getAddress(),param.getReceiver_name(),param.getReceiver_phone()))
            return CommonResult.success(null,"修改成功");
        else
            return CommonResult.failed("修改失败");
    }

    /**
    * @Description: 删除收货地址
    * @Param: [user, aid]
    * @return: com.edu.nju.seckill.common.CommonResult<java.lang.String>
    * @Author: whn
    * @Date: 2020/2/3
    */
    @ApiOperation(value="删除收货地址")
    @DeleteMapping("/address/deleteaddress/{aid}")
    public CommonResult<String> deleteAddress(CurrentUser currentUser,@PathVariable Integer aid){
        User user=currentUser.getUser();
        if(addressService.deleteAddress(aid,user.getUid()))
            return CommonResult.success(null,"删除成功");
        else
            return CommonResult.failed("删除失败或不存在该地址id");
    }

    /**
    * @Description: 修改默认收货地址
    * @Param: [currentUser, aid]
    * @return: com.edu.nju.seckill.common.CommonResult<java.lang.String>
    * @Author: whn
    * @Date: 2020/2/7
    */
    @ApiOperation(value = "设置默认地址")
    @PutMapping("/address/default")
    public CommonResult<String> updateDefaultAddress(CurrentUser currentUser,
                                                     @RequestBody Map<String, Integer> map){
        Integer aid=map.get("aid");
        User user=currentUser.getUser();
        Long uid=user.getUid();
        if(addressService.updateDefaultAddress(uid,aid))
            return CommonResult.success("修改成功");
        else
            return CommonResult.failed("修改失败");
    }
}
