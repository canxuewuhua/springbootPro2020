package com.example.demo.springproxy.proxycglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class PersonServiceInterceptorCglib implements MethodInterceptor {

    //目标类
    private Object target;
    //增强类
    private MyTransactionCglib myTransaction;

    //构造函数注入目标类和增强类
    public PersonServiceInterceptorCglib(Object target,MyTransactionCglib myTransaction){
        this.target = target;
        this.myTransaction = myTransaction;
    }

    public Object createProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(this);
        enhancer.setSuperclass(this.target.getClass());
        return enhancer.create();
    }

    @Override
    public Object intercept(Object arg0, Method arg1, Object[] arg2,
                            MethodProxy arg3) throws Throwable {
        myTransaction.beginTransaction();
        Object returnValue = arg1.invoke(this.target, arg2);
        myTransaction.commit();
        return returnValue;
    }
}
