package com.fishman.app.economy.service;

import com.fishman.app.economy.model.User;

/**
 * Created by hema on 16/9/21.
 */
public interface UserService {

    User getUserById(long id);

    User getUser(User u);

    void save(User u);
}
