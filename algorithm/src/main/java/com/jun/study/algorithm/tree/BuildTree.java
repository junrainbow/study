package com.jun.study.algorithm.tree;

/**
 * @author junrainbow
 * @Description 构建二叉树
 * @Date:Create in 2018-11-14 14:23
 */
public class BuildTree {

    private int index = 0;

    private int capacity = 0;

    private char[] chars = null;

    public BuildTree(String inputElements){
        chars = inputElements.toCharArray();
        capacity = chars.length;

    }

    /**
     * 根据先序，构建二叉树
     * @return
     */
    public TreeNode buildBydlr(){
        //已无节点
        if(index==chars.length){
            return null;
        }
        //取值
        char c = chars[index++];
        //空节点直接返回
        if(c=='#'){
            return null;
        }
        //构建节点
        TreeNode parentNode = new TreeNode();
        parentNode.setValue(c);
        //创建左节点
        TreeNode leftNode = buildBydlr();
        //创建右节点
        TreeNode rightNode = buildBydlr();
        //组装节点
        parentNode.setLeft(leftNode);
        parentNode.setRight(rightNode);
        //返回父节点
        return parentNode;
    }


}
