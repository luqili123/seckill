package com.edu.nju.seckill.dao;

import com.edu.nju.seckill.domain.dto.CarouselItems;
import com.edu.nju.seckill.domain.Goods;

import com.edu.nju.seckill.domain.dto.GoodsDetailResult;
import com.edu.nju.seckill.domain.dto.GoodsListResult;
import com.edu.nju.seckill.domain.dto.GoodsSearchResult;
import com.edu.nju.seckill.domain.dto.SecKillGoodsDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMapper {
    int deleteByPrimaryKey(Long gid);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long gid);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<CarouselItems> selectHotProductCarousel();

    List<GoodsListResult> getGoodsList(@Param("typeName") String typeName,@Param("orderby") String orderby,
                                       @Param("keyword") String keyword);

    GoodsDetailResult getGoodDetail(@Param("gid") Long gid);

    List<GoodsSearchResult> searchGoodsForIndex(@Param("keyword") String keyword);

    List<String> getGoodsIndexTips(@Param("keyword") String keyword);

    int updateGoodsCount(Long gid, Integer num);
}