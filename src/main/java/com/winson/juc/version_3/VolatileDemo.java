package com.winson.juc.version_3;

/**
 * @author winson
 * @date 2021/6/18
 **/
public class VolatileDemo {

    public static class UnsafeConfig {

        private String config;

        private volatile boolean init;

        public void setConfig() {
            config = "hello";
            init = true;
        }

        public String getConfig() {
            while (!init) {

            }
            return config;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("volatile demo start ... ");
        boolean run = true;
        while (run) {
            final UnsafeConfig unsafeConfig = new UnsafeConfig();
            Thread t1 = new Thread(() -> {
                String config = unsafeConfig.getConfig();
                if (config == null) {
                    System.out.println("指令重排，且顺序错乱");
                }
                System.out.println("t1 end .");
            });
            Thread t2 = new Thread(() -> {
                unsafeConfig.setConfig();
                System.out.println("t2 end ...");
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println("正常执行 1");
        }
        System.out.println("volatile demo end ... ");
    }

}
