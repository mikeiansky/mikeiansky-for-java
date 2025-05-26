package io.github.mikeiansky.java.concurrent.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author mike ian
 * @date 2025/5/26
 * @desc
 **/
public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("task thread : " + Thread.currentThread().getName());
                System.out.println("task is running");
                return 0;
            }
        });
        task.run();
        Integer ret = task.get();
        System.out.println("task return : " + ret);
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        Integer absent = map.putIfAbsent(1, 1);
        System.out.println("putIfAbsent absent : " + absent);
        absent = map.putIfAbsent(1, 2);
        System.out.println("putIfAbsent absent : " + absent);
        absent = map.putIfAbsent(1, 3);
        System.out.println("putIfAbsent absent : " + absent);

    }

}
