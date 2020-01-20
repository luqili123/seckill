package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.domain.Navigation;
import com.edu.nju.seckill.domain.dto.NavigationDto;
import com.edu.nju.seckill.service.NavigationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lql
 * @date 2020/1/20 12:32
 */
@RestController
@Api(tags = "导航栏实体类")
public class NavigationController {

    @Autowired
    NavigationService navigationService;

    @ApiOperation(value = "获取所有导航栏信息")
    @GetMapping("/menu/navItems")
    public CommonResult<List<NavigationDto>> getNavItems(){
        List<NavigationDto> navigationList=navigationService.getAllNavItems();
        if(navigationList!=null&&navigationList.size()>0){
            return CommonResult.success(navigationList);
        }else {
            return CommonResult.failed("没有导航栏");
        }
    }


}
