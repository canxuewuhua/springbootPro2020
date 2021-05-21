package com.example.demo.dailyAlgorithm.lru;

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
