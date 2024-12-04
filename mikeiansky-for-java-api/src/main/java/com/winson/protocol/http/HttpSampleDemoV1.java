package com.winson.protocol.http;


import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author winson
 * @date 2022/2/21
 **/
public class HttpSampleDemoV1 {

    public static void main(String[] args) {

        String urlStr = "https://img.shixijob.net//Uploads/202202/20/fbdd1e00-9264-11ec-b57b-171b1d7df001.png?imageView2/1/w/200/h/200";

        String base64Data = getBase64Data(urlStr);
        System.out.println(base64Data);

    }

    public static String getBase64Data(String urlStr){
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            InputStream in = connect.getInputStream();
            byte[] buf = new byte[1024];
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int readLength = 0;
            while ((readLength = in.read(buf,0, 1024)) > 0){
                out.write(buf, 0, readLength);
            }
            byte[] allByte = out.toByteArray();
            out.flush();
            out.close();

            String base64Str = Base64.getUrlEncoder().encodeToString(allByte);
            return "data:image/png;base64," + base64Str;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
