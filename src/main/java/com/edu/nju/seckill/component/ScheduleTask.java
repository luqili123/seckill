package com.edu.nju.seckill.component;

import com.edu.nju.seckill.domain.dto.SeckillGoodsList;
import com.edu.nju.seckill.domain.dto.SeckillGoodsResult;
import com.edu.nju.seckill.service.OrderService;
import com.edu.nju.seckill.service.SeckillGoodsService;
import com.edu.nju.seckill.utils.RedisUtil;
import org.omg.CORBA.OBJ_ADAPTER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 定时器任务
 * @author lql
 * @date 2020/4/20 14:41
 */

@Component
public class ScheduleTask {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SeckillGoodsService seckillGoodsService;
    @Autowired
    private OrderService orderService;

    private static Logger logger= LoggerFactory.getLogger(ScheduleTask.class);

    private final String GOODPREFIX="secGood_";
    private final String ORDERPREFIX="secOrder_";
    /***
     * 定时执行，将新一期开始秒杀的商品放入redis中.
     *cron ="0 0 0/1 * * *":表示每天0点开始，每隔一个小时执行一次
     */
    @Scheduled(cron ="0 0 0/1 * * *")
    public void getStartSecGoodsInfo2Redis(){
        logger.info("秒杀商品信息定时写入Redis任务开启!");
        //查询已经开始的秒杀商品
        SeckillGoodsList seckillGoodsLists = seckillGoodsService.getStartSeckillGoods();
        if(seckillGoodsLists!=null) {
            //存储每个秒杀商品详细信息
            Map<String,Object> goodsInfo = new HashMap<>();
            List<SeckillGoodsResult> goodsList = seckillGoodsLists.getSeckillGoodsList();
            for (SeckillGoodsResult seckillGoodsResult : goodsList) {
                goodsInfo.put("sgid",seckillGoodsResult.getSgid());
                goodsInfo.put("name",seckillGoodsResult.getGoods_name());
                goodsInfo.put("price",seckillGoodsResult.getGoods_price());
                goodsInfo.put("seckill_price",seckillGoodsResult.getSeckill_price());
                goodsInfo.put("description",seckillGoodsResult.getDesc());
                goodsInfo.put("image",seckillGoodsResult.getImgUrl());
                goodsInfo.put("count",seckillGoodsResult.getCount());
                goodsInfo.put("remain_count",seckillGoodsResult.getRemain_count());
                goodsInfo.put("start_time",seckillGoodsLists.getStartTime());
                goodsInfo.put("end_time",seckillGoodsLists.getEndTime());
                //将开始秒杀的商品信息存入redis
                redisUtil.hmset(GOODPREFIX+seckillGoodsResult.getSgid(),goodsInfo);
            }
            logger.info("Redis载入新一轮秒杀商品！");
//            System.out.println(goodsInfo.toString());
        }
    }

    /***
     * 定时执行，将上一期秒杀商品结果写回秒杀商品表，再将订单写入订单表
     */
    @Scheduled(cron ="0 0 0/1 * * *")
    public void updateEndSecGoodsInfo2SQL(){
        logger.info("秒杀商品信息定时写出到MySql任务开启!");
        logger.info("秒杀订单定时写出到MySql任务开启!");
        //将上一期秒杀商品信息写回数据库（主要是remain_count的变化)
        Set<String> goodsId=redisUtil.getByPattern(GOODPREFIX+"*");
        if(goodsId!=null){
            //更新数据
            for (String s : goodsId) {
                Object sgid=redisUtil.hget(s,"sgid");
                Object remainCount=redisUtil.hget(s,"remain_count");
                seckillGoodsService.updateSeckillGoodsRemainCount((int)remainCount,(int)sgid);
            }
            //删除redis中的数据
            redisUtil.del(goodsId);
        }
        logger.info("Redis秒杀商品信息写入成功！");
        //将上一期产生的秒杀订单更新到数据库
        Set<String> secOrder=redisUtil.getByPattern(ORDERPREFIX+"*");
        //更新数据
        if(secOrder!=null){
            for (String s : secOrder) {
                Map<Object,Object> orderInfo=redisUtil.hmget(s);
                if(orderService.insertSecOrder(orderInfo)){
                    logger.info("秒杀订单"+s+"写入成功！");
                    redisUtil.del(s);
                }else{
                    logger.debug("秒杀订单"+s+"写入失败！");
                }
            }

        }
    }


}
