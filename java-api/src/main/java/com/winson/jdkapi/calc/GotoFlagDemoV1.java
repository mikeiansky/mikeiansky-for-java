package com.winson.jdkapi.calc;

/**
 * @author winson
 * @date 2022/6/9
 **/
public class GotoFlagDemoV1 {

    public static void main(String[] args) {

        retry:
        for (int i = 0; i < 10; i++) {
//            System.out.println("i : " + i);
            for (int j = 0; j < 5; j++) {
                System.out.println("i : " + i + " , j : " + j);
                if(i == 2 && j == 2){
                    continue retry;
                }
            }

        }

        System.out.println("main end ... ");

    }

}
