package com.winson.spring.mvc;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author winson
 * @date 2022/1/11
 **/
public class MyHttpFilterTwo extends HttpFilter {

    static {
        System.out.println("MyHttpFilterTwo static init");
    }

    public MyHttpFilterTwo(){
        super();
        System.out.println("MyHttpFilterTwo construct init");
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        super.init(config);
        System.out.println("MyHttpFilterTwo init FilterConfig");
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        super.doFilter(req, res, chain);
        System.out.println("MyHttpFilterTwo doFilter");
    }
}
