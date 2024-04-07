package com.winson.springboot02;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author mike ian
 * @date 2024/4/7
 * @desc
 **/
@Service
public class ZKClientApp {

    public void useClient() throws Exception {
        ZkClient zkClient = new ZkClient("172.16.2.241:2181", 2000);
        System.out.println("connect 1");
        String data = zkClient.readData("/app");
        System.out.println("app data value is " + data);

        while (true) {
            System.in.read();
        }
    }

}
