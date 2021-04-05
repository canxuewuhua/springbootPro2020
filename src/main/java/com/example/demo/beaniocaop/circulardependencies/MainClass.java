package com.example.demo.beaniocaop.circulardependencies;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass{


    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);


        InstanceA instanceA = (InstanceA) ctx.getBean("instanceA");

        System.out.println(instanceA);
    }
}
