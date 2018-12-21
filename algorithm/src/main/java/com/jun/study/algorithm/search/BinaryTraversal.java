package com.jun.study.algorithm.search;

import com.jun.study.algorithm.tree.TreeNode;

/**
 * @author junrainbow
 * @Description 二叉树遍历
 * @Date:Create in 2018-11-01 11:52
 */
public class BinaryTraversal {

    /**
     * 先序遍历
     * @param root
     */
    public static void dlr(TreeNode root){
        //打印
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

    /**
     * 中序遍历
     * @param root
     */
    public static void ldr(TreeNode root){
        //递归左
        if(root.left!=null){
            ldr(root.left);
        }
        //打印
        System.out.println(root.value);
        //递归右
        if(root.right!=null){
            ldr(root.right);
        }
    }

    /**
     * 后序遍历
     * @param root
     */
    public static void lrd(TreeNode root){
        //递归左
        if(root.left!=null){
            lrd(root.left);
        }
        //递归右
        if(root.right!=null){
            lrd(root.right);
        }
        //打印
        System.out.println(root.value);

    }


}
