package io.github.mikeiansky.java.base.jdk.thread;

/**
 * @author mike ian
 * @date 2024/12/26
 * @desc
 **/
public class DaemonThreadDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("daemon thread start ... ");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("daemon thread complete ... ");
            }
        });
        thread.setDaemon(true);
        thread.start();

        System.out.println("complete ... ");
    }

}
