package com.jun.study.algorithm.structure;

/**
 * @author junrainbow
 * @Description 链表节点
 * @Date:Create in 2018-11-02 14:42
 */
public class LinkNode {

    /**
     * 节点值
     */
    public int value;

    /**
     * 下一个节点
     */
    public LinkNode next;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public LinkNode getNext() {
        return next;
    }

    public void setNext(LinkNode next) {
        this.next = next;
    }
}
