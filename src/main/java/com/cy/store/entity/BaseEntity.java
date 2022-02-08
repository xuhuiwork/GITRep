package com.cy.store.entity;

import java.io.Serializable;
import java.util.Date;
//实现Serializable接口  因为在网络中要以流的形式输出出来
public class BaseEntity implements Serializable {

    private String createdUser;//'日志-创建人',
    private Date createdTime;//DATETIME COMMENT '日志-创建时间',
    private String modifiedUser;// VARCHAR(20) COMMENT '日志-最后修改执行人',
    private Date modifiedTime;// DATETIME COMMENT '日志-最后修改时间',

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "createdUser='" + createdUser + '\'' +
                ", createdTime=" + createdTime +
                ", modifiedUser='" + modifiedUser + '\'' +
                ", modifiedTime=" + modifiedTime +
                '}';
    }
}
