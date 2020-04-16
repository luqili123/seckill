package com.edu.nju.seckill.dao;

import com.edu.nju.seckill.domain.User;
import com.edu.nju.seckill.domain.dto.UserInfo;
import com.edu.nju.seckill.domain.dto.UserParam;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    int deleteByPrimaryKey(Long uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long uid);

    User selectByPhone(String phone);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int updatePwd(Long uid, String password);

    int updatePwd2(UserParam userParam);

    int updateInfo(UserInfo userInfo);

    int updateUser(Long uid, String name, String email);
}