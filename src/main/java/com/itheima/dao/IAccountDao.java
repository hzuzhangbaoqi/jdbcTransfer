package com.itheima.dao;

import com.itheima.domain.Account;

import java.util.List;

public interface IAccountDao {


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



}
