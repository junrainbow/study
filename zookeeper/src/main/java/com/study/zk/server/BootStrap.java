package com.study.zk.server;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-09-11 18:11
 */
public class BootStrap {

    public static void main(String[] args) throws Exception {
            new Thread(new ZkClusterServerBoot1()).start();
            new Thread(new ZkClusterServerBoot2()).start();
            new Thread(new ZkClusterServerBoot3()).start();
    }

}
