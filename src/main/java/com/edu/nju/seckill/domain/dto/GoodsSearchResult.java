package com.edu.nju.seckill.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("商城首页商品搜索列表_结果页")
public class GoodsSearchResult {
    public String getType_zh() {
        return type_zh;
    }

    public void setType_zh(String type_zh) {
        this.type_zh = type_zh;
    }

    public String getType_en() {
        return type_en;
    }

    public void setType_en(String type_en) {
        this.type_en = type_en;
    }

    public List<GoodsSearchList> getList() {
        return list;
    }

    public void setList(List<GoodsSearchList> list) {
        this.list = list;
    }

    @ApiModelProperty("类型中文")
    String type_zh;
    @ApiModelProperty("类型英文")
    String type_en;

    @ApiModelProperty("商品具体信息")
    List<GoodsSearchList> list;
}
