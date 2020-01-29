package com.edu.nju.seckill.service;

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
}
