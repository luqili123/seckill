package com.edu.nju.seckill.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author lql
 * @date 2020/1/29 13:17
 */
@ApiModel(value = "单一导航栏条目下的所有商品对象")
public class TableItem implements Serializable {
    @ApiModelProperty(value = "商品的类型")
    private String type;
    @ApiModelProperty(value = "商品的其他信息，包括gid，name，imageUrl，price")
    private List<GoodsResult> goodsResults;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<GoodsResult> getGoodsResults() {
        return goodsResults;
    }

    public void setGoodsResults(List<GoodsResult> goodsResults) {
        this.goodsResults = goodsResults;
    }

    @Override
    public String toString() {
        return "TableItem{" +
                "type='" + type + '\'' +
                ", goodsResults=" + goodsResults +
                '}';
    }
}
