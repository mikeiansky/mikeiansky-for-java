package com.winson.remote.demo.rmi;

import java.io.IOException;

/**
 * @author winson
 * @date 2021/12/28
 **/
public class CmdDemo {

    public static void main(String[] args) {
        try {
            Runtime.getRuntime().exec("mkdir cw01");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
