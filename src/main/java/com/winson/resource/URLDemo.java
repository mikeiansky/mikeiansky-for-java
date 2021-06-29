package com.winson.resource;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author winson
 * @date 2021/6/29
 **/
public class URLDemo {

    public static void main(String[] args) throws MalformedURLException {
        URL url1 = new URL("http://www.baidu.com:453/user/account/detail?name=winson&age=33");
        printURLInfo(url1,"url1");
        URL url2 = new URL("file://www.baidu.com:453/user/account/detail?name=winson&age=33");
        printURLInfo(url2,"url2");
        URL url3 = new URL("file://www.baidu.com:453/user/account/detail?name=winson&age=33");
        printURLInfo(url3,"url3");
        URL url4 = new URL("http:///www.baidu.com:453/user/account/detail?name=winson&age=33");
        printURLInfo(url4,"url4");
        URL url5 = new URL("http:/www.baidu.com:453/user/account/detail?name=winson&age=33");
        printURLInfo(url5,"url5");
        URL url6 = new URL("http:////www.baidu.com:453/user/account/detail?name=winson&age=33");
        printURLInfo(url6,"url6");
        URL url7 = new URL("http://///www.baidu.com:453/user/account/detail?name=winson&age=33");
        printURLInfo(url7,"url7");
    }

    public static void printURLInfo(URL url, String source) {
        System.out.println("========= " + source + " ==========");
        System.out.println("origin:" + url);
        System.out.println("protocol:" + url.getProtocol());
        System.out.println("host:" + url.getHost());
        System.out.println("path:" + url.getPath());
        System.out.println("port:" + url.getPort());
        System.out.println("defaultPort:" + url.getDefaultPort());
        System.out.println("query:" + url.getQuery());
        System.out.println("authority:" + url.getAuthority());
        System.out.println("file:" + url.getFile());
        System.out.println("ref:" + url.getRef());
        System.out.println("userInfo:" + url.getUserInfo());
    }

}
