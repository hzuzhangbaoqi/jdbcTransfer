package com.itheima.utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {

    private ThreadLocal<Connection> t1 = new ThreadLocal<Connection>();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getThreadConnection(){
       try {
           Connection conn = t1.get();
           if(conn == null){
               conn = dataSource.getConnection();
               t1.set(conn);
           }

           return conn;
       }catch (Exception e){
            throw new RuntimeException(e);
       }
    }

    public void removeConnection(){
        t1.remove();
    }
}
