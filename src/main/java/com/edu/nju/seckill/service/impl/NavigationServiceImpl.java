package com.edu.nju.seckill.service.impl;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.dao.NavigationMapper;
import com.edu.nju.seckill.domain.Navigation;
import com.edu.nju.seckill.domain.dto.NavigationResult;
import com.edu.nju.seckill.domain.dto.TableItem;
import com.edu.nju.seckill.domain.dto.TableItems;
import com.edu.nju.seckill.exception.DataBaseException;
import com.edu.nju.seckill.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lql
 * @date 2020/1/20 12:02
 */
@Service
public class NavigationServiceImpl implements NavigationService {

    @Autowired
    private NavigationMapper navigationMapper;

    @Override
    public List<NavigationResult> getAllNavItems() {
        List<NavigationResult> navigationResults = navigationMapper.selectAll();
        if (navigationResults.size() > 0)
            return navigationResults;
        throw new DataBaseException("暂时还没有设置导航栏哦O(∩_∩)O");
    }

    @Override
    public String getNameByType(String type) {
        String name = navigationMapper.selectByType(type);
        if (!"".equals(name)) {
            return name;
        }
        return null;
    }

    @Override
    public String[] getNameAndTypeByNid(Integer nid) {
        Navigation navigation = navigationMapper.selectByPrimaryKey(nid);
        if (navigation != null) {
            String[] nameAndType = {navigation.getName(), navigation.getType()};
            return nameAndType;
        }
        return null;
    }

    @Override
    public Map<String, List<TableItem>> getTableItems() {
        List<TableItem> tableItems = navigationMapper.selectTableItems();
        if (tableItems.size() > 0) {
            Map<String, List<TableItem>> result = new HashMap<>();
            result.put("tableItems", tableItems);
            return result;
        }
        throw new DataBaseException("导航栏下暂时没有商品呢o(*￣▽￣*)o");
    }


}
