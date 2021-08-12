package com.winson.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author winson
 * @date 2021/8/12
 **/
public class IODemoV1 {

    public static void main(String[] args) throws IOException {
        // hello'中文孤独，你好'end
        String path = "D:\\work\\java\\winson-for-java\\src\\main\\java\\com\\winson\\io\\IODemoV1.java";
        File file = new File(path);
        FileInputStream in = new FileInputStream(file);
//        byte[] buf = new byte[in.available()];
//        in.read(buf);
//        System.out.println(new String(buf));
//        System.out.println(in.available());

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = null;
        while ((line = reader.readLine()) != null){
            System.out.println(line);
        }
    }

}
