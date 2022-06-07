package com.winson.jdkapi.juc.v1.version_3;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author winson
 * @date 2021/6/22
 **/
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 2,
                TimeUnit.SECONDS, new SynchronousQueue<>(),
                new ThreadFactory() {
                    private final AtomicLong id = new AtomicLong();

                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r,"winson-thread-" + id.getAndIncrement());
                    }
                },
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("custom rejected handle ... : " + Thread.currentThread().getName());
                        r.run();
                    }
                });

        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread : " + Thread.currentThread().getName() + " , execute ...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread : " + Thread.currentThread().getName() + " , end ...");
            }
        });
        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread : " + Thread.currentThread().getName() + " , execute ...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread : " + Thread.currentThread().getName() + " , end ...");
            }
        });
        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread : " + Thread.currentThread().getName() + " , execute ...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread : " + Thread.currentThread().getName() + " , end ...");
            }
        });
        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread : " + Thread.currentThread().getName() + " , execute ...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread : " + Thread.currentThread().getName() + " , end ...");
            }
        });
        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread : " + Thread.currentThread().getName() + " , execute ...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread : " + Thread.currentThread().getName() + " , end ...");
            }
        });
        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread : " + Thread.currentThread().getName() + " , execute ...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread : " + Thread.currentThread().getName() + " , end ...");
            }
        });

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread : " + Thread.currentThread().getName() + " , execute ...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread : " + Thread.currentThread().getName() + " , end ...");
            }
        });


    }

}
