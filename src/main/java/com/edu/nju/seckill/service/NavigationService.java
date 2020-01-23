package com.edu.nju.seckill.service;

import com.edu.nju.seckill.domain.Navigation;
import com.edu.nju.seckill.domain.dto.NavigationDto;

import java.util.List;

/**
 * @author lql
 * @date 2020/1/20 12:02
 */
public interface NavigationService {

    public List<NavigationDto> getAllNavItems();

    public String getNameByType(String type);
}
