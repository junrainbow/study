package com.jun.study.algorithm.tree;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-11-14 14:25
 */
public class TreeNode {

        /**
         * 节点
         */
        public TreeNode(){};
        public TreeNode(TreeNode left, TreeNode right, int value){
            this.left = left;
            this.right = right;
            this.value = value;
        }


        /**
         * 左节点
         */
        public TreeNode left;
        /**
         * 右节点
         */
        public TreeNode right;
        /**
         * 节点值
         */
        public int value;

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

}
