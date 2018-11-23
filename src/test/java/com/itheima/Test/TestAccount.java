package com.itheima.Test;

import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestAccount {
   /* @Test
    public void findAllAccount(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService contextBean = context.getBean("IAccountService", IAccountService.class);
        List<Account> allAccount = contextBean.findAllAccount();
        System.out.println(allAccount);
    }

    @Test
    public void findByName(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService iAccountService = context.getBean("IAccountService", IAccountService.class);
        Account ccc = iAccountService.findAccountByName("ccc");
        System.out.println(ccc);
    }*/

    @Test
    public void transfer(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService iAccountService = context.getBean("proxyAccountService",IAccountService.class);
        iAccountService.transfer("bbb","aaa",100f);
    }
}
