package com.jun.study.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-10-22 10:25
 */
public class Exercise {

    public static void main(String[] args) throws Exception {

        aFunc(8);


    }

    static void aFunc(int n) {
        for (int i = 2; i < n; i++) {
            i *= 2;
            System.out.println(i);
        }
    }






    private static void swap(int[] arry,int i ,int j){
//        arry[i] = arry[i]^arry[j];
//        arry[j] = arry[i]^arry[j];
//        arry[i] = arry[i]^arry[j];
        arry[i] = arry[i]+arry[j];
        arry[j] = arry[i]-arry[j];
        arry[i] = arry[i]-arry[j];
    }
}
