package com.example.demo.controller;


import com.example.demo.util.dingyiconfig.MyDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloConfigController {

    @Autowired
    private MyDateUtil myDateUtil;

    @RequestMapping("testConfig")
    public String test(String str){
        String localTime = myDateUtil.getLocalTime();
        return localTime;
    }
}
