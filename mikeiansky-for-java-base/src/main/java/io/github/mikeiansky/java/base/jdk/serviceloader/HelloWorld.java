package io.github.mikeiansky.java.base.jdk.serviceloader;

/**
 * @author mike ian
 * @date 2024/12/20
 * @desc
 **/
public class HelloWorld implements HelloService{
    @Override
    public void hello(String msg) {
        System.out.println("hello world " + msg);
    }
}
