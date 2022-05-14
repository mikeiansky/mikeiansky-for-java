package com.winson.advanced.concurrency;


import java.util.concurrent.*;

/**
 * @author winson
 * @date 2022/5/12
 **/
public class ForkJoinDemoV1 {

    public static class Fibonacci extends RecursiveTask<Integer> {
        final int n;

        public Fibonacci(int n) {
            this.n = n;
        }


        @Override
        protected Integer compute() {
            if(n <= 1){
                return n;
            }
            Fibonacci f1 = new Fibonacci(n-1);
            f1.fork();
            Fibonacci f2 = new Fibonacci(n-2);
            return f1.join() + f2.compute();
        }

    }

    public static void main(String[] args) {

        Fibonacci fibonacci = new Fibonacci(9);
        Executor executor = Executors.newFixedThreadPool(10);
        ForkJoinPool fjp = new ForkJoinPool(5);
        System.out.println(fjp.invoke(fibonacci));

    }

}
