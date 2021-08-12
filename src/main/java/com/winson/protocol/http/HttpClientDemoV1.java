package com.winson.protocol.http;


import okhttp3.*;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author winson
 * @date 2021/8/12
 **/
public class HttpClientDemoV1 {

    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=gbk");
        RequestBody body = RequestBody.create(mediaType, "name=中");
        Request request = new Request.Builder()
                .url("http://localhost:8001")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk")
                .build();
        Response response = client.newCall(request).execute();
//        System.out.println(response.body().string());
        System.out.println("-----------");
        System.out.println(new String(response.body().bytes(), Charset.forName("gbk")));
    }

}
