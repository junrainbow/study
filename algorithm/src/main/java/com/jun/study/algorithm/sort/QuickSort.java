package com.jun.study.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * @author junrainbow
 * @Description 快速排序
 * @Date:Create in 2018-10-22 15:24
 */
public class QuickSort {

    private static int[] arry = {2,1,3,0,4,8,9,7,5,10,6};

    public static void main(String[] args) throws Exception {
        int left= 0;
        int right= arry.length-1;

        //左右指针法
        sort(arry,left,right);
        System.out.println(JSON.toJSONString(arry));
        //挖坑法
//        keng(arry,left,right);
//        System.out.println(JSON.toJSONString(arry));
    }

    /**
     * 挖坑排序
     * @param arry
     * @param left
     * @param right
     */
    public static void keng(int[] arry,int left,int right){
        //退出条件
        if(left>=right){
            return ;
        }
        //参考值
        int end = right;
        //完成一趟遍历
        int cutIdx = doCut2(arry,left,right);
        //排序左侧
        keng(arry,0,cutIdx-1);
        //排序右侧
        keng(arry,cutIdx+1,end);
    }

    /**
     * 找切点
     * @param arry
     * @param left
     * @param right
     * @return
     */
    private static int doCut2(int[] arry,int left,int right){
        //三数取中
        int keyIdx = selectMidIdx(arry,left,right);
        //交换参考值
        swap(arry,keyIdx,right);
        //参考值
        int key = arry[right];
        while(left<right){
            //左边找坑
            while(left<right&&arry[left]<=key){
                left++;
            }
            arry[right] = arry[left];
            //右边找坑
            while(left<right&&arry[right]>=key){
                right--;
            }
            arry[left] = arry[right];
        }
        arry[left] = key;
        return left;
    }




    /**
     * 左右指针法
     * @param arry
     * @param left
     * @param right
     */
    private static void sort(int[] arry,int left,int right){
        int start = left;
        int end = right;
        //退出条件
        if(start>=end){
            return ;
        }
        int cutIdx = doCut(arry,left,right);
        //递归分路排序
        sort(arry,start,cutIdx-1);
        sort(arry,cutIdx+1,end);

    }


    /**
     * 找切点
     * @param arry
     * @param left
     * @param right
     * @return
     */
    private static int doCut(int[] arry,int left,int right){
        //选出Key
        int end = right;
        int midIdx = selectMidIdx(arry,left,right);
        //将Key与右侧值互换
        swap(arry,midIdx,right);
        int key = arry[right];
        //循环交换
        while(left<right){
            //左下标
            while (left<right&&arry[left]<=key){
                left++;
            }
            //右下标
            while (left<right&&arry[right]>=key){
                right--;
            }
            //左右交换
            swap(arry,left,right);
        }
        //一趟排序完成，固定key值的位置
        swap(arry,left,end);
        //返回分隔点（切点）
        return left;
    }


    /**
     * 交换数组中的值
     * @param arry
     * @param i
     * @param j
     */
    private static void swap(int[] arry,int i,int j){
//        arry[i] = arry[i]^arry[j];
//        arry[j] = arry[i]^arry[j];
//        arry[i] = arry[i]^arry[j];
        int temp = arry[i];
        arry[i] = arry[j];
        arry[j] = temp;
    }


    /**
     * 找参考值（三数区中法）
     * @param arry
     * @param left
     * @param right
     * @return
     */
    private static int selectMidIdx(int[] arry,int left,int right){
        //取中间下标
        int mid = (left+right)>>1;
        //取中间值
        int leftVal = arry[left];
        int rightVal = arry[right];
        int midVal = arry[mid];
        if(leftVal<rightVal){
            if(leftVal>midVal){
                return left;
            }else{
                return mid;
            }
        }else{
            if(rightVal<midVal){
                return mid;
            }else{
                return right;
            }
        }
    }

}
