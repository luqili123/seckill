package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.domain.User;
import com.edu.nju.seckill.service.FavoriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public String getTest( User user){
        return user.toString();
    }

    @ApiOperation(value = "post参数解析器测试",notes = "testPost")
    @PostMapping("/favorite/post")
    public String postTest(User user,  @RequestParam String string){
        return user.getName()+string;
    }

    @ApiOperation(value = "添加收藏夹",notes = "addFavorite")
    @PostMapping("/favorite")
    public CommonResult<String> addFavorite(User user, @RequestParam long gid){
        if(favoriteService.addFavorite(user.getUid(),gid)) {
            return CommonResult.success(null, "添加成功");
        }else {
            return CommonResult.failed("添加失败");
        }
    }
}
