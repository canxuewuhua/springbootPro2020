package com.example.demo.springproxy.proxy;

import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService{

    @Override
    public void savePerson() {
        System.out.println("添加");
    }

    @Override
    public void updatePerson() {
        System.out.println("修改");
    }

    @Override
    public void deletePerson() {
        System.out.println("删除");
    }

}
