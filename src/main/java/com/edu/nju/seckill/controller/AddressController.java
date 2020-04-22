package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.domain.Address;
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
 * @author beverly lql
 *
 * @date 2020/1/11 20:25
 */
@RestController
@Api(tags = "收货地址控制类")
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
    * @Description: 新增收货地址 并对邮编和电话号码进行验证
    * @Param: [user, param, bindingResult]
    * @return: com.edu.nju.seckill.common.CommonResult
    * @Author: whn
    * @Date: 2020/2/3
    */
    @ApiOperation(value = "添加收货地址")
    @PostMapping("/add")
    public CommonResult<Boolean> addAddress(CurrentUser currentUser, @Validated @RequestBody AddressOperationParam param){
        User user = currentUser.getUser();
        boolean res = addressService.addAddress(user.getUid(), param);
        return CommonResult.success(res,"地址添加成功");
    }

    /**
    * @Description: 获取收货地址列表
    * @Param: [user]
    * @return: com.edu.nju.seckill.common.CommonResult<java.util.List<com.edu.nju.seckill.domain.dto.GetAddressResult>>
    * @Author: whn
    * @Date: 2020/2/3
    */
    @ApiOperation(value = "获取收货地址列表")
    @GetMapping("/list")
    public CommonResult<List<GetAddressResult>> getAddress(CurrentUser currentUser){
        User user=currentUser.getUser();
        List<GetAddressResult> res = addressService.getAddress(user.getUid());
        return CommonResult.success(res);
    }

    @ApiOperation("根据id获取收货地址")
    @GetMapping("/{aid}")
    public CommonResult<Address> getAddressById(CurrentUser currentUser, @PathVariable("aid") Integer aid) {
        Address address = addressService.getAddressById(currentUser.getUser().getUid(), aid);
        return CommonResult.success(address);
    }

    /**
    * @Description: 修改收货地址
    * @Param: [user, aid, param, bindingResult]
    * @return: com.edu.nju.seckill.common.CommonResult
    * @Author: whn
    * @Date: 2020/2/3
    */
    @ApiOperation(value = "修改收货地址")
    @PostMapping("/update/{aid}")
    public CommonResult<Boolean> updateAddress(CurrentUser currentUser,@PathVariable Integer aid,@Validated @RequestBody AddressOperationParam param){
        User user=currentUser.getUser();
        boolean res=addressService.updateAddress(aid,user.getUid(),param);
        return CommonResult.success(res,"修改地址成功");
    }

    /**
    * @Description: 删除收货地址
    * @Param: [user, aid]
    * @return: com.edu.nju.seckill.common.CommonResult
    * @Author: whn
    * @Date: 2020/2/3
    */
    @ApiOperation(value="删除收货地址")
    @DeleteMapping("/deleteaddress/{aid}")
    public CommonResult<Boolean> deleteAddress(CurrentUser currentUser,@PathVariable Integer aid){
        User user=currentUser.getUser();
        boolean res = addressService.deleteAddress(aid,user.getUid());
        return CommonResult.success(res,"删除地址成功");
    }

    /**
    * @Description: 修改默认收货地址
    * @Param: [currentUser, aid]
    * @return: com.edu.nju.seckill.common.CommonResult
    * @Author: whn
    * @Date: 2020/2/7
    */
    @ApiOperation(value = "设置默认地址")
    @PutMapping("/default")
    public CommonResult<Boolean> updateDefaultAddress(CurrentUser currentUser,
                                                     @RequestBody Map<String, Integer> map){
        Integer aid=map.get("aid");
        User user=currentUser.getUser();
        Long uid=user.getUid();
        boolean res=addressService.updateDefaultAddress(uid,aid);
        return CommonResult.success(res,"修改默认地址成功");
    }
}
