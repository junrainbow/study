package com.jun.study.algorithm.structure;

/**
 * @author junrainbow
 * @Description 链表实现栈
 * @Date:Create in 2018-11-02 14:34
 */
public class StackFoiLink {

    public StackFoiLink(){

    }


    /**
     * 栈顶指针
     */
    private LinkNode top;
    /**
     * 元素数量
     */
    public int size = 0;

    /**
     * 压栈
     * @param value
     */
    public void push(int value){
        LinkNode node = new LinkNode();
        node.setValue(value);
        if(top==null){
            top = node;
        }else{
            node.next = top;
            top = node;
        }
        size++;
    }

    /**
     * 弹栈
     * @return
     */
    public int pop(){
        if(top==null){
            throw new RuntimeException("栈空了");
        }
        int value = top.value;
        top = top.next;
        size--;
        return value;
    }

    /**
     * 删除
     */
    public void remove(){
        if(top==null){
            throw new RuntimeException("栈空了");
        }
        top = top.next;
        size--;
    }

}
