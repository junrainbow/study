package com.jun.study.algorithm.match;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;
import java.util.List;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-11-06 19:03
 */
public class KMP {

    public KMP(String masterString,String parttenString){
        this.masterString = masterString;
        this.parttenString = parttenString;
        init();
    }

    /**
     * 主串
     */
    private String masterString;
    /**
     * 模式串（搜索串）
     */
    private String parttenString;
    /**
     * 记录模式串中，每种组合的最长重复长度
     */
    private int[] next;

    /**
     * 初始化，并检查数组
     */
    private void init(){
        //校验主串与模式串非空
        checkEmpty();
        //初始化数组
        next = new int[parttenString.length()];
    }

    /**
     * 返回首次完全匹配的位置
     * @return
     */
    public int firstIndexof(){
        //得到子串next核心数组，好知道不匹配时，从哪里再次比较
        parseNextByParttenString();
        //进行匹配
        int masterLength = masterString.length();
        int parttenLength = parttenString.length();
        int si = 0;
        int pj = 0;

        while(si<masterLength&&pj<parttenLength){
            char sc = masterString.charAt(si);
            char pc = parttenString.charAt(pj);
            if(sc==pc){
                si++;
                pj++;
            }else{
                pj = next[pj-1];
            }
        }
        //子串匹配次数等于子串长度，代表完全匹配了
        if(pj==parttenLength){
            return si - pj;//si是当前下标最后边-pj长度=si完全匹配的起始位置
        }
        return -1;
    }


    /**
     * 返回所有完全匹配的位置，没匹配返回-1
     * @return
     */
    public List<Integer> indexof(){
        List<Integer> matchResult = new LinkedList<>();//记录匹配结果集
        //得到子串next核心数组，好知道不匹配时，从哪里再次比较
        parseNextByParttenString();
        //进行匹配
        int masterLength = masterString.length();
        int parttenLength = parttenString.length();
        int si = 0;
        int pj = 0;

        while(si<masterLength){
            char sc = masterString.charAt(si);
            char pc = parttenString.charAt(pj);
            if(sc==pc){
                si++;
                pj++;
            }else{
                pj = next[pj-1];
            }

            //子串匹配次数等于子串长度，代表完全匹配了
            if(pj==parttenLength){
                matchResult.add(si - pj);//si是当前下标最后边-pj长度=si完全匹配的起始位置
                pj = 0;//归0，再次匹配
            }
        }
        //未匹配
        if(matchResult.isEmpty()){
            matchResult.add(-1);
        }
        return matchResult;
    }


    /**
     * 解析模式串，获得next数组
     */
    private void parseNextByParttenString(){
        int p_length = parttenString.length();//模式串长度
        for (int i = 1; i < p_length; i++) {
            int matchCount = 0;//连续匹配次数
            //比对自己
            for (int j = 0; i<p_length && j < p_length; j++) {
                char c1 = parttenString.charAt(i);
                char c2 = parttenString.charAt(j);
                //将当前连续匹配次数，赋值给next
                if(c1==c2){
                    matchCount++;
                    next[i] = matchCount;//当前长度，匹配次数
                    i++;
                }else{
                    //次数清0，重新匹配计数
                    matchCount = 0;
                    next[i] = matchCount;
                    break;
                }
            }
        }
        System.out.println("本次next数组值："+JSON.toJSONString(next));
    }


    private void checkEmpty(){
        if(masterString==null||masterString.length()==0){
            throw new RuntimeException("主串为空");
        }
        if(parttenString==null||parttenString.length()==0){
            throw new RuntimeException("模式串为空");
        }
    }


    public String getMasterString() {
        return masterString;
    }

    public void setMasterString(String masterString) {
        this.masterString = masterString;
    }

    public String getParttenString() {
        return parttenString;
    }

    public void setParttenString(String parttenString) {
        this.parttenString = parttenString;
    }

    public int[] getNext() {
        return next;
    }

    public void setNext(int[] next) {
        this.next = next;
    }
}
