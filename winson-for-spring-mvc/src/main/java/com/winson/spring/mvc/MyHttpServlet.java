package com.winson.spring.mvc;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author winson
 * @date 2022/1/10
 **/
public class MyHttpServlet extends HttpServlet {

    static {
        System.out.println("MyHttpServlet static init ...... ");
    }

    public MyHttpServlet(){
        super();
        System.out.println("MyHttpServlet construct init ...... ");
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
//        doNormal(req, resp);
//        doCookie(req, resp);
        doSession(req, resp);
    }

    private void doSession(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");

        HttpSession session = req.getSession();

        System.out.println("session.getCreationTime() : " + session.getCreationTime());
        System.out.println("session.getId() : " + session.getId());
        System.out.println("session.getLastAccessedTime() : " + session.getLastAccessedTime());
        System.out.println("session.getMaxInactiveInterval() : " + session.getMaxInactiveInterval());
        System.out.println("session.getServletContext() : " + session.getServletContext());

//        session.setMaxInactiveInterval(1);

        resp.getWriter().write("hello session!");
        resp.getWriter().close();

//        resp.sendError(777, "winson-error");
    }

    private String getCookieString(Cookie cookie) {
        return cookie.getName() + ":" + cookie.getPath() + ":" + cookie.getValue();
    }

    private void doCookie(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");

        Arrays.stream(req.getCookies())
                .map(this::getCookieString)
                .forEach(System.out::println);

        Cookie cookie = new Cookie("winson-company", "ciwei-big");
        resp.addCookie(cookie);

        resp.getWriter().write("hello winson");
        resp.getWriter().close();
    }

    private void doNormal(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 设置响应给页面的格式、字符集
        System.out.println("MyHttpServlet doGet ...... ");
//        resp.setContentType("text/html;charset=GBK");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
//        writer.write("你好中文 00005 --- "+"\r\n");
//        resp.setContentType("text/html;charset=GBK");
//        resp.setContentType("text/html;charset=utf-8");
//        writer.close();

        resp.setContentType("text/html;charset=GBK");
//        resp.setContentType("text/html;charset=utf-8");

        System.out.println("req.getContextPath():" + req.getContextPath());
        System.out.println("req.getServletPath():" + req.getServletPath());
        System.out.println("req.getMethod():" + req.getMethod());
        System.out.println("req.getRequestURL():" + req.getRequestURL());
        System.out.println("req.getQueryString():" + req.getQueryString());
        System.out.println("req.getServletContext():" + req.getServletContext());
        System.out.println("req.getServerName():" + req.getServerName());
        System.out.println("req.getRemoteAddr():" + req.getRemoteAddr());
        System.out.println("req.getRemoteHost():" + req.getRemoteHost());
        System.out.println("winson-name:" + req.getAttribute("winson-name"));
//        writer.write("你好中文 00001 --- "+"\r\n");
//        resp.setHeader("Content-Type","text/html;charset=gbk");
//        resp.setContentType("text/html;charset=gbk");
//        resp.setHeader("Content-Type","text/html;charset=utf-8");
//        resp.setContentType("text/html;charset=utf-8");

        String str = "中文";

        resp.addHeader("winson-heard", "winson-head1");
        resp.addHeader("winson-heard", "winson-head2");
        resp.addHeader("winson-heard", "winson-head3");
        resp.setHeader("winson-heard", "winson-head001");
        System.out.println("Charset.defaultCharset().name():" + Charset.defaultCharset().name());
//        resp.getOutputStream().write(str.getBytes(StandardCharsets.UTF_8));
//        resp.getOutputStream().write(str.getBytes());

        System.out.println("------buf1-----");
        byte[] buf1 = str.getBytes();
        for (byte b : buf1) {
            System.out.println(Integer.toHexString(b));
        }

        System.out.println("------buf2-----");
        byte[] buf2 = str.getBytes(StandardCharsets.UTF_8);
        for (byte b : buf2) {
            System.out.println(Integer.toHexString(b));
        }

//        resp.getOutputStream().close();

        resp.sendRedirect("http://www.baidu.com");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);

    }

}
