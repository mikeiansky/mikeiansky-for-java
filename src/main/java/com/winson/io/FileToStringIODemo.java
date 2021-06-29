package com.winson.io;

import java.io.*;
import java.net.URL;

/**
 * @author winson
 * @date 2021/6/29
 **/
public class FileToStringIODemo {

    public static void main(String[] args) throws IOException {
        String filePath = "D:\\work\\java\\winson-for-java\\src\\main\\java\\com\\winson\\io\\FileToStringIODemo.java";
        File file = new File(filePath);

        FileInputStream fin = new FileInputStream(file);

        BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine() ) != null ){
            sb.append(line);
            sb.append("\n");
        }
        System.out.println(sb);
        fin.close();
    }


}
