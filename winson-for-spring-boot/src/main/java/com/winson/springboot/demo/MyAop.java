package com.winson.springboot.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author winson
 * @date 2022/4/6
 **/
@Aspect
@Order(101)
@Component
public class MyAop {

    @Before(value = "@within(annotation)")
    public void before(JoinPoint joinPoint,MyAnnotation annotation) {
//        RequestContextHolder.getRequestAttributes();
//        System.out.println(joinPoint.getClass());
//        System.out.println(joinPoint.getSignature().getName());
//        System.out.println("before ===> ");
//        System.out.println("MyAop process ==> point:{}" + joinPoint.getSignature());

        System.out.println("one ----> before");
    }
//
//    @AfterThrowing(value = "@within(annotation)")
//    public void afterReturn(JoinPoint joinPoint,MyAnnotation annotation) {
//        System.out.println("afterReturn ===> ");
//
//    }

    @AfterThrowing(value = "@within(controller)", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, RestController controller, Exception e){
//        e.printStackTrace();
        System.out.println("one afterThrowing ===> ");
    }

//    @AfterThrowing(value = "@within(controller)", throwing = "e")
//    public void afterThrowing(JoinPoint joinPoint, RestController controller, NullPointerException e){
////        e.printStackTrace();
//        System.out.println("one afterThrowing ===> NullPointerException");
//    }

//    @Around(value = "@within(controller)")
//    public void around(JoinPoint joinPoint, RestController controller) throws Throwable {
//        ProceedingJoinPoint pj = (ProceedingJoinPoint) joinPoint;
//        System.out.println("around ===> start ");
//        try {
//            pj.proceed();
//        } catch (Throwable throwable) {
//            System.out.println("around ===> throw ");
////            throw throwable;
//        }
//        System.out.println("around ===> end ");
//    }

}
