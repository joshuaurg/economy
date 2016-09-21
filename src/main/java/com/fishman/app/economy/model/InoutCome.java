package com.fishman.app.economy.model;

import com.fasterxml.jackson.databind.deser.Deserializers;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hema on 16/9/21.
 */
public class InoutCome extends BaseModel implements Serializable{

   private Double money;
   private Long uid;
   private Integer typeId;
   private Date startDate;
   private Date endDate;
   private Integer  status;

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
