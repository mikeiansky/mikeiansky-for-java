package com.winson.spring.mvc.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author winson
 * @date 2022/5/2
 **/
@RequestMapping("/home")
//@RestController("/home")
public class RequestMappingController {

    @RequestMapping("/info")
    public String doInfo(HttpServletRequest request, HttpServletResponse response){
        System.out.println("RequestMappingController do info .... ");
        return "/WEB-INF/winson.html";
    }

    @RequestMapping("/mv")
    public ModelAndView doMV(HttpServletRequest request, HttpServletResponse response){
        System.out.println("RequestMappingController do mv .... ");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/WEB-INF/ciwei2.jsp");
        return mv;
    }

    @RequestMapping("/json")
    @ResponseBody
    public String doJson(HttpServletRequest request, HttpServletResponse response){
        System.out.println("RequestMappingController do json .... ");
        return "json haha";
    }

}
