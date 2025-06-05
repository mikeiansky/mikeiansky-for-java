package io.github.mikeiansky.java.concurrent.info;

/**
 * @author mike ian
 * @date 2025/6/5
 * @desc
 **/
public class ThreadInfoDemo {

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
                System.out.println(threadGroup);
            }
        }).start();
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        System.out.println(threadGroup);

    }

}
