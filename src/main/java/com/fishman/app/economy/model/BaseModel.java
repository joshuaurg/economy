package com.fishman.app.economy.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hema on 16/9/21.
 */
public class BaseModel implements Serializable{
    private long id;
    private Date creTime;
    private Date updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreTime() {
        return creTime;
    }

    public void setCreTime(Date creTime) {
        this.creTime = creTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
