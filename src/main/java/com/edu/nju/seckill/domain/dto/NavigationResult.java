package com.edu.nju.seckill.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author lql
 * @date 2020/1/20 13:51
 */
@ApiModel("导航栏dto")
public class NavigationResult implements Serializable {
    @ApiModelProperty(value = "导航栏名称")
    private String name;

    @ApiModelProperty(value = "导航栏类型")
    private String type;


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

    @Override
    public String toString() {
        return "NavigationDto{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
