package com.example.demo.dailyexercise;

public class DlinkedNode {

    int key;
    int value;

    DlinkedNode pre;
    DlinkedNode next;

    DlinkedNode(){}

    DlinkedNode(int key, int value){
        this.key = key;
        this.value = value;
    }
}
