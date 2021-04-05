package com.example.demo.springproxy.proxyjdk;

import org.springframework.stereotype.Service;

@Service
public class JdkPersonServiceImpl implements JdkPersonService {

    @Override
    public String savePerson() {
        System.out.println("添加");
        return "保存成功！";
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
