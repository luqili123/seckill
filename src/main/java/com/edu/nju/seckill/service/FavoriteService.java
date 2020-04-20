package com.edu.nju.seckill.service;

import com.edu.nju.seckill.domain.dto.FavoriteResult;

import java.rmi.server.UID;
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

    /**
     * 是否收藏过该商品
     * @param uid 用户id
     * @param gid 收藏商品id
     * @return true/false
     */
    boolean hasFavorite(Long uid, Long gid);

    /**
     * 根据用户id和商品id，取消该商品收藏
     * @param uid
     * @param gid
     * @return
     */
    boolean cancelFav(Long uid, Long gid);
}
