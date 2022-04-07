package com.winson.springboot.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author winson
 * @date 2022/4/6
 **/
@Aspect
//@Order(100)
@Component
public class MyAopSecond {

    @Before(value = "@within(controller)")
    public void fefore(JoinPoint joinPoint, RestController controller) {
//        ProceedingJoinPoint pj = (ProceedingJoinPoint) joinPoint;
//        System.out.println(joinPoint.getClass());
//        System.out.println("second fefore ===> ");
//        System.out.println("second process ==> point:{}" + joinPoint.getSignature());

        System.out.println("second ----> before");


    }
//
//    @AfterThrowing(value = "@within(controller)")
//    public void afterReturn(JoinPoint joinPoint, RestController controller){
//        System.out.println("second afterReturn ===> ");
//    }

    @AfterThrowing(value = "@within(controller)", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, RestController controller, NullPointerException e){
//        e.printStackTrace();
        System.out.println("second afterThrowing ===> NullPointerException");
    }

    @AfterThrowing(value = "@within(controller)", throwing = "e")
    public void afterThrowing2(JoinPoint joinPoint, RestController controller, IllegalArgumentException e){
//        e.printStackTrace();
        System.out.println("second afterThrowing ===> IllegalArgumentException");
        throw new NullPointerException("null object");
    }

//    @Around(value = "@within(controller)")
//    public void around(JoinPoint joinPoint, RestController controller) throws Throwable {
//        ProceedingJoinPoint pj = (ProceedingJoinPoint) joinPoint;
//        System.out.println("second around ===> start ");
//        try {
//            pj.proceed();
//        } catch (Throwable throwable) {
//            System.out.println("second around ===> throw ");
////            throw throwable;
//        }
//        System.out.println("second around ===> end ");
//    }

}
