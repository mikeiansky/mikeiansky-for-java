package com.winson.geo;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author com.winson
 * @date 2020/12/18
 **/
public class TestGaodeGeo {

    public static void main(String[] args) {
        // https://restapi.amap.com/v3/geocode/geo?address=北京市朝阳区阜通东大街6号&output=XML&key=<用户的key>
//        String url = "https://restapi.amap.com/v3/geocode/geo?key=9f7750e0449e313463cfad1e848f2003&address=黑龙江";
//        System.out.println(url);
//        download(url);

//        StringBuilder sb = new StringBuilder("winson");
//        sb.append("ddd::");
//        System.out.println(sb.toString());

        List<Integer> idList = null;
        List result =  Optional.ofNullable(idList).map(idList2->{
            System.out.println("2222");
            return new ArrayList();
        }).orElse(new ArrayList());
        System.out.println(result);

    }

    public static void download(String urlPath) {
        try {
            URL url = new URL(urlPath);
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(3 * 1000);
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            InputStream str = conn.getInputStream();
            byte[] bytes = new byte[0];
            bytes = new byte[str.available()];
            str.read(bytes);
            String result = new String(bytes);
            System.out.println(result);
            str.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
