package com.jun.study.algorithm.sort;

/**
 * @author junrainbow
 * @Description 获取链表中间节点
 * @Date:Create in 2018-10-31 11:48
 */
public class CalcLinkSize {


    private static int capacity = 0;

    static{
        for (int i = 1; i <= capacity; i++) {
            LinkTableForLink.add(i);
        }
        LinkTableForLink.printLink();
    }


    /**
     * 打印中间节点
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //获取起始节点
        LinkTableForLink.Node skipNode = LinkTableForLink.head.getStartNode();
        //链表为空
        if(skipNode==null){
            System.out.println("链表为空");
            return ;
        }
        //记录中间节点
        LinkTableForLink.Node midNode = skipNode;
        while(skipNode!=null){
            skipNode = skipNode.getNext();
            if(skipNode==null){
               break;
            }
            skipNode = skipNode.getNext();
            if(skipNode==null){
                break;
            }
            midNode = midNode.getNext();
        }
        System.out.println("中间节点值："+midNode.getValue());
    }



}
