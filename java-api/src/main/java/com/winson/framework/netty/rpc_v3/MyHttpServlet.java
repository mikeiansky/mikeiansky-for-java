package com.winson.framework.netty.rpc_v3;


import com.winson.utils.common.SerUtil;
import io.netty.handler.codec.http.HttpResponseStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author winson
 * @date 2021/8/31
 **/
public class MyHttpServlet extends HttpServlet {

    public static MyNettyRpcV3.RequestDispatch dispatch;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        System.out.println("http req : " + req);
        int contentLength = Integer.parseInt(req.getHeader("content-length"));
        byte[] content = new byte[contentLength];
        req.getInputStream().read(content);

        MyNettyRpcV3.MyContent reqContent = SerUtil.decode(content, MyNettyRpcV3.MyContent.class);

        System.out.println("req test ----> content : " + reqContent);

        Object target = dispatch.getDispatch(reqContent.getName());

        String res = null;
        try {
            Method method = target.getClass().getMethod(reqContent.getMethod(), reqContent.getParamTypes());
            res = (String) method.invoke(target, reqContent.getArgs());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        MyNettyRpcV3.MyContent resContent = new MyNettyRpcV3.MyContent();
        resContent.setContent(res);

        byte[] rcbs = SerUtil.encode(resContent);

//        resp.setStatus(HttpResponseStatus.OK.code());
//        resp.setContentLength(rcbs.length);
        System.out.println("server response content length ------->>>>> " + rcbs.length);

        ObjectOutputStream oos = new ObjectOutputStream(resp.getOutputStream());
        oos.writeObject(resContent);

//        resp.getOutputStream().write(rcbs);

    }
}