package com.winson.jdkapi.juc.version_3;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author winson
 * @date 2021/6/23
 **/
public class FortJoinFibonacciDemo {

    static class Fibonacci extends RecursiveTask<Integer> {

        private int num;

        public Fibonacci(int num) {
            this.num = num;
        }

        @Override
        protected Integer compute() {

            if(num<=1){
                return num;
            }
            Fibonacci f1 = new Fibonacci(num -1 );
            f1.fork();
            Fibonacci f2 = new Fibonacci(num -2 );
            return f2.compute() + f1.join();
        }

    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci(10);
        ForkJoinPool forkJoinPool = new ForkJoinPool(5);
        int result = forkJoinPool.invoke(fibonacci);
        System.out.println(result);
    }

}
