package com.fishman.app.economy.model;

import java.io.Serializable;

/**
 * Created by hema on 16/9/21.
 */
public class User extends BaseModel implements Serializable{

    private String username;
    private String password;
    private Integer role;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
