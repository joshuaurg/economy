package com.fishman.app.economy.model;

import java.io.Serializable;

/**
 * Created by hema on 16/9/21.
 */
public class Category extends BaseModel implements Serializable{
    private Integer type;
    private Integer subType;
    private String title;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSubType() {
        return subType;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
