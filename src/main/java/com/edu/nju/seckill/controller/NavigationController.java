package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.domain.dto.NavigationResult;
import com.edu.nju.seckill.domain.dto.TableItem;
import com.edu.nju.seckill.domain.dto.TableItems;
import com.edu.nju.seckill.service.NavigationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    public CommonResult<List<NavigationResult>> getNavItems(){
        List<NavigationResult> navigationList=navigationService.getAllNavItems();
        if(navigationList!=null&&navigationList.size()>0){
            return CommonResult.success(navigationList);
        }else {
            return CommonResult.failed("没有导航栏");
        }
    }
    @ApiOperation(value = "获取导航栏条目下的主要商品")
    @GetMapping("/menu/tabItems")
    public CommonResult<?> getNavTableItems(){
        List<TableItem> tableItemList=navigationService.getTableItems();
        if(tableItemList!=null){
            TableItems tableItems=new TableItems(tableItemList);
            return CommonResult.success(tableItems);
        }
        return CommonResult.failed("没有商品！") ;

    }

}
