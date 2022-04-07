package com.winson.springboot.demo;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author winson
 * @date 2022/4/6
 **/
@RestControllerAdvice
public class MyAdvice {

    @ExceptionHandler({
            IllegalArgumentException.class
    })
    public void exceptionHandler(IllegalArgumentException e1){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        System.out.println(attributes);
        System.out.println("exceptionHandler :: IllegalArgumentException");
    }

    @ExceptionHandler({
            NullPointerException.class
    })
    public void exceptionHandler(NullPointerException ne){
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        System.out.println(Thread.currentThread().getName() + " thread :: "+attributes.getRequest().getRequestURL());
//        System.out.println(attributes.getRequest().getMethod());
//
//        System.out.println(attributes.getRequest().getRemoteAddr());

        System.out.println("exceptionHandler :: NullPointerException");
//        throw ne;
    }

    @ExceptionHandler({
            Exception.class
    })
    public void exceptionHandler(Exception e){
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        System.out.println(attributes);
        System.out.println("exceptionHandler :: Exception");
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            System.out.println(stackTraceElement);
        }
    }

}
