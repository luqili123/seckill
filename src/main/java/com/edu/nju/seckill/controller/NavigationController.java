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
import java.util.Map;

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
    public CommonResult<List<NavigationResult>> getNavItems() {
        List<NavigationResult> navigationList = navigationService.getAllNavItems();
        return CommonResult.success(navigationList);
    }

    @ApiOperation(value = "获取导航栏条目下的主要商品")
    @GetMapping("/menu/tabItems")
    public CommonResult<?> getNavTableItems() {
        Map<String, List<TableItem>> res = navigationService.getTableItems();
        return CommonResult.success(res);
    }
}
