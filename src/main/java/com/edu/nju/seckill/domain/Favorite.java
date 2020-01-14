package com.edu.nju.seckill.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lqllq
 */
@ApiModel("收藏夹对象实体")
public class Favorite implements Serializable {
    @ApiModelProperty(value = "收藏夹编号",required = true)
    private Integer fid;
    @ApiModelProperty(value = "被收藏的商品编号",required = true)
    private Long gid;
    @ApiModelProperty(value = "用户编号",required = true)
    private Long uid;
    @ApiModelProperty(value = "收藏夹创建时间",required = true)
    private Date createTime;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}