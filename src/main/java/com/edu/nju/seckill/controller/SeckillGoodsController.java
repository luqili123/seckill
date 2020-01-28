package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.domain.Goods;
import com.edu.nju.seckill.domain.SeckillGoods;
import com.edu.nju.seckill.domain.dto.SeckillGoodsResult;
import com.edu.nju.seckill.service.GoodsService;
import com.edu.nju.seckill.service.NavigationService;
import com.edu.nju.seckill.service.SeckillGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private GoodsService goodsService;

    @ApiOperation(value = "秒杀商品列表")
    @GetMapping("/seckill/list")
    public CommonResult<?> getSeckillGoods() {
        List<SeckillGoodsResult> seckillGoodsList = seckillGoodsService.getSeckillList();
        if (seckillGoodsList != null) {
            return CommonResult.success(seckillGoodsList);
        } else {
            return CommonResult.failed("没有秒杀商品");
        }
    }

    @ApiOperation(value = "添加秒杀商品")
    @PostMapping("/seckill")
    public CommonResult<?> addSeckillGoods(@RequestParam Long gid) {
        //根据gid对象，查询数据库，验证goods是否存在
        Goods goods=goodsService.getGoodsByGid(gid);
        if (goods!=null) {
            SeckillGoods seckillGoods=seckillGoodsService.getSeckillBySgid(gid);
            if(seckillGoods==null){
                //存在goods对象，seckillgoods对象不存在，则将该对象信息添加至秒杀商品表
                //1.根据nid，查询商品分类的名称和类型
                String[] navNameAndType = navigationService.getNameAndTypeByNid(goods.getNid());
                if (navNameAndType == null) {
                    return CommonResult.failed("商品分类不存在!");
                }
                //2.给秒杀商品对象赋值
                seckillGoods = new SeckillGoods(goods.getGid(),
                        goods.getName(), goods.getPrice(), navNameAndType[0],
                        navNameAndType[1], goods.getDescription(), goods.getImage(),
                        goods.getCount(), goods.getCount(), null, null, new Date(System.currentTimeMillis()));
                if (seckillGoodsService.insertSeckillGoods(seckillGoods)) {
                    return CommonResult.success("秒杀商品创建成功！");
                } else {
                    return CommonResult.failed("秒杀商品创建失败！");
                }

            }else {
                return CommonResult.failed("该商品已经是秒杀商品");
            }

        } else {
            return CommonResult.failed("商品已经下架或商品不存在！");
        }
    }
}
