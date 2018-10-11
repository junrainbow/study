package com.study.zk;

import org.apache.curator.framework.CuratorFramework;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-09-11 18:20
 */
public class Main {

    public static void main(String[] args) throws Exception {
//        CuratorFramework client = Connect.getClient();
//
//        client.setData().forPath("/haha","55".getBytes());

//        byte[] stat = client.getData().forPath("/haha");
//        System.out.println(new String(stat));


        String s2 = "ab";
        String s3 = "cd";
        String s1 = "abcd";
        String s10 = new String("abcd");
        System.out.println(s1.intern()==s1);//true
        System.out.println(s1.intern()==s10);//false
        System.out.println(s1.intern()==s10.intern());//true
        System.out.println(s10.intern()==s10);//false
        String s11 = new String("ab"+"cd");
        System.out.println(s11.intern()==s1);//true

        byte[] arr = {65,66,67,68};
        String s12 = new String(arr);
        System.out.println(s12.intern()==s1);//false
        String s13 = new String(arr);
        System.out.println(s12.intern()==s13.intern());//true
        System.out.println(s12==s12.intern());//true
        System.out.println(s13==s13.intern());//false

        String s14 = new String(s2+s3);
        System.out.println(s14.intern()==s1);//true
        System.out.println(s14.intern()==s12.intern());//false

    }




}
