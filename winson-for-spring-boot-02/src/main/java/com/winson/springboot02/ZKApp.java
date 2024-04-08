package com.winson.springboot02;

import jakarta.annotation.PostConstruct;
import org.I0Itec.zkclient.IZkDataListener;
import org.apache.zookeeper.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * @author mike ian
 * @date 2024/4/7
 * @desc
 **/
@Service
public class ZKApp {

    public void connectZKServer() throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper(
                "172.16.2.241:2181," +
                        "172.16.2.242:2181," +
                        "172.16.2.243:2181," +
                        "172.16.2.244:2181," +
                        "172.16.2.245:2181," +
                        "172.16.2.246:2181",
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
//        byte[] data = zooKeeper.getData("/hello",
//                watchedEvent -> {
//                    System.out.println("path:" + watchedEvent.getPath());
//                    System.out.println("KeeperState:" + watchedEvent.getState());
//                    System.out.println("EventType:" + watchedEvent.getType());
//                },
//                null);
//        zooKeeper.setData("/hello", "world".getBytes(), -1);
//
//        System.out.println(new String(data));
//        System.out.println("connect ----> 2");

        zooKeeper.addWatch("/temp", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                event.getType();
                System.out.println("node app :: " + event);
                System.out.println("watch node path :: " + event.getPath());

                try {
                    List<String> children = zooKeeper.getChildren("/temp", false);
                    System.out.println("before sort : " + children);
                    children.sort(Comparator.naturalOrder());
                    System.out.println("after sort : " + children);
                } catch (KeeperException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

//                try {
//                    zooKeeper.removeWatches("/temp", this, WatcherType.Persistent, false);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                } catch (KeeperException e) {
//                    throw new RuntimeException(e);
//                }

//                try {
//                    System.out.println("new data :: " +
//                            new String(zooKeeper.getData(event.getPath(), false, null)));
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
            }
        }, AddWatchMode.PERSISTENT);

        System.out.println("connect ----> 3");

        String lockValue = "lock-004";
//        String result = zooKeeper.create("/temp/", lockValue.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        String result = zooKeeper.create("/temp/", lockValue.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("create lock success : " + result);

//        List<String> lockChildren = zooKeeper.getChildren("/lock", false);
//        System.out.println("lockChildren :: " + lockChildren);
//        System.out.println("create lock success");



        boolean flag = true;
        while (flag) {
            System.in.read();
        }

        zooKeeper.close();
    }

}
