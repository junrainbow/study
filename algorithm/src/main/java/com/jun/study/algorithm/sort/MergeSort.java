package com.jun.study.algorithm.sort;

import com.alibaba.fastjson.JSON;


/**
 * @author junrainbow
 * @Description 归并排序
 * 1.将大数组，拆分两段
 * 2.每段先拆分左边，再拆分右边，直到拆分为独立两个元素为止
 * 3.比较并排序元素，放回到原始数组中
 * 4.其实是将大数组，划分为N对儿元素值，在将每一对儿合并。合并的过程，是自下而上的进行合并的。最终是两个已排序好的数组合并，得到完整的有序数组。
 * @Date:Create in 2018-10-19 11:33
 */
public class MergeSort {

    public static void main(String []args){
        //声明数组
        int []arr = {9,8,7,6,5,4,3,2,1};
        //归并排序
        sort(arr);
        //打印
        System.out.println(JSON.toJSONString(arr));
    }


    /**
     * 准备排序基础数据
     * @param arr
     */
    public static void sort(int[] arr){
        int[] temp = new int[arr.length];
        int left = 0;
        int right = arr.length-1;
        mergeSort(arr,left,right,temp);
    }


    /**
     * 递归拆分
     * @param arr 原始数组
     * @param left 数组左下标
     * @param right 数组右下表
     * @param temp 临时数组
     */
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        //每一对儿小数组，左<右，那么还需要拆分，否则已成为两个独立的数字
        if(left<right){
            //取中间下标
            int mid = (left+right)/2;
            //递归，中间当作下一次拆分的右下标，优先拆左树
            mergeSort(arr,left,mid,temp);
            //递归，中间+1当作下一次拆分左下标，回到上一层，拆右树
            mergeSort(arr,mid+1,right,temp);
            //合并
            merge(arr,left,mid,right,temp);
        }
    }

    /**
     * 排序并合并同级元素
     * @param arr 原始数组
     * @param left 当前数组左下标
     * @param mid 当前数组中间下标
     * @param right 当前数组右下标
     * @param temp 临时数组buffer，用于将当前数组左右两部分数据，排序到临时数组中，在从临时数组倒回原始数组
     */
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        //左序列起始位置
        int leftStart = left;
        //右序列起始位置
        int rightStart = mid+1;
        //左序列结束位置
        int leftEnd = mid;
        //右序列结束位置
        int rightEnd = right;
        //临时数组装的个数
        int idx = 0;
        //两部分数组，是否仍存在元素，说明还需比较
        while (leftStart<=leftEnd&&rightStart<=rightEnd){
            //谁小就放入临时数组
            if(arr[leftStart]<=arr[rightStart]){
                temp[idx] = arr[leftStart];
                leftStart++;
            }else{
                temp[idx] = arr[rightStart];
                rightStart++;
            }
            idx++;
        }
        //左数组是否有剩余元素，有则直接追加到尾部。因为之前已排序，剩余的必然大
        while(leftStart<=leftEnd){
            temp[idx] = arr[leftStart];
            leftStart++;
            idx++;
        }
        //右数组是否有剩余元素，有则直接追加到尾部。因为之前已排序，剩余的必然大
        while(rightStart<=rightEnd){
            temp[idx] = arr[rightStart];
            rightStart++;
            idx++;
        }
        //将临时数组元素，导入原始数组中
        for (int i = 0; i < idx; i++) {
            arr[left+i] = temp[i];
        }
    }
}
