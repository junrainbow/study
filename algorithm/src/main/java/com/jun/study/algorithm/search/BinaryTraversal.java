package com.jun.study.algorithm.search;

/**
 * @author junrainbow
 * @Description 二叉树遍历
 * @Date:Create in 2018-11-01 11:52
 */
public class BinaryTraversal {


    public static void dlr(BinaryTree.Node root){
        //先根
        System.out.println(root.value);
        //递归左
        if(root.left!=null){
            dlr(root.left);
        }
        //递归右
        if(root.right!=null){
            dlr(root.right);
        }
    }


}
