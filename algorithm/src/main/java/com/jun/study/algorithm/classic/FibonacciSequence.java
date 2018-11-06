package com.jun.study.algorithm.classic;

import com.alibaba.fastjson.JSON;

/**
 * @author junrainbow
 * @Description 斐波那契序列
 * @Date:Create in 2018-11-01 20:50
 */
public class FibonacciSequence {


    public static void main(String[] args) throws Exception {
        //打印斐波那契
        print1();
        //打印斐波那契
        print2();
    }

    /**
     * 数组实现
     */
    private static void print1() {
        int[] arry = new int[40];
        arry[0] = 0;
        arry[1] = 1;
        for (int i = 2; i < 40; i++) {
            arry[i] = arry[i-1]+arry[i-2];
        }
        System.out.println("print1："+JSON.toJSONString(arry));
    }


    /**
     * 递归实现
     * @return
     */
    static void print2(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 40; i++) {
            sb.append(fib(i)).append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println("print2："+sb.toString());
    }

    static int fib(int i ){
        if(i<2){
            return i==0?0:1;
        }
        return fib(i-1)+fib(i-2);
    }



}
