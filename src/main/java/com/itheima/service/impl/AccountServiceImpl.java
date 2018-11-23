package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import com.itheima.utils.TransactionManager;

public class AccountServiceImpl implements IAccountService {
    private IAccountDao iAccountDao;
    private TransactionManager transactionManager;


    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setiAccountDao(IAccountDao iAccountDao) {
        this.iAccountDao = iAccountDao;
    }

    public Account findByName(String name) {
        Account byName = iAccountDao.findByName(name);
        return byName;
    }


    public Account findById(Integer id) {
        Account byId = iAccountDao.findById(id);
        return byId;
    }

    public void UpdateAccount(Account account) {
        iAccountDao.UpdateAccount(account);
    }

    public void transfer(String resourceName, String targetName, Float money) {
           Account resourceAccount = iAccountDao.findByName(resourceName);
           //查詢轉入賬戶
           Account targetAccount = iAccountDao.findByName(targetName);
           //設置轉出金額
           resourceAccount.setMoney(resourceAccount.getMoney() - money);
           //設置轉入金額
           targetAccount.setMoney(targetAccount.getMoney() + money );

           iAccountDao.UpdateAccount(resourceAccount);
           int u = 6/0;
           iAccountDao.UpdateAccount(targetAccount);

    }
}
