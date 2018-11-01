package com.jun.study.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * @author junrainbow
 * @Description 数组实现链表
 * @Date:Create in 2018-10-30 11:37
 */
public class LinkTableForArray {

    /**
     * 数组声明
     */
    private static int[] arry = new int[15];
    /**
     * 数组容量
     */
    private static int capacity = arry.length;
    /**
     * 数组元素
     */
    private static int elementLength = 0;
    /**
     * 初始化数组
     */
    static{
        for (int i = 1; i <= 10; i++) {
            arry[i-1] = i;
            elementLength++;
        }
    }


    /**
     * 测试
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //插入
        insertBeforeBySeq(1,0);
        System.out.println(JSON.toJSONString(arry));
        System.out.println(elementLength);
        //删除
        deleteBySeq(4);
        System.out.println(JSON.toJSONString(arry));
        System.out.println(elementLength);
    }

    /**
     * 插入元素到指定位置前面
     * @param seq
     */
    private static void insertBeforeBySeq(int seq,int value){
        //插入位置越界
        if(seq<1||seq>capacity){
            System.out.println("数组容量"+capacity+"元素个数"+elementLength+"，要处理序号"+seq);
            return;
        }
        //已满
        if(elementLength==capacity){
            System.out.println("数组容量"+capacity+"元素个数"+elementLength+"，要处理序号"+seq);
            return;
        }
        //插入的下标位置
        int insertIdx = seq-1;
        //将现有元素后移
        for (int i = elementLength; i > insertIdx; i--) {
            arry[i] = arry[i-1];
        }
        //将新值插入目标位置
        arry[insertIdx] = value;
        //元素数量+1
        elementLength++;
    }


    /**
     * 根据位置，删除元素
     * @param seq
     */
    private static void deleteBySeq(int seq){
        //覆盖起始位置
        int targetIdx = seq-1;
        //向前覆盖数据
        for (int i = targetIdx; i < elementLength-1; i++) {
            arry[i] = arry[i+1];
        }
        arry[elementLength-2] = 0;
        //元素数量-1
        elementLength--;
    }
}
