package com.fishman.app.economy.service.impl;

import com.fishman.app.economy.dao.UserDao;
import com.fishman.app.economy.model.User;
import com.fishman.app.economy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hema on 16/9/21.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    public User getUserById(long id) {
        return userDao.getUserByID(id);
    }

    public User getUser(User u) {
        return userDao.getUser(u);
    }

    public void save(User u) {
        userDao.insert(u);
    }
}
