package com.winson.spring.mvc;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author winson
 * @date 2022/1/11
 **/
@WebServlet(asyncSupported = true, name = "annotationHttpServlet", loadOnStartup = 1,
        urlPatterns = {"/annotationHttpServlet", "/*"})
public class MyAnnotationHttpServlet extends HttpServlet {

    static {
        System.out.println("MyAnnotationHttpServlet static init ...... ");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("MyAnnotationHttpServlet destroy() ...... ");
    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("MyAnnotationHttpServlet getServletConfig() ...... ");
        return super.getServletConfig();
    }

    @Override
    public String getServletInfo() {
        System.out.println("MyAnnotationHttpServlet getServletInfo() ...... ");
        return super.getServletInfo();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("MyAnnotationHttpServlet init(ServletConfig config) ...... ");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);

        // 设置响应给页面的格式、字符集
        System.out.println("MyAnnotationHttpServlet doGet ...... ");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write("MyAnnotationHttpServlet 00001 --- ");
        writer.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
