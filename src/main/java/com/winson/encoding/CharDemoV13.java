package com.winson.encoding;

import java.io.*;

/**
 * @author winson
 * @date 2021/8/13
 **/
public class CharDemoV13 {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        // 设置文件输入流地址
        FileInputStream in = new FileInputStream("");

        // 设置输入流读取数据时使用的编码格式
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));


    }

}
