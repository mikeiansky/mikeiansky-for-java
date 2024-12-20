package io.github.mikeiansky.java.base.jdk.serviceloader;

/**
 * @author mike ian
 * @date 2024/12/20
 * @desc
 **/
public class HelloJava implements HelloService {
    @Override
    public void hello(String msg) {
        System.out.println("hello java " + msg);
    }
}
