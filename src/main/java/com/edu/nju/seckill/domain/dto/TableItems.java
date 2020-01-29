package com.edu.nju.seckill.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author lql
 * @date 2020/1/29 14:00
 */
@ApiModel(value = "所有导航栏条目下的所有商品对象")
public class TableItems implements Serializable {

    @ApiModelProperty(value = "所有商品列表")
    private List<TableItem> tableItems;

    public TableItems(List<TableItem> tableItems) {
        this.tableItems = tableItems;
    }

    public List<TableItem> getTableItems() {
        return tableItems;
    }

    public void setTableItems(List<TableItem> tableItems) {
        this.tableItems = tableItems;
    }

    @Override
    public String toString() {
        return "TableItems{" +
                "tableItems=" + tableItems +
                '}';
    }
}
