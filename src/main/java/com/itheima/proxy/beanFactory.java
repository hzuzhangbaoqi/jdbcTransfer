package com.itheima.proxy;

import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountServiceImpl;
import com.itheima.utils.TransactionManager;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class beanFactory {
    private AccountServiceImpl accountService;
    private TransactionManager transactionManager;

    public void setAccountService(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public IAccountService jdkProxyHandle() {
        return (IAccountService) Proxy.newProxyInstance(IAccountService.class.getClassLoader(), accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object invoke = null;
                        try {
                            transactionManager.beginTransaction();
                            invoke = method.invoke(accountService, args);
                            transactionManager.commitTransaction();
                        } catch (Exception e) {
                            transactionManager.rollbackTransaction();
                            throw new RuntimeException(e);
                        } finally {
                            transactionManager.closeTransaction();
                        }
                        return invoke;
                    }
                }
        );

    }


    public IAccountService cgLibProxyHandle(){
        return (IAccountService) Enhancer.create(IAccountService.class, new MethodInterceptor() {
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object invoke = null;
                try {
                    transactionManager.beginTransaction();
                    invoke = method.invoke(accountService, objects);
                    transactionManager.commitTransaction();
                } catch (Exception e) {
                    transactionManager.rollbackTransaction();
                    throw new RuntimeException(e);
                } finally {
                    transactionManager.closeTransaction();
                }
                return invoke;
            }
        });
    }

}
