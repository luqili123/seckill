package com.edu.nju.seckill.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/***
 *
 * @author lql
 */
@ApiModel("导航栏对象")
public class Navigation implements Serializable {

    @ApiModelProperty(value = "导航栏编号")
    private Integer nid;

    @ApiModelProperty(value = "导航栏名称")
    private String name;

    @ApiModelProperty(value = "导航栏类型")
    private String type;

    @ApiModelProperty(value = "导航栏优先级")
    private Integer sort;

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "Navigation{" +
                "nid=" + nid +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", sort=" + sort +
                '}';
    }
}