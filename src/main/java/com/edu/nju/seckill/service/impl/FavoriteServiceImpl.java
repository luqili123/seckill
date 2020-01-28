package com.edu.nju.seckill.service.impl;

import com.edu.nju.seckill.dao.FavoriteMapper;
import com.edu.nju.seckill.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lql
 * @date 2020/1/11 20:20
 */
@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteMapper favoriteMapper;

    /**
     * @Description: 添加收藏夹
     * @Param: [uid, gid]
     * @return: boolean
     * @Author: whn
     * @Date: 2020/1/29
     */
    @Override
    public boolean addFavorite(long uid, long gid) {
        if (favoriteMapper.findFavoriteByUidGid(uid, gid) > 0)
            return false;
        else {
            int res = favoriteMapper.addFavorite(uid, gid);
            if (res > 0)
                return true;
            else
                return false;
        }
    }
}
