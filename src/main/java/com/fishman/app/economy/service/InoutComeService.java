package com.fishman.app.economy.service;

import com.fishman.app.economy.model.InoutCome;

import java.util.List;

/**
 * Created by hema on 16/9/21.
 */
public interface InoutComeService {

    void saveIncome(InoutCome inoutCome);

    List<InoutCome> getInoutComeList(int type);
}
