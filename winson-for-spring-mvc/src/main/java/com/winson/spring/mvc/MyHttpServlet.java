package com.winson.spring.mvc;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author winson
 * @date 2022/1/10
 **/
public class MyHttpServlet extends HttpServlet {

    static {
        System.out.println("MyHttpServlet static init ...... ");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("MyHttpServlet destroy() ...... ");
    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("MyHttpServlet getServletConfig() ...... ");
        return super.getServletConfig();
    }

    @Override
    public String getServletInfo() {
        System.out.println("MyHttpServlet getServletInfo() ...... ");
        return super.getServletInfo();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("MyHttpServlet init(ServletConfig config) ...... ");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);

        // 设置响应给页面的格式、字符集
        System.out.println("MyHttpServlet doGet ...... ");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write("MyHttpServlet 00000 --- ");
        writer.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);

    }

}
