package com.winson.remote.demo.rmi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author winson
 * @date 2021/12/29
 **/
public class DownloadTest {

    public static void main(String[] args) throws IOException {

        String url = "http://172.16.2.113:8180/ExecTemplateJDK8";
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        InputStream in = connection.getInputStream();
        System.out.println("total length : " + in.available());
        FileOutputStream out = new FileOutputStream("D:\\test.class");

        int length = 0;
        byte[] buf = new byte[1024];
        while ((length = in.read(buf)) > 0) {
//                System.out.println("read length : " + length);
            out.write(buf, 0, length);
            out.flush();

        }
        out.close();

    }

}
