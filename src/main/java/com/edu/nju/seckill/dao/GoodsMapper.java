package com.edu.nju.seckill.dao;

import com.edu.nju.seckill.domain.dto.CarouselItems;
import com.edu.nju.seckill.domain.Goods;

import com.edu.nju.seckill.domain.dto.GoodsListResult;
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

    List<GoodsListResult> getGoodsList(@Param("type") String type, @Param("order") String orderby, @Param("keyword") String keyword);

}