package com.jun.study.algorithm.exercise;

import com.jun.study.algorithm.classic.Calc;
import com.jun.study.algorithm.structure.CircularQueueForArray;
import com.jun.study.algorithm.structure.CircularQueueForLink;

import java.util.Scanner;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-11-02 11:09
 */
public class ExerciseMain {

    public static void main(String[] args) throws Exception {

        //测试四则计算器
        calc();

        //循环队列（数组版）
        circularQueueForArray();

        //循环队列（链表版）
        circularQueueForLink();




    }

    /**
     * 循环队列（链表版）
     */
    private static void circularQueueForLink() {
        CircularQueueForLink circularQueue = new CircularQueueForLink();
        int count = 0;
        //元素入队
        for (int i = 1; i <= 10; i++) {
            circularQueue.add(++count);
        }
        //元素出队
        for (int i = 0; i < 5; i++) {
            circularQueue.remove();
        }
        for (int i = 0; i < 10; i++) {
            circularQueue.add(++count);
        }
        //打印
        circularQueue.print();
        System.out.println("尾指针的值："+circularQueue.getTail().getValue());
        System.out.println("头指针的值："+circularQueue.getTail().getNext().getValue());
    }

    /**
     * 循环队列（数组版）
     */
    private static void circularQueueForArray() {
        CircularQueueForArray deque = new CircularQueueForArray(12);
        int count = 0;
        //元素入队
        for (int i = 1; i <= 10; i++) {
            deque.add(++count);
        }
        //元素出队
        for (int i = 0; i < 5; i++) {
            deque.remove();
        }
        for (int i = 0; i < 7; i++) {
            deque.add(++count);
        }
        //打印
        deque.print();
    }

    /**
     * 逆波兰四则运算
     */
    private static void calc() {
        System.out.println("☆☆☆淘宝双11-智能计算器特卖，支持四则运算☆☆☆");
        Scanner scan = new Scanner(System.in);
        String exp = scan.next();
        System.out.println("您输入的表达式："+exp);
        Calc calc = new Calc();
        String result = calc.calc(exp);
        System.out.println("计算结果："+result);
    }



}
