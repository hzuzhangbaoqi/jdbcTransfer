package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.runner.Runner;

import java.sql.SQLException;
import java.util.List;

public class AccountDaoImpl implements IAccountDao {

    private QueryRunner runner;

    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    public Account findByName(String name) {
        List<Account> accounts = null;
        try {
            accounts =  runner.query(connectionUtils.getThreadConnection(),"select * from account where name = ?",new BeanListHandler<Account>(Account.class)
                    ,name);
            if(accounts == null || accounts.size() < 0){
                return null;
            }
            if(accounts.size() > 1){
                throw new RuntimeException("用户账户非法");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts.get(0);
    }

    public Account findById(Integer id) {
        Account account = null;
        try {
            account =  runner.query(connectionUtils.getThreadConnection(),"select * from account where id = ?",
                    new BeanHandler<Account>(Account.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public void UpdateAccount(Account account) {
        try {
            runner.update(connectionUtils.getThreadConnection(),"update account set name = ? , money = ? where id = ?",account.getName(),account.getMoney(),account.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
