package com.fishman.app.economy.dao;

import com.fishman.app.economy.model.User;

/**
 * Created by hema on 16/9/21.
 */
public interface UserDao {
    User getUserByID(long id);

    User getUser(User u);

    void insert(User u);
}
