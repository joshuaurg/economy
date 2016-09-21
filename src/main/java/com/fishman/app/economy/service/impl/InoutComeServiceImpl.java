package com.fishman.app.economy.service.impl;

import com.fishman.app.economy.dao.InoutComeDao;
import com.fishman.app.economy.dao.UserDao;
import com.fishman.app.economy.model.InoutCome;
import com.fishman.app.economy.model.User;
import com.fishman.app.economy.service.InoutComeService;
import com.fishman.app.economy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hema on 16/9/21.
 */
@Service
@Transactional
public class InoutComeServiceImpl implements InoutComeService {
    @Autowired
    InoutComeDao inoutComeDao;
    @Override
    public void saveIncome(InoutCome inoutCome) {
        inoutComeDao.save(inoutCome);
    }
}
