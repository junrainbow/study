package com.jun.study.algorithm.structure;

/**
 * @author junrainbow
 * @Description 循环队列-链表实现
 * @Date:Create in 2018-11-05 16:20
 */
public class CircularQueueForLink {


    private LinkNode front;//头指针

    private LinkNode tail;//尾指针

    private int size;//元素数


    public CircularQueueForLink(){
        init();
    }

    private void init(){
        this.front = null;
        this.tail = null;
        this.size = 0;
    }


    /**
     * 添加元素
     * @param value
     */
    public void add(int value){
        if(front==null){
            //构建新节点，所有指针都指向新节点
            LinkNode node = new LinkNode();
            node.setValue(value);
            node.setNext(front);
            front = node;
            tail = node;
        }else{
            LinkNode node = new LinkNode();
            node.setValue(value);
            node.setNext(front);//新节点指向头指针
            tail.setNext(node);//之前尾结点，指向新节点
            tail = node;//新节点成为尾节点
        }
        size++;
    }

    /**
     * 删除元素
     */
    public int remove(){
        //链表为空
        if(size==0){
            throw new RuntimeException("队列为空，无法删除");
        }
        //头节点值
        int tmp = front.getValue();
        //头节点指向下一个
        front = front.getNext();
        //元素个数自减
        size--;
        //返回结果
        return tmp;
    }


    /**
     * 打印队列元素
     */
    public void print(){
        LinkNode tmo_front = front;
        StringBuilder sb = new StringBuilder();
        while(tmo_front!=null){
            sb.append(tmo_front.getValue()).append(",");
            tmo_front = tmo_front.getNext();
            if(tmo_front==front){
                sb.deleteCharAt(sb.length()-1);
                break;
            }
        }
        System.out.println(sb.toString());
    }


    public LinkNode getFront() {
        return front;
    }

    public void setFront(LinkNode front) {
        this.front = front;
    }

    public LinkNode getTail() {
        return tail;
    }

    public void setTail(LinkNode tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
