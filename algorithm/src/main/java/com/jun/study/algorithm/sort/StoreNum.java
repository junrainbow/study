package com.jun.study.algorithm.sort;


/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-10-15 16:49
 */
public class StoreNum {

    /**
     * 将N个整数以最小代价存入并排序打印
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //定义数据总量
        int count = 10000;
        //分成几段（因为INT类型4个字节32位，故分段为5，即：2^5次方=32）
        int shift = 5;
        //每段下标界限（因为每行为32位空间，由0-31格子组成，故界限为32-1=31）
        int mask = 31;
        //定义数组大小，（因为+1是因为数据从0开始，0-31存在一起，32需要存入新空间）
        int[] arry = new int[(count>>shift)+1];
        //载入数据到内存数组
        for(int num=0;num<count;num++){
            //1.定位数组行
            //2.定位数组列
            //3.1<<列下标，即左移动几次
            //4.与原数组或运算，即将该各自占上
            arry[num>>shift] |= ( 1<<(num&mask) ) ;
        }
        //打印数组数据
        int num = 0;
        for (int i = 0; i < arry.length; i++) {
            //获得行值
            int val = arry[i];
            //遍历每一位
            for (int j = 0; j < 32; j++) {
                //得到偏移量
                int offset = 1<<j;
                //获取余数（模）
                int mo = Integer.toBinaryString((val&offset)).length()-1;
                //当偏移量对应的数值与偏移量相同时，证明此处非空白
                if(mo==j){
                    //计算原值，行数*32+余数
                    num = i*32+mo;
                    //打印值
                    System.out.println(num);
                }
            }
        }
    }
}
