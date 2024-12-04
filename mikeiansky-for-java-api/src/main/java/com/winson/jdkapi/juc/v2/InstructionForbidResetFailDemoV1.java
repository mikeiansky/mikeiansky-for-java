package com.winson.jdkapi.juc.v2;

/**
 * @author winson
 * @date 2022/5/6
 * @desc 使用volatile，验证失败
 **/
public class InstructionForbidResetFailDemoV1 {

    static int a = 0;
    static int b = 0;
    static int x = 0;
    static int y = 0;
    static int temp = 0;
    static boolean start1 = false;
    static boolean start2 = false;
    static volatile int f = 0;

    public static void main(String[] args) throws InterruptedException {

        int i = 0;
        while (true) {
            i++;
            a = 0;
            b = 0;
            x = 0;
            y = 0;
            f = 0;
            temp = 0;
            start1 = false;
            start2 = false;

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    a = 1;
                    x = b;
                    start1 = true;
                    f = 1;
                }
            });
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
//                    f = 2;
                    // 如果仅仅是读取volatile 的数据并不会导致
                    while (f != 1){

                    }
//                    temp = f;
//                    int ttt = f;
                    b = 1;
                    y = a;
                    start2 = true;
//                    temp = ttt;
                }
            });

//            Thread t3 = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("t3 start ... ");
//                    while (true) {
////                        System.out.println("t3 start1 : " + start1+", start2 : " + start2);
//                        if (start1 && start2) {
//                            if (x == 0 && y == 0) {
//                                System.out.println("指令重排 i : " + i + " , x=0,y=0");
//                                return;
//                            } else {
//                                break;
//                            }
////                            System.out.println("t3 end ... ");
//                        } else {
////                            System.out.println("not all start ");
//                        }
//                    }
//                    System.out.println("t3 end ... ");
//                }
//            });

            t1.start();
            t2.start();
//            t3.start();

            System.out.println("start ... i : " + i);
            while (true) {
                if (start1 && start2) {
                    if (x == 0 && y == 0) {
                        System.out.println("指令重排 i : " + i + " , x=0,y=0, temp:" + temp);
                        return;
                    } else {
                        System.out.println("break .... ");
                        break;
                    }
                } else {
                    // 需要一条指令，避免编译优化一些东西
                    int c = 0;
//                    System.out.println("no match and wait ... ");
                }
            }
            System.out.println("end ... i : " + i);

            t1.join();
            t2.join();
//            t3.join();
        }

    }

}
