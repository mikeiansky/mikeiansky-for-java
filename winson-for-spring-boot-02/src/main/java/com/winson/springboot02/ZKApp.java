package com.winson.springboot02;

import jakarta.annotation.PostConstruct;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author mike ian
 * @date 2024/4/7
 * @desc
 **/
@Service
public class ZKApp {

    public void connectZKServer() throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper(
                "172.16.2.241:2181",
                20000,
                new Watcher() {
                    @Override
                    public void process(WatchedEvent watchedEvent) {
                        // 发生变更的节点路径
                        String path = watchedEvent.getPath();
                        System.out.println("path:" + path);

                        // 通知状态
                        Watcher.Event.KeeperState state = watchedEvent.getState();
                        System.out.println("KeeperState:" + state);

                        // 事件类型
                        Watcher.Event.EventType type = watchedEvent.getType();
                        System.out.println("EventType:" + type);
                    }
                }
        );
        System.out.println("connect ----> 1");
        byte[] data = zooKeeper.getData("/hello",
                watchedEvent -> {
                    System.out.println("path:" + watchedEvent.getPath());
                    System.out.println("KeeperState:" + watchedEvent.getState());
                    System.out.println("EventType:" + watchedEvent.getType());
                },
                null);
        zooKeeper.setData("/hello", "world".getBytes(), -1);

        System.out.println(new String(data));
        System.out.println("connect ----> 2");

        boolean flag = true;
        while (flag){
            System.in.read();
        }

        zooKeeper.close();
    }

}
