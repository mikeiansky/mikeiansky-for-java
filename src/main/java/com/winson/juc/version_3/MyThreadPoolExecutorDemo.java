package com.winson.juc.version_3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * @author winson
 * @date 2021/6/22
 **/
public class MyThreadPoolExecutorDemo {

    private static class ThreadPoolRunnable implements Runnable {

        private boolean cancel = false;

        private final BlockingQueue<Runnable> queue;

        private final Runnable initRunnable;

        private long lastRunningTime = 0L;

        private int state;

        public int getState(){
            return state;
        }

        private ThreadPoolRunnable(Runnable initRunnable, BlockingQueue<Runnable> queue) {
            this.initRunnable = initRunnable;
            this.queue = queue;
        }

        public void cancel() {
            cancel = true;
//            Thread.currentThread().interrupt();
        }

        @Override
        public void run() {
            lastRunningTime = System.currentTimeMillis();
            state = 1;
            initRunnable.run();
            while (!cancel) {
                try {
                    state = 0;
                    Runnable runnable = queue.take();
                    lastRunningTime = System.currentTimeMillis();
                    state = 1;
                    runnable.run();
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            }
            state = 2;
        }

    }

    public static class MyThreadPoolExecutor {

        private int coreSize;

        private int maximumSize;

        private int timeToLive;

        private TimeUnit timeUnit;

        private BlockingQueue<Runnable> queue;

        private ThreadFactory threadFactory;

        private Consumer<Runnable> rejectHandler;

        private Vector<Thread> threads;

        private ConcurrentHashMap<Thread, ThreadPoolRunnable> poolRunnableMap = new ConcurrentHashMap<>();

        private Thread checkThread;

        private boolean checkThreadCancel = false;

        public MyThreadPoolExecutor(int coreSize, int maximumSize, int timeToLive, TimeUnit timeUnit, BlockingQueue<Runnable> queue, ThreadFactory threadFactory, Consumer<Runnable> rejectHandler) {
            this.coreSize = coreSize;
            this.maximumSize = maximumSize;
            this.timeToLive = timeToLive;
            this.timeUnit = timeUnit;
            this.queue = queue;
            this.threadFactory = threadFactory;
            this.rejectHandler = rejectHandler;
            threads = new Vector<>(Math.round(coreSize * 4 / 3f));
            checkThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!checkThreadCancel){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                        System.out.println("check thread run ... ");
                        long liveTime = timeUnit.toMillis(timeToLive);
                        long currentTime = System.currentTimeMillis();
                        List<Thread> needRecycleThreads = new ArrayList<>();
                        Iterator<Thread> iterable = threads.iterator();
//                        System.out.println("threads size : " + threads.size());
                        while (iterable.hasNext()){
                            Thread thread = iterable.next();
//                            Thread.State state = thread.getState();
                            ThreadPoolRunnable target = poolRunnableMap.get(thread);
                            if(target != null){
                                if(target.getState() == 0){
//                                    System.out.println("thread name : " + thread.getName() + " , runtime : " + ((currentTime - target.lastRunningTime)) + " , live time : " + liveTime);
                                    if((currentTime - target.lastRunningTime) > liveTime){
                                        needRecycleThreads.add(thread);
                                    }
                                }
                            }
                        }
//                        System.out.println("needRecycleThreads size : " + needRecycleThreads.size());

                        int allSize = threads.size();
                        if(allSize > coreSize){
                            int recycleSize = allSize - coreSize;
                            Iterator<Thread> iterator = needRecycleThreads.iterator();
                            while (recycleSize> 0 && iterator.hasNext()){
                                Thread thread = iterator.next();
                                System.out.println("回收线程：" + thread.getName());
                                threads.remove(thread);
                                ThreadPoolRunnable target = poolRunnableMap.remove(thread);
                                if(target != null){
                                    target.cancel();
                                }
                                thread.interrupt();
                                recycleSize--;
                            }
                        }

                    }
                }
            });
            checkThread.start();
        }

        public void cancelCheckThread(){
            checkThreadCancel = true;
            checkThread.interrupt();
        }

        public void execute(final Runnable runnable) {
            int size = threads.size();
            if (size < coreSize) {
                ThreadPoolRunnable poolRunnable = new ThreadPoolRunnable(runnable, queue);
                Thread thread = threadFactory.newThread(poolRunnable);
                threads.add(thread);
                thread.start();
                poolRunnableMap.put(thread, poolRunnable);
            } else {
                ThreadPoolRunnable poolRunnable = new ThreadPoolRunnable(runnable, queue);
                Thread thread = threadFactory.newThread(poolRunnable);
                threads.add(thread);
                thread.start();
                poolRunnableMap.put(thread, poolRunnable);
//                try {
//                    queue.put(runnable);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }

    }

    public static void main(String[] args) {
        MyThreadPoolExecutor executor = new MyThreadPoolExecutor(
                2, 10, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1),
                Executors.defaultThreadFactory(), null);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("my runnable run at thread : " + Thread.currentThread().getName());
            }
        });
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("my runnable run at thread : " + Thread.currentThread().getName());
            }
        });
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("my runnable run at thread : " + Thread.currentThread().getName());
            }
        });
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("my runnable run at thread : " + Thread.currentThread().getName());
            }
        });

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
