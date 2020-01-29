package com.edu.nju.seckill.service;

import com.edu.nju.seckill.domain.dto.FavoriteResult;

import java.util.List;

/**
 * @author lql
 * @date 2020/1/11 20:19
 */
public interface FavoriteService {
    /**
    * @Description: 添加收藏夹
    * @Param: [uid, gid]
    * @return: boolean
    * @Author: whn
    * @Date: 2020/1/29
    */
    boolean addFavorite(long uid,long gid);

    /*
        删除收藏记录
     */
    Boolean deleteFavorite(int fid);

    /*
        通过keyword搜索收藏记录
     */
    List<FavoriteResult> searchFavoriteByKeyword(long uid, String keyword);
}
