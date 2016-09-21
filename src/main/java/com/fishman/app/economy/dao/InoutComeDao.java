package com.fishman.app.economy.dao;

import com.fishman.app.economy.model.InoutCome;
import com.fishman.app.economy.model.User;

import java.util.List;

/**
 * Created by hema on 16/9/21.
 */
public interface InoutComeDao {
    void save(InoutCome inoutCome);

    List<InoutCome> getInoutComeList(int type);
}
