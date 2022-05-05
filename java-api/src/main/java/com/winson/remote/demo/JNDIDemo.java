package com.winson.remote.demo;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

/**
 * @author winson
 * @date 2021/12/27
 **/
public class JNDIDemo {

    public static void main(String[] args) throws NamingException {

        // 创建环境变量对象
        Hashtable<String, String> env = new Hashtable<String, String>();

        // 设置JNDI初始化工厂类名
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");

        // 设置JNDI提供服务的URL地址
        env.put(Context.PROVIDER_URL, "dns://114.114.114.114");

        // 创建JNDI目录服务对象
        DirContext context = new InitialDirContext(env);


//        System.out.println(context.getAttributes(Context.PROVIDER_URL));

//        System.out.println(context.lookup("com.sun.jndi.dns.DnsContextFactory"));

        try {
            // 获取DNS解析记录测试
            Attributes attrs1 = context.getAttributes("baidu.com", new String[]{"A"});
            Attributes attrs2 = context.getAttributes("qq.com", new String[]{"MX"});

            System.out.println(attrs1);
            System.out.println(attrs2);
            System.out.println(attrs1.get("A"));
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

}
