package com.winson.protocol.http;

import okhttp3.*;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author winson
 * @date 2021/8/12
 **/
public class HttpClientDemoV2 {

    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                RequestBody body = RequestBody.create(mediaType, "name="+URLEncoder.encode("中", "gbk"));
        Request request = new Request.Builder()
                .url("http://localhost:8001")
//                .url("http://localhost:8090/winson_study_spring_mvc_v1_war_exploded/name.do")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
//                .addHeader("Accept-Charset", "utf-8")
                .addHeader("Accept-Charset", "gbk")
                .build();
        Response response = client.newCall(request).execute();

    }

}
