package com.edu.nju.seckill.service.impl;

import com.edu.nju.seckill.dao.FavoriteMapper;
import com.edu.nju.seckill.domain.dto.FavoriteResult;
import com.edu.nju.seckill.exception.DataBaseException;
import com.edu.nju.seckill.exception.FavExistException;
import com.edu.nju.seckill.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            throw new FavExistException("该商品已被您收藏啦^o^");
        return favoriteMapper.addFavorite(uid, gid) == 1;
    }

    /**
    * @Description: 通过收藏夹主键fid删除收藏记录
    * @Param: [fid]
    * @return: java.lang.Boolean
    * @Author: whn
    * @Date: 2020/1/29
    */
    @Override
    public Boolean deleteFavorite(int fid) {
        if (favoriteMapper.deleteFavorite(fid) == 1) return true;
        throw new DataBaseException("数据库异常了");
    }

    @Override
    public List<FavoriteResult> searchFavoriteByKeyword(long uid, String keyword) {
        return favoriteMapper.searchFavoriteByKeyword(uid,keyword);
    }

    @Override
    public boolean hasFavorite(Long uid, Long gid) {
        return favoriteMapper.getFavorite(uid, gid) != null;
    }

    @Override
    public boolean cancelFav(Long uid, Long gid) {
        if (favoriteMapper.deleteFavByGid(uid, gid) == 1)
            return true;
        throw new DataBaseException("┗|｀O′|┛ 嗷~~，网络异常，请稍后重试");
    }
}
