package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.domain.SeckillGoods;
import com.edu.nju.seckill.service.NavigationService;
import com.edu.nju.seckill.service.SeckillGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author lql
 * @date 2020/1/11 20:26
 */
@RestController
@Api(tags = "秒杀商品控制类")
public class SeckillGoodsController {

    @Autowired
    private SeckillGoodsService seckillGoodsService;

    @Autowired
    private NavigationService navigationService;

//    @GetMapping("/seckill/slide")
//    public CommonResult<?>  getSecGoodsItems(){
//        return null;
//    }

    @ApiOperation(value = "添加秒杀商品")
    @PostMapping("/seckill")
    public CommonResult<?> addSeckillGoods(@RequestBody @Validated SeckillGoods seckillGoods
            , BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            List<ObjectError> list = bindingResult.getAllErrors();
            String message=null;
            for (ObjectError error : list) {
                if(!error.getDefaultMessage().equals("")){
                    message=message+" "+error.getDefaultMessage();
                }
            }
            System.out.println("错误信息："+message);
            return CommonResult.validateFailed(message);
        }
        //若navName为空，则查询导航表，填入对应名字
        if(seckillGoods.getNavName()==null||seckillGoods.getNavName().equals("")) {
            String name = navigationService.getNameByType(seckillGoods.getNavType());
            if (name != null) {
                seckillGoods.setNavName(name);
            } else {
                return CommonResult.failed("商品类别不存在");
            }
        }
        seckillGoods.setDisplayTime(new Date(System.currentTimeMillis()));
        seckillGoodsService.insertSeckillGoods(seckillGoods);
        return CommonResult.success(null);
    }
}
