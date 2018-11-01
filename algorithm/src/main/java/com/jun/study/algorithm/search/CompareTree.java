package com.jun.study.algorithm.search;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-11-01 11:40
 */
public class CompareTree {


    public static void main(String[] args) throws Exception {
        //构建二叉树
        BinaryTree tree = new BinaryTree(12);
        //先序遍历
        BinaryTraversal.dlr(tree.root);
    }

}
