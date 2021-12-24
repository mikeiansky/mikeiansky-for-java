package com.winson.temp;

import java.io.IOException;

/**
 * @author winson
 * @date 2021/12/24
 **/
public class JNDIDemo {

    public static void main(String[] args) {

        try {
            Runtime.getRuntime().exec("calc");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
