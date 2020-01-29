package com.edu.nju.seckill.service.impl;

import com.edu.nju.seckill.dao.NavigationMapper;
import com.edu.nju.seckill.domain.Navigation;
import com.edu.nju.seckill.domain.dto.NavigationResult;
import com.edu.nju.seckill.domain.dto.TableItem;
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
    public List<NavigationResult> getAllNavItems() {

        return navigationMapper.selectAll() ;
    }

    @Override
    public String getNameByType(String type) {
        String name=navigationMapper.selectByType(type);
        if(!"".equals(name)){
            return name;
        }
        return null;
    }

    @Override
    public String[] getNameAndTypeByNid(Integer nid) {
        Navigation navigation=navigationMapper.selectByPrimaryKey(nid);
        if(navigation!=null){
            String[] nameAndType={navigation.getName(),navigation.getType()};
            return nameAndType;
        }
        return null;
    }

    @Override
    public List<TableItem> getTableItems() {
        List<TableItem> tableItems= navigationMapper.selectTableItems();
        if(tableItems!=null&&tableItems.size()>0){
            return tableItems;
        }
        return null;
    }


}
