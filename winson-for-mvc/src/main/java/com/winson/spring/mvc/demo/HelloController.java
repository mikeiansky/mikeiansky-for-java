package com.winson.spring.mvc.demo;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author winson
 * @date 2022/1/13
 **/
public class HelloController implements Controller {

    static {
        System.out.println("HelloController static init()");
    }

    public HelloController() {
        System.out.println("HelloController construct init()");
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("HelloController --> handleRequest");
        return new ModelAndView();
    }

}
