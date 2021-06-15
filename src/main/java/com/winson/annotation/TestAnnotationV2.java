package com.winson.annotation;

import java.lang.reflect.Proxy;

/**
 * @author winson
 * @date 2021/6/15
 **/
public class TestAnnotationV2 {


    public static void main(String[] args) {
        System.out.println("test annotation v2 start ... ");

        AnnotationService service = (AnnotationService) Proxy.newProxyInstance(AnnotationServiceImpl.class.getClassLoader(),
                AnnotationServiceImpl.class.getInterfaces(),
                new AnnotationServiceHandler(new AnnotationServiceImpl()));
        service.addUser("12345678912345678912");
        service.addUser("123456789123456789123");
//        service.addUser(null);
        System.out.println("test annotation v2 stop ... ");
    }

}
