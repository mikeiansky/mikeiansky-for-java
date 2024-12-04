package com.winson.jdkapi.juc.threadpool.v1;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

/**
 * @author winson
 * @date 2022/6/9
 **/
public class ExecutorDemoV1 {

    public static void main(String[] args) {

        Executor executor = new Executor() {
            @Override
            public void execute(@NotNull Runnable command) {
                System.out.println("executor execute command");
                command.run();
            }
        };

        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello executor!");
            }
        });

    }

}
