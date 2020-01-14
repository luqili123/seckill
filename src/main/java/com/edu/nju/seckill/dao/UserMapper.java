package com.edu.nju.seckill.dao;

import com.edu.nju.seckill.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    int deleteByPrimaryKey(Long uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}