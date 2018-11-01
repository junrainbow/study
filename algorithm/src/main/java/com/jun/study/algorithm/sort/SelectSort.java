package com.jun.study.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * @author junrainbow
 * @Description 选择排序（两数比较，单独记录当前最值的指针位置，直到一趟遍历完成后，得到最值的下标。和当前趟数下标值交换
 * @Date:Create in 2018-10-18 15:01
 */
public class SelectSort {

//    public static void main(String[] args) throws Exception {
//        int[] array = {100,1,2,5,7,22,3333,423,6,7,1};
//
//        for (int i = 0; i < array.length - 1; i++) {
//            int idx = i;
//            for (int j = i+1; j < array.length; j++) {
//                if(array[j]<array[idx]){
//                    idx = j;
//                }
//            }
//            //swap
//            if(idx!=i){
//                array[i] = array[i]^array[idx];
//                array[idx] = array[i]^array[idx];
//                array[i] = array[i]^array[idx];
//            }
//        }
//
//        System.out.println(JSON.toJSONString(array));
//    }

    /**
     * 本次开发用时5分42秒
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int[] arry = {10,5,8,3,0};

        for (int i = 0; i < arry.length - 1; i++) {
            int idx = i;
            for (int j = i+1; j < arry.length; j++) {
                if(arry[idx]>arry[j]){
                    idx = j;
                }
            }
            if(idx!=i){
                arry[i] = arry[i]^arry[idx];
                arry[idx] = arry[i]^arry[idx];
                arry[i] = arry[i]^arry[idx];
            }
        }
        System.out.println(JSON.toJSONString(arry));
    }

}
