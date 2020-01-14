package com.edu.nju.seckill.dao;

import com.edu.nju.seckill.domain.Navigation;

import org.springframework.stereotype.Repository;

@Repository
public interface NavigationMapper {
    int deleteByPrimaryKey(Integer nid);

    int insert(Navigation record);

    int insertSelective(Navigation record);

    Navigation selectByPrimaryKey(Integer nid);

    int updateByPrimaryKeySelective(Navigation record);

    int updateByPrimaryKey(Navigation record);
}