package com.jun.study.algorithm.sort;

/**
 * @author junrainbow
 * @Description 初始化链表
 * @Date:Create in 2018-11-01 15:42
 */
public class Temp {

        private static int size = 10;
        
        public static void main(String[] args) throws Exception {
            LinkTableForLink.HeadNode head = new LinkTableForLink.HeadNode();
            LinkTableForLink.Node last = null;
            for (int i = 1; i <= size; i++) {
                //构建节点
                LinkTableForLink.Node node = new LinkTableForLink.Node();
                node.setValue(i);
                //初始化头节点
                if(i==1){
                    last = node;
                    head.setStartNode(node);
                    head.setEndNode(node);
                    continue;
                }
                last.setNext(node);
                node.setPrev(last);
                last = node;
            }


            LinkTableForLink.Node node = head.getStartNode();
            while(node!=null){
                System.out.println(node.getValue());
                node = node.getNext();
            }
        }


}
