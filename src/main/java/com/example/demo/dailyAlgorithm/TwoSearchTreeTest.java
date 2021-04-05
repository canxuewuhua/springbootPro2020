package com.example.demo.dailyAlgorithm;

public class TwoSearchTreeTest {
    public static void main(String[] args) {
        Node root = new Node(6);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);

        node4.leftChild = node3;
        node4.rightChild = node5;
        root.leftChild = node4;
        root.rightChild = node8;
        node8.leftChild = node7;
        node8.rightChild = node9;

        node3.leftChild = null;
        node3.rightChild = null;

        PostOrder(root);
    }

    public static void preOrder(Node root){
        if (root!=null){
            System.out.println("节点的值："+root.val);
            preOrder(root.leftChild);
            preOrder(root.rightChild);
        }
    }

    public static void InOrder(Node root){
        if (root!=null){
            InOrder(root.leftChild);
            System.out.println("节点的值："+root.val);
            InOrder(root.rightChild);
        }
    }

    public static void PostOrder(Node root){
        if (root!=null){
            PostOrder(root.leftChild);
            PostOrder(root.rightChild);
            System.out.println("节点的值："+root.val);
        }
    }
}
