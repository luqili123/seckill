package com.edu.nju.seckill.dao;

import com.edu.nju.seckill.domain.Favorite;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(Favorite record);

    int insertSelective(Favorite record);

    Favorite selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Favorite record);

    int updateByPrimaryKey(Favorite record);
}