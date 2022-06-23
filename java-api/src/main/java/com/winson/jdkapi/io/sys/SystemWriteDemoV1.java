package com.winson.jdkapi.io.sys;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author winson
 * @date 2022/6/23
 **/
public class SystemWriteDemoV1 {

    public static void main(String[] args) throws IOException {
        String raw = "\033[0m BLABLA \033[0m\n";
        String raw2 = "\u001B[32mBLABLA\u001B[32m";

        System.out.write(raw.getBytes());
        System.out.println(raw2);
//        System.out.println((char)27 + "[31mThis text would show up red" + (char)27 + "[0m");


    }

}
