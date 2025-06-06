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
 * @date 2022/1/4
 **/
public class WinsonServlet2 extends HttpServlet {

    static {
        System.out.println("WinsonServlet2 static init ... ");
    }

    public WinsonServlet2(){
        System.out.println("WinsonServlet2 construct init ... ");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("WinsonServlet2 init(ServletConfig config) ... ");
        System.out.println("WinsonServlet2 init(ServletConfig config) - classLoader: " + Thread.currentThread().getContextClassLoader());
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("WinsonServlet2 destroy ... ");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置响应给页面的格式、字符集
        String reqUri = req.getRequestURI();
        System.out.println("winson servlet do get ......  " + reqUri);
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write("winson - ciwei - servlet - server ==> ||||" + reqUri);
        writer.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
