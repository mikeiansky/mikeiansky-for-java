package com.winson.exceptions;

/**
 * @author winson
 * @date 2021/10/9
 **/
public class TestExceptionFactory {

    public static void throwException() throws TestException {
        int temp1 = 1;
        int temp2 = 1;
        int temp3 = 1;
        int temp4 = 1;
        int temp5 = 1;
        int temp6 = 1;
        if(temp1 < Integer.MAX_VALUE){
            throw new TestException("this is test exception ");
        }
    }

}
