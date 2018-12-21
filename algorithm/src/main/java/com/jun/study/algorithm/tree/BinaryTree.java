package com.jun.study.algorithm.tree;

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

    private Stack<TreeNode> elementStack = new Stack<TreeNode>();

    private Stack<TreeNode> branchStack = new Stack<TreeNode>();

    public TreeNode root;



    /**
     * 初始化二叉树
     */
    private void init(){
        //计算最后一个根节点下标
        int branchTreeNodeIdx = size-1-1>>1;
        //初始化节点
        for (int i = 1; i <= size; i++) {
            TreeNode TreeNode = new TreeNode(null,null,i);
            //保存根节点
            if(i==1){
                root = TreeNode;
            }
            //所有节点入栈
            elementStack.push(TreeNode);
            //将根节点入栈
            if(i-1<=branchTreeNodeIdx){
                branchStack.push(TreeNode);
            }
        }
        System.out.println("元素总数："+elementStack.size()+"，根节点数："+branchStack.size()+"，最后一个根节点下标："+branchTreeNodeIdx);
        //组装节点
        boolean flag = true;
        for (int i = branchStack.size(); i > 0; i--) {
            //偶数则最后一个节点组装时，装配一个节点
            if(size%2==0&&flag){
                TreeNode branch = branchStack.pop();
                TreeNode left = elementStack.pop();
                branch.left = left;
                flag = false;
                continue;
            }
            TreeNode branch = branchStack.pop();
            TreeNode right = elementStack.pop();
            TreeNode left = elementStack.pop();
            branch.left = left;
            branch.right = right;
        }
    }



}
