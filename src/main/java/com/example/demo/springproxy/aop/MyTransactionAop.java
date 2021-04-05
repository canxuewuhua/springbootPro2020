package com.example.demo.springproxy.aop;


import com.example.demo.service.annotation.MyFirstAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

//切面类
@Aspect
@Component
public class MyTransactionAop {

    //切面里的通知方法
    public void beginTransaction(){
        System.out.println("开启事务 ");
    }
    //切面里的通知方法
    public void commit(){
        System.out.println("提交事务");
    }

    /**
     * 定义切点，对DAO层进行拦截
     * 代表AopPersonServiceImpl类中定义的任意方法的执行
     */
    @Pointcut("execution(* com.example.demo.springproxy.aop.AopPersonServiceImpl.savePerson(..))")
    public void privilege() {
        System.out.println("输出-------》");
    }

    /**
     * 前置通知：目标方法执行之前执行以下方法体的内容
     * @param joinPoint
     */
    @Before("privilege()")
    public void beforePointcut(JoinPoint joinPoint) {
        System.out.println("前置通知");
    }

    /**
     * 环绕通知：目标方法执行前后分别执行一些代码，发生异常的时候执行另外一些代码
     * @return
     */
    @Around("privilege()")
    public Object advice(ProceedingJoinPoint joinPoint){
        System.out.println("通知之开始");
        Object retmsg=null;
        try {
            retmsg=joinPoint.proceed();
            System.err.println("++++++++"+retmsg);
        }catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("通知之结束");
        return retmsg;
    }


    /**
     * 后置通知：目标方法执行之后执行以下方法体的内容，不管是否发生异常
     * @param joinPoint
     */
    @After("privilege()")
    public void afterPointcut(JoinPoint joinPoint) {
        System.out.println("后置通知");
    }

}
