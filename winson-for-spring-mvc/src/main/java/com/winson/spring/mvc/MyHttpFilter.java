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
public class MyHttpFilter extends HttpFilter {

    static {
        System.out.println("MyHttpFilter static init");
    }

    public MyHttpFilter(){
        super();
        System.out.println("MyHttpFilter construct init");
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        super.init(config);
        System.out.println("MyHttpFilter init FilterConfig");
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        super.doFilter(req, res, chain);
        System.out.println("MyHttpFilter doFilter");
    }
}
