package com.study.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;

public class Connect {

    private static final String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
    private static final int connectTimeout = 100000;
    private static final int sessionTimeout = 6000;
    private static final String parentNode = "junrainbow";



    public static CuratorFramework getClient(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);//刚开始重试间隔为1秒，之后重试间隔逐渐增加，最多重试不超过三次
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString(connectString)
                .sessionTimeoutMs(sessionTimeout)//会话超时时间
                .connectionTimeoutMs(connectTimeout)//连接超时时间
                .retryPolicy(retryPolicy)
                .namespace(parentNode)
                .build();
        client.start();
        return client;
    }


    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);//刚开始重试间隔为1秒，之后重试间隔逐渐增加，最多重试不超过三次
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString(connectString)
                .sessionTimeoutMs(sessionTimeout)//会话超时时间
                .connectionTimeoutMs(connectTimeout)//连接超时时间
                .retryPolicy(retryPolicy)
                .namespace(parentNode)
                .build();

        client.start();

        String path = client.create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath("/haha","niubi".getBytes());
        System.out.println(path);

        client.close();
    }



}