package com.jun.study.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * @author junrainbow
 * @Description 冒泡排序（每次将下标右移，两两比较互换）
 * @Date:Create in 2018-10-18 10:51
 */
public class BubbleSort {

    /**
     * @param args
     * @throws Exception
     */
//    public static void main(CustomerString[] args) throws Exception {
//        int[] arry = {2,33,99,0,87,671,2889,3,4};
//
//
//        for (int i = arry.length; i-1 > 0; i--) {
//            for (int j = 0; j < i - 1 ; j++) {
//                if(arry[j]>arry[j+1]){
//                    arry[j] = arry[j]^arry[j+1];
//                    arry[j+1] = arry[j]^arry[j+1];
//                    arry[j] = arry[j]^arry[j+1];
//                }
//            }
//        }
//
//        System.out.println(JSON.toJSONString(arry));
//
//
//    }

    /**
     * 本次开发用时3分25秒
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int[] arry = {100,1,2,2,3,54,21};

        for (int i = arry.length -1 ; i > 0 ; i--) {
            for (int j = 0; j < i; j++) {
                if(arry[j]>arry[j+1]){
                    arry[j] = arry[j]^arry[j+1];
                    arry[j+1] = arry[j]^arry[j+1];
                    arry[j] = arry[j]^arry[j+1];
                }
            }
        }
        System.out.println(JSON.toJSONString(arry));
    }
}
