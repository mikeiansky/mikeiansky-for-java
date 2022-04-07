package com.winson.springboot.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author winson
 * @date 2022/4/6
 **/
//@MyAnnotation
@RestController
@MyAnnotation
public class MyController {

    @Autowired
    private MyService myService;

    @GetMapping("/user")
    public void getUserInfo(){
        System.out.println("myService : " + myService);
        System.out.println(Thread.currentThread().getName() + " get user info === start ");
        myService.function();
        System.out.println(Thread.currentThread().getName() + " get user info === end ");
    }

}
