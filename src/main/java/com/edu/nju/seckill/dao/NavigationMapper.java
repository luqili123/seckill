package com.edu.nju.seckill.dao;

import com.edu.nju.seckill.domain.Navigation;

import com.edu.nju.seckill.domain.dto.NavigationDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NavigationMapper {
    
    int deleteByPrimaryKey(Integer nid);

    int insert(Navigation record);

    int insertSelective(Navigation record);

    Navigation selectByPrimaryKey(Integer nid);

    int updateByPrimaryKeySelective(Navigation record);

    int updateByPrimaryKey(Navigation record);

    List<NavigationDto> selectAll();

    String selectByType(String type);
}