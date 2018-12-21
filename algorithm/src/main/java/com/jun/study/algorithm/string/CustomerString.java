package com.jun.study.algorithm.string;

import java.util.LinkedList;
import java.util.List;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-11-06 16:42
 */
public class CustomerString {

    private char[] arry;

    public CustomerString(String str){
        //构造数组
        arry = new char[str.length()];
        //初始化数组
        init(str);
    }

    /**
     * 初始化主串数组
     * @param str
     */
    private void init(String str){
        for (int i = 0; i < str.length(); i++) {
            arry[i] = str.charAt(i);
        }
        System.out.println("主串为【"+str+"】");
    }

    /**
     * 查找子串，并返回首个匹配下标，-1为不存在
     * @param serachStr
     * @return
     */
    public int firstIndexOf(String serachStr){
        System.out.println("查找串为【"+serachStr+"】");
        int serachLength = serachStr.length();
        int length = arry.length;
        //小于等于主串
        if(length<serachLength){
            throw new RuntimeException("主串长度【"+length+"】，查找串长度【"+serachLength+"】");
        }
        //将查询串，转化为数组
        char[] serachArry = serachStr.toCharArray();
        //主串-查找串长度，作为查找次数
        int serachCount = length - serachLength;
        for (int i = 0; i <= serachCount; i++) {
            boolean equlas = true;//是否相等
            for (int j = 0; j < serachLength; j++) {
                char c1 = arry[i+j];
                char c2 = serachArry[j];
                //每个字符是否相等
                if(c1!=c2){
                    equlas = false;
                    break;
                }
            }
            if(equlas){
                return i;
            }
        }
        return -1;
    }



    /**
     * 查找子串，并返回所有匹配下标，-1为不存在
     * @param serachStr
     * @return
     */
    public List<Integer> indexOf(String serachStr){
        System.out.println("查找串为【"+serachStr+"】");
        List<Integer> result = new LinkedList<Integer>();//最大匹配次数
        int serachLength = serachStr.length();
        int length = arry.length;
        //小于等于主串
        if(length<serachLength){
            throw new RuntimeException("主串长度【"+length+"】，查找串长度【"+serachLength+"】");
        }
        //将查询串，转化为数组
        char[] serachArry = serachStr.toCharArray();
        //主串-查找串长度，作为查找次数
        int serachCount = length - serachLength;
        for (int i = 0; i <= serachCount; i++) {
            boolean equlas = true;//是否相等
            for (int j = 0; j < serachLength; j++) {
                char c1 = arry[i+j];
                char c2 = serachArry[j];
                //每个字符是否相等
                if(c1!=c2){
                    equlas = false;
                    break;
                }
            }
            if(equlas){
                result.add(i);
            }
        }
        //未匹配
        if(result.isEmpty()){
            result.add(-1);
        }
        return result;
    }

}
