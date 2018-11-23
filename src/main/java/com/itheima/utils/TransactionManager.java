package com.itheima.utils;

import java.sql.SQLException;

public class TransactionManager {
    public ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }


    /**
     * 开启事务
     */
    public void beginTransaction(){
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
    public void commitTransaction(){
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */

    public void rollbackTransaction(){
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接
     */

    public void closeTransaction(){
        try {
            connectionUtils.getThreadConnection().close();//归还连接到连接池
            connectionUtils.removeConnection();//与线程绑定的连接解除
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
