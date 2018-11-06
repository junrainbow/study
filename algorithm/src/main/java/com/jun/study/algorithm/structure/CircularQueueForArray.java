package com.jun.study.algorithm.structure;

/**
 * @author junrainbow
 * @Description 循环队列-数组实现
 * @Date:Create in 2018-11-05 16:20
 */
public class CircularQueueForArray {

    private int[] arry;//内部数组

    private int front;//头指针

    private int tail;//尾指针

    private int size;//元素数

    private int capacity;//容量

    private boolean full;//容量

    public CircularQueueForArray(){
        init(10);
    }

    public CircularQueueForArray(int capacity){
        init(capacity);
    }

    private void init(int capacity){
        this.arry = new int[capacity];
        this.front = 0;
        this.tail = 0;
        this.size = 0;
        this.capacity = capacity;
        full = false;
    }


    /**
     * 添加元素
     * @param value
     */
    public void add(int value){
        //未满
        if(full){
            throw new RuntimeException("队列已满");
        }
        //队尾追加
        arry[tail] = value;
        //尾指针达到极限
        if(tail==capacity-1){
            tail = 0;
        }else{
            tail++;
        }
        //元素数量增加
        size++;
        //队列已满
        if(size==capacity){
            full = true;
        }
    }

    /**
     * 删除元素
     */
    public int remove(){
        //未满
        if(size==0){
            throw new RuntimeException("队列为空");
        }
        int tmp = arry[front];
        //删除队头
        arry[front] = 0;
        //元素数量减少
        size--;
        //设置为不满
        full = false;
        //队列为空
        if(size==0){
            front = 0;
            tail = 0;
            return tmp;
        }else{
            //头指针达到极限
            if(front==capacity-1){
                front = 0;
            }else{
                front++;
            }
            return tmp;
        }
    }


    public void print(){
        if(size==0){
            System.out.println("队列为空，没有可打印的");
        }
        System.out.println("队列头指针下标【"+front+"】，尾指针下标【"+tail+"】");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            if(i==capacity-1){
                sb.append(arry[i]);
                break;
            }
            sb.append(arry[i]).append(",");
        }
        System.out.println(sb.toString());
    }

}
