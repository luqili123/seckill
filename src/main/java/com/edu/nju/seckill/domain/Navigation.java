package com.edu.nju.seckill.domain;


import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * @author lqllq
 */
@ApiModel("导航栏对象")
public class Navigation implements Serializable {
    private Integer nid;

    private String name;

    private String type;

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
}