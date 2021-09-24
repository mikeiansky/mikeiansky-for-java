package com.winson.utils.reptile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author winson
 * @date 2021/9/23
 **/
public class AiqichaUtilsDemoV1 {

    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://aiqicha.baidu.com/s?q=%E5%B9%BF%E5%B7%9E%E5%B8%82%E4%B9%90%E6%9C%89%E5%AE%B6%E6%88%BF%E4%BA%A7%E7%BB%8F%E7%BA%AA%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8%E7%8C%8E%E5%BE%B7%E5%A4%A7%E9%81%93%E7%AC%AC%E4%B8%80%E5%88%86%E5%85%AC%E5%8F%B8&t=0")
                .method("GET", null)
                .addHeader("Cookie", "BAIDUID=BD286F6292080FF180913DA0AB436351:FG=1")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());

    }

}
