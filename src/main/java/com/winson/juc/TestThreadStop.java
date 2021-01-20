package com.winson.juc;

import org.junit.Test;

/**
 * @author winson
 * @date 2021/1/20
 * @desc 测试Thread的 stop 方法， stop方法和interrupted方法的区别是，
 **/
public class TestThreadStop {

    public synchronized void read(int id) {
        System.out.println("id : " + id + " , read start");
        if(id == 1){
            while (true){}
        }else{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("id : " + id + " , read end");
    }

    @Test
    public void testStop() {

    }


    public static void main(String[] args) {

    }


}
