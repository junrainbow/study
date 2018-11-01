package com.jun.study.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * @author junrainbow
 * @Description 堆排序
 * 步骤：
 *  1.将无序数组想象二叉树
 *  2.将二叉树，构建成大顶堆。从最下最右测跟节点开始向上调整。数组下标最大数i，最后一个根节点公式：(i-1)*2
 *  3.将数组首下标与尾下标值交换
 *  4.由于之前已调整为大顶堆结构，所以剩下的每次仅需要调整首节点即可。查找目标节点孩子公式：2*i+1（左节点）与2*i+2（右节点）
 * @Date:Create in 2018-10-26 16:57
 */
public class HeapSort {


    public static void main(String[] args) throws Exception {
        //准备基础参数
        int[] arry = {5,3,1,11,5555,92,99,0};
        int length = arry.length;
        int left = 0;
        int right = arry.length-1;
        //构建大顶堆
        buildHeap(arry,right);
        //交换数据
        swap(arry,left,right);
        //重新排序剩余节点
        for (int i = right-1; i > 0; i--) {
            sort(arry,0,i);
            swap(arry,0,i);
        }
        //打印结果
        System.out.println(JSON.toJSONString(arry));
    }

    /**
     * 构建大顶堆
     * @param arry 待排序的数组
     * @param right 数组长度
     */
    private static void buildHeap(int[] arry,int right){
        int lastRightNodeIdx = (right-1)/2;//最下层且最右侧的根节点下标
        while(lastRightNodeIdx>=0){
            sort(arry,lastRightNodeIdx,right);
            lastRightNodeIdx--;
        }
    }


    /**
     * 根据目标节点，调整为大顶堆
     * @param arry 待排序数组
     * @param targetNodeIdx 需要排序的根节点
     * @param right 数组长度
     */
    private static void sort(int[] arry, int targetNodeIdx, int right){
        //是否已全部排序
        int leftSubNodeIdx = 2*targetNodeIdx+1;//当前根节点，左下标
        int rightSubNodeIdx = 2*targetNodeIdx+2;//当前根节点，右下标
        int maxIdx = targetNodeIdx;//记录最大下标
        if(leftSubNodeIdx<=right&&arry[maxIdx]<arry[leftSubNodeIdx]){
            maxIdx = leftSubNodeIdx;
        }
        if(rightSubNodeIdx<=right&&arry[maxIdx]<arry[rightSubNodeIdx]){
            maxIdx = rightSubNodeIdx;
        }
        if(targetNodeIdx!=maxIdx){
            swap(arry,targetNodeIdx,maxIdx);
            sort(arry,maxIdx,right);
        }
        return ;
    }

    /**
     * 通过下标，交换值
     * @param arry
     * @param a
     * @param b
     */
    private static void swap(int[] arry,int a,int b){
        arry[a] = arry[a]^arry[b];
        arry[b] = arry[a]^arry[b];
        arry[a] = arry[a]^arry[b];
    }
}
