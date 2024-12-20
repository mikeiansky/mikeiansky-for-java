package io.github.mikeiansky.java.base.jdk.serviceloader;

import java.util.ServiceLoader;

/**
 * @author mike ian
 * @date 2024/12/20
 * @desc
 **/
public class ServiceloaderDemo {

    public static void main(String[] args) {

        ServiceLoader<HelloService> loaders = ServiceLoader.load(HelloService.class);
        for (HelloService helloService : loaders) {
            helloService.hello("demo");
        }

    }

}
