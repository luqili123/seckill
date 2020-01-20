package com.edu.nju.seckill.service.impl;

import com.edu.nju.seckill.dao.NavigationMapper;
import com.edu.nju.seckill.domain.Navigation;
import com.edu.nju.seckill.domain.dto.NavigationDto;
import com.edu.nju.seckill.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lql
 * @date 2020/1/20 12:02
 */
@Service
public class NavigationServiceImpl implements NavigationService {

    @Autowired
    private NavigationMapper navigationMapper;

    @Override
    public List<NavigationDto> getAllNavItems() {

        return navigationMapper.selectAll() ;
    }
}
