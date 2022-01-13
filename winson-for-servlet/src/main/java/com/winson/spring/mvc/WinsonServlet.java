package com.winson.spring.mvc;

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
public class WinsonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置响应给页面的格式、字符集
//        System.out.println("winson servlet do get ...... ");
//        resp.setContentType("text/html;charset=UTF-8");
//        PrintWriter writer = resp.getWriter();
//        writer.write("winson --- C语言中文网，c.biancheng.net");
//        writer.close();

        System.out.println("WinsonServlet --> doGet ");
        req.setAttribute("winson-name","from-winson-servlet");
        req.getRequestDispatcher("ciwei333").forward(req,resp);
//        req.getRequestDispatcher("ciwei444").include(req,resp);
//        req.getRequestDispatcher("/ciwei555").include(req,resp);
//        req.getRequestDispatcher("/ciwei222").forward(req,resp);
//        req.getRequestDispatcher("ciwei222").forward(req,resp);
//        req.getServletContext().getRequestDispatcher("/ciwei222").forward(req,resp);
//        req.getServletContext().getRequestDispatcher("/winson-servlet-demo2/ciwei222").forward(req,resp);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
