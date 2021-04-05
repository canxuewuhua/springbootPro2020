package com.example.demo.springproxy.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 静态代理模式的缺点：
 *
 * 1、假设一个系统中有100个Service，则需要创建100个代理对象
 *
 * 2、如果一个Service中有很多方法需要事务（增强动作），发现代理对象的方法中还是有很多重复的代码
 *
 * 3、由第一点和第二点可以得出：静态代理的重用性不强
 *
 * 那怎么解决呢？
 *
 * 用动态代理就可以很好的解决上述问题
 */
@Service
public class PersonServiceProxy implements PersonService{

    //目标类
    @Autowired
    private PersonService personService;

    //增强类
    @Autowired
    private Transaction transaction;

    //利用构造函数将目标类和增强类注入
    public PersonServiceProxy(PersonService personService,Transaction transaction){
        this.personService = personService;
        this.transaction = transaction;
    }

    @Override
    public void savePerson() {
        transaction.beginTransaction();
        personService.savePerson();
        transaction.commit();
    }

    @Override
    public void updatePerson() {
        transaction.beginTransaction();
        personService.updatePerson();
        transaction.commit();
    }

    @Override
    public void deletePerson() {
        transaction.beginTransaction();
        personService.deletePerson();
        transaction.commit();
    }
}
