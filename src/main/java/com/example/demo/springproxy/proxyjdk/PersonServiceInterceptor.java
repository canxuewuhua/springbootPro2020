package com.example.demo.springproxy.proxyjdk;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PersonServiceInterceptor implements InvocationHandler{
    //目标类
    private Object target;
    //增强类
    private MyTransaction myTransaction;
    //构造函数注入目标类和增强类
    public PersonServiceInterceptor(Object target,MyTransaction myTransaction){
        this.target = target;
        this.myTransaction = myTransaction;
    }

    //代理类的每一个方法被调用的时候都会调用下边的这个invoke方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        this.myTransaction.beginTransaction();
        Object returnValue = method.invoke(this.target, args);
        this.myTransaction.commit();
        return returnValue;
    }

}
