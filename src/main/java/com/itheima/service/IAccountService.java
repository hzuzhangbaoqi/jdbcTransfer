package com.itheima.service;

import com.itheima.domain.Account;

public interface IAccountService {
    /**
     * 根据账户名进行查询
     * @param name
     * @return
     */
    Account findByName(String name);


    /**
     * 根据id进行查询
     * @param id
     * @return
     */
    Account findById(Integer id);

    /**
     * 更新账户
     */
    void UpdateAccount(Account account);

    /**
     * 进行转账操作
     * @param resourceName 转出账户名
     * @param targetName   转入账户名
     * @param money   转账金额
     */
    void transfer(String resourceName,String targetName,Float money);
}
