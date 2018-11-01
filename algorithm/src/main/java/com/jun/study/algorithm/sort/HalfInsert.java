package com.jun.study.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * @author junrainbow
 * @Description 二分法插入（和直接插入法类似，只是有序端使用二分法确定下标，然后插入，再将后续数据右移）
 * @Date:Create in 2018-10-18 21:10
 */
public class HalfInsert {

    /**
     * 二分插入排序法
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int[] arry = {92,7,5200,4,2,213,1,0};

        for (int i = 1; i < arry.length; i++) {

            if(arry[i]>arry[i-1]){
                continue;
            }

            int start = 0;
            int end = i-1;
            int maxVal = arry[i];
            while (start<=end){
                int mid = (start + end)>>1;
                int midVal = arry[mid];
                if(maxVal>midVal){
                    start = mid+1;
                }else{
                    end = mid-1;
                }
            }

            int targetIdx = start;
            for (int j = targetIdx; j < i; j++) {
                arry[j] = arry[j]^arry[i];
                arry[i] = arry[j]^arry[i];
                arry[j] = arry[j]^arry[i];
            }
        }
        System.out.println(JSON.toJSONString(arry));
    }

}
