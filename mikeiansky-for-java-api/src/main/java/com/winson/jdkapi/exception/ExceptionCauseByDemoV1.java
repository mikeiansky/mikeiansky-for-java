package com.winson.jdkapi.exception;


/**
 * @author winson
 * @date 2021/10/9
 **/
public class ExceptionCauseByDemoV1 {

    public static void testException(int flag)  {
//        if(flag < 20){
            try {
                flag++;
//                testException(flag);

                nextedException(flag+1);

            }catch (Exception e){
                System.out.println("testException");

//                e.printStackTrace();
                // 这里需要将e包装出去，这样就可以知道出问题的地方了
                throw new IllegalArgumentException("exception on testException", e);
            }
//        } else {
//            throw new NullPointerException("exception on testException");
//        }
    }

    public static void nextedException(int flag)  {
        if(flag >= 20){
            try {
                int zero = 0;
                int temp = flag/zero;
//                TestExceptionFactory.throwException();
            }catch (Exception e){
                System.out.println("nextedException");
//                e.printStackTrace();
                // 这里需要将e包装出去，这样就可以知道出问题的地方了
                throw new IllegalArgumentException("exception on nextedException", e);
            }
        }
    }

    public static void main(String[] args)  {
        int flag = 100;

        testException(flag);

    }

}
