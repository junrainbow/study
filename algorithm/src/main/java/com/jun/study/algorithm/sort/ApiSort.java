package com.jun.study.algorithm.sort;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-10-18 10:23
 */
public class ApiSort {


    /**
     * 实现利用API，排序数组
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int[] arry = {9,10,23,58,90,1,2,5,7,89,-1,0};
        String[] sarry = {"C","c","b","a"};
        Arrays.sort(arry);
        Arrays.sort(sarry, Collections.reverseOrder());

        System.out.println(JSON.toJSONString(arry));
        System.out.println(JSON.toJSONString(sarry));
    }

}
