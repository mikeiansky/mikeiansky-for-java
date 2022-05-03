package com.winson.spring.mvc.demo;

import org.springframework.lang.Nullable;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author winson
 * @date 2022/5/3
 **/
public class MyJspHandler implements HttpRequestHandler, ServletContextAware {

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyJspHandler ---> handleRequest");
        RequestDispatcher rd = servletContext.getNamedDispatcher("jsp");
        rd.forward(request, response);
    }


}
