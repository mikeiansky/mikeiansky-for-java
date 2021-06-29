package com.winson.resource;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author winson
 * @date 2021/6/29
 **/
public class URIDemo {

    public static void main(String[] args) throws URISyntaxException {
        URI uri1 = new URI("http://www.baidu.com:448/user/account?name=winson&age=78");
        printUriInfo(uri1, "uri1");
        URI uri2 = new URI("winson://www.baidu.com:448/user/account?name=winson&age=78");
        printUriInfo(uri2, "uri2");
        URI uri3 = new URI("winson:/www.baidu.com:448/user/account?name=winson&age=78");
        printUriInfo(uri3, "uri3");
        URI uri4 = new URI("winson:///www.baidu.com:448/user/account?name=winson&age=78");
        printUriInfo(uri4, "uri4");
        URI uri5 = new URI("winson:////www.baidu.com:448/user/account?name=winson&age=78");
        printUriInfo(uri5, "uri5");
        URI uri6 = new URI("winson://///www.baidu.com:448/user/account?name=winson&age=78");
        printUriInfo(uri6, "uri6");
    }

    public static void printUriInfo(URI uri, String source) {
        System.out.println("======== " + source + " =========");
        System.out.println("origin: " + uri);
        System.out.println("scheme: " + uri.getScheme());
        System.out.println("host: " + uri.getHost());
        System.out.println("port: " + uri.getPort());
        System.out.println("path: " + uri.getPath());
        System.out.println("rawPath: " + uri.getRawPath());
        System.out.println("query: " + uri.getQuery());
        System.out.println("rawQuery: " + uri.getRawQuery());
        System.out.println("authority: " + uri.getAuthority());
        System.out.println("rawAuthority: " + uri.getRawAuthority());
        System.out.println("fragment: " + uri.getFragment());
        System.out.println("userInfo: " + uri.getUserInfo());
    }

}
