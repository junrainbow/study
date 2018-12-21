package com.jun.study.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * @author junrainbow
 * @Description 插入排序（认为当前数组第一个数为有序序列，后面所有数字认为无需序列。
 * 将无序序列挨个与有序序列比较，形成新的有序序列。即：无序序列会逐渐加入到有序序列中。
 * 特点：比如升序，有序序列最后一个值即为当前最大，如果无序中的值大于有序最后一个值，则无需比较。直接进行下一趟比较，优于选择与冒泡
 * @Date:Create in 2018-10-18 17:11
 */
public class InsertSort {

//    public static void main(CustomerString[] args) throws Exception {
//        int[] arry = {9,3,1,4,2,7,8,6,5};
//
//        for (int i = 1; i < arry.length; i++) {
//            for (int j = 0; j < i; j++) {
//                if(arry[i]<arry[j]){
//                    swap(arry,i,j);
//                }
//            }
//        }
//
//        System.out.println();
//    }
//
    private static void swap(int[] arry,int a, int b){
        arry[a] = arry[a]^arry[b];
        arry[b] = arry[a]^arry[b];
        arry[a] = arry[a]^arry[b];
    }


    /**
     * 直接插入排序
     * 开发用时3分56秒
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int[] arry = {1,5,7,8,912,3212,2};

        for (int i = 1; i < arry.length; i++) {
            //while实现
//            int j = i;
//            while(j>0&&arry[j]<arry[j-1]){
//                swap(arry,j,j-1);
//                j--;
//            }
            //for实现
            for (int j = i; j > 0; j--) {
                if(arry[j]>arry[j-1]){
                    break;
                }
                swap(arry,j,j-1);
            }
        }
        System.out.println(JSON.toJSONString(arry));
    }

}
