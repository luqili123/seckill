package com.edu.nju.seckill.service;

import com.edu.nju.seckill.domain.dto.NavigationResult;
import com.edu.nju.seckill.domain.dto.TableItem;

import java.util.List;
import java.util.Map;

/**
 * @author lql
 * @date 2020/1/20 12:02
 */
public interface NavigationService {

    public List<NavigationResult> getAllNavItems();

    public String getNameByType(String type);

    String[] getNameAndTypeByNid(Integer nid);

    /***
     * 获取获取所有商品的信息，并按类型分组
     * @return
     */
    Map<String, List<TableItem>> getTableItems();
}
