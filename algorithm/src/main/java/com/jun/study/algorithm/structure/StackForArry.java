package com.jun.study.algorithm.structure;

/**
 * @author junrainbow
 * @Description 数组实现栈
 * @Date:Create in 2018-11-02 10:42
 */
public class StackForArry {

    public StackForArry(){
        arry = new int[defaultSize];
    }

    public StackForArry(int size){
        arry = new int[size];
    }



    private static int defaultSize = 10;

    private int[] arry;

    private int topIdx = 0;

    private int size = 0;


    /**
     * 压栈
     * @param i
     */
    public void push(int i){
        //是否已满
        if(size==arry.length){
            System.out.println("栈已满");
            return ;
        }
        if(size==0){
            //压入栈顶
            arry[topIdx] = i;
            size++;
        }else {
            //压入栈顶
            arry[++topIdx] = i;
            size++;
        }
    }

    /**
     * 弹栈
     * @return
     */
    public int pop(){
        if(size==0){
            throw new RuntimeException("栈为空");
        }
        int val = arry[topIdx];
        arry[topIdx] = 0;
        topIdx = --topIdx<0?0:topIdx--;
        size--;
        return val;
    }

    /**
     * 删除
     */
    public void remove(){
        if(size==0){
            throw new RuntimeException("栈为空");
        }
        arry[topIdx] = 0;
        topIdx = --topIdx<0?0:topIdx--;
        size--;
    }


    public static int getDefaultSize() {
        return defaultSize;
    }

    public static void setDefaultSize(int defaultSize) {
        StackForArry.defaultSize = defaultSize;
    }

    public int[] getArry() {
        return arry;
    }

    public void setArry(int[] arry) {
        this.arry = arry;
    }

    public int getTopIdx() {
        return topIdx;
    }

    public void setTopIdx(int topIdx) {
        this.topIdx = topIdx;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
