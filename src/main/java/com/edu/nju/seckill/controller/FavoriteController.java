package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.domain.User;
import com.edu.nju.seckill.domain.dto.CarouselItems;
import com.edu.nju.seckill.service.FavoriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lql
 * @date 2020/1/11 20:25
 */
@RestController
@Api(tags = "收藏夹控制类")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @ApiOperation(value = "get参数解析器测试",notes = "testGet")
    @GetMapping("/favorite/get")
    public String getTest(User user,  @RequestParam String string){
        return user.getName()+string;
    }

    @ApiOperation(value = "post参数解析器测试",notes = "testPost")
    @PostMapping("/favorite/post")
    public String postTest(User user,  @RequestParam String string){
        return user.getName()+string;
    }
}
