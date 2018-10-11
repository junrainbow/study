package com.study.zk.server;

import org.apache.zookeeper.server.quorum.QuorumPeerMain;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-09-11 18:00
 */
public class ZkClusterServerBoot2 implements Runnable{

    private static String[] server2 = {"D:\\zookeeper3.4.11\\zookeeper-3.4.11\\conf\\zoo2.cfg"};

    public void run() {
        QuorumPeerMain.main(server2);
    }

    public static void main(String[] args) throws Exception {
        QuorumPeerMain.main(server2);
    }
}
