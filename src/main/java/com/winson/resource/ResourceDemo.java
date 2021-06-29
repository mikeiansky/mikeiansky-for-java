package com.winson.resource;

import java.util.Properties;

/**
 * @author winson
 * @date 2021/6/29
 **/
public class ResourceDemo {

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");
        System.out.println("user.dir = " + userDir);

        String user = System.getProperty("user");
        System.out.println("user = " + user);

    }

}
