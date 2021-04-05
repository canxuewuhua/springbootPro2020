package com.example.demo.springproxy.aop;

import org.springframework.stereotype.Service;

@Service
public class AopPersonServiceImpl implements AopPersonService{


    @Override
    public String savePerson() {
        System.out.println("添加");
        return "AOP的 保存成功！";
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
