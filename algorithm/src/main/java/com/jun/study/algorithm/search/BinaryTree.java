package com.jun.study.algorithm.search;

import java.util.Stack;

/**
 * @author junrainbow
 * @Description 二叉树
 * @Date:Create in 2018-11-01 11:03
 */
public class BinaryTree {

    /**
     * 构建树
     */
    public BinaryTree(){
        init();
    };

    /**
     * 构建树
     */
    public BinaryTree(int size){
        this.size = size;
        init();
    }

    private int size = 12;

    private Stack<Node> elementStack = new Stack<Node>();

    private Stack<Node> branchStack = new Stack<Node>();

    public Node root;



    /**
     * 初始化二叉树
     */
    private void init(){
        //计算最后一个根节点下标
        int branchNodeIdx = size-1-1>>1;
        //初始化节点
        for (int i = 1; i <= size; i++) {
            Node node = new Node(null,null,i);
            //保存根节点
            if(i==1){
                root = node;
            }
            //所有节点入栈
            elementStack.push(node);
            //将根节点入栈
            if(i-1<=branchNodeIdx){
                branchStack.push(node);
            }
        }
        System.out.println("元素总数："+elementStack.size()+"，根节点数："+branchStack.size()+"，最后一个根节点下标："+branchNodeIdx);
        //组装节点
        boolean flag = true;
        for (int i = branchStack.size(); i > 0; i--) {
            //偶数则最后一个节点组装时，装配一个节点
            if(size%2==0&&flag){
                Node branch = branchStack.pop();
                Node left = elementStack.pop();
                branch.left = left;
                flag = false;
                continue;
            }
            Node branch = branchStack.pop();
            Node right = elementStack.pop();
            Node left = elementStack.pop();
            branch.left = left;
            branch.right = right;
        }
    }


    /**
     * 节点
     */
    public static class Node{
        public Node(){};
        public Node(Node left,Node right,int value){
            this.left = left;
            this.right = right;
            this.value = value;
        }


        /**
         * 左节点
         */
        Node left;
        /**
         * 右节点
         */
        Node right;
        /**
         * 节点值
         */
        int value;

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

    }

}
