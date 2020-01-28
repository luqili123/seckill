package com.edu.nju.seckill.dao;

import com.edu.nju.seckill.domain.Favorite;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(Favorite record);

    int insertSelective(Favorite record);

    Favorite selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Favorite record);

    int updateByPrimaryKey(Favorite record);

    int addFavorite(@Param("uid") long uid, @Param("gid") long gid);

    /**
    * @Description: 通过uid gid查询收藏夹内是否已有该商品
    * @Param: [uid, gid]
    * @return: int
    * @Author: whn
    * @Date: 2020/1/29
    */
    int findFavoriteByUidGid(@Param("uid") long uid, @Param("gid") long gid);
}