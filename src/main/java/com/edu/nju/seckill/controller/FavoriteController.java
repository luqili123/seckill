package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.domain.User;
import com.edu.nju.seckill.domain.dto.CurrentUser;
import com.edu.nju.seckill.domain.dto.FavoriteResult;
import com.edu.nju.seckill.service.FavoriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author lql
 * @date 2020/1/11 20:25
 */
@RestController
@Api(tags = "收藏夹控制类")
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @ApiOperation(value = "get参数解析器测试", notes = "testGet")
    @GetMapping("/get")
    public String getTest(User user) {
        return user.toString();
    }

    @ApiOperation(value = "post参数解析器测试", notes = "testPost")
    @PostMapping("/post")
    public String postTest(User user, @RequestParam String string) {
        return user.getName() + string;
    }

    @ApiOperation(value = "添加收藏夹", notes = "addFavorite")
    @PostMapping("")
    public CommonResult<Boolean> addFavorite(CurrentUser currentUser, @RequestBody Map<String, Long> map) {
        Long gid = map.get("gid");
        User user = currentUser.getUser();
        boolean res = favoriteService.addFavorite(user.getUid(), gid);
        return CommonResult.success(res, "添加收藏成功");
    }

    /**
     * @Description: 通过收藏夹表主键fid删除收藏记录
     * @Param: [fid]
     * @return: com.edu.nju.seckill.common.CommonResult<java.lang.String>
     * @Author: whn
     * @Date: 2020/1/29
     */
    @ApiOperation(value = "从收藏夹删除", notes = "addFavorite")
    @DeleteMapping("/{fid}")
    public CommonResult<Boolean> deleteFavorite(@PathVariable int fid) {
        return CommonResult.success(favoriteService.deleteFavorite(fid),"删除成功");
    }

    @ApiOperation(value = "取消收藏")
    @DeleteMapping("/cancel")
    public CommonResult<Boolean> cancelFavorite(CurrentUser currentUser, @RequestParam("gid") Long gid) {
        boolean res = favoriteService.cancelFav(currentUser.getUser().getUid(), gid);
        return CommonResult.success(res, "已取消商品收藏");
    }

    /**
     * @Description: 通过keyword模糊查询收藏的商品信息
     * @Param: [user, keyword]
     * @return: com.edu.nju.seckill.common.CommonResult<com.edu.nju.seckill.domain.dto.FavoriteResult>
     * @Author: whn
     * @Date: 2020/1/30
     */
    @ApiOperation(value = "从收藏夹通过keyword搜索", notes = "searchFavoriteByKeyword")
    @GetMapping(value = {"/list/{keyword}", "/list"})
    public CommonResult<List<FavoriteResult>> searchFavoriteByKeyword(CurrentUser currentUser, @PathVariable(required = false) String keyword) {
        User user = currentUser.getUser();
        List<FavoriteResult> res = favoriteService.searchFavoriteByKeyword(user.getUid(), keyword);
        return CommonResult.success(res);
    }
}
