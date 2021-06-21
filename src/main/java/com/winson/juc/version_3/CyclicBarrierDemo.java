package com.winson.juc.version_3;

import java.util.concurrent.*;

/**
 * @author winson
 * @date 2021/6/21
 **/
public class CyclicBarrierDemo {

    public static class CalcParam {
        private int paramX;

        private int paramY;

        public int calc() {
            return paramX * paramY;
        }

        public void reset(){
            paramX = 0;
            paramY = 0;
        }
    }

    private Executor executor = Executors.newFixedThreadPool(2);
    private CalcParam calcParam = new CalcParam();

    private CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
        @Override
        public void run() {
            calc();
        }
    });

    public int getParamX() {
        return (int) (10000 * Math.random());
    }

    public int getParamY() {
        return (int) (20000 * Math.random());
    }

    public void getParams(){
        calcParam.reset();
        executor.execute(() -> {
            calcParam.paramX = getParamX();
            System.out.println("get x");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        executor.execute(() -> {
            calcParam.paramY = getParamY();
            System.out.println("get y");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
    }

    public void calc() {
        int result = calcParam.calc();
        System.out.println("calc result : " + result);
    }

    public static void main(String[] args) {
        final CyclicBarrierDemo demo = new CyclicBarrierDemo();
        for (int i = 0; i < 10; i++) {
            demo.getParams();
        }
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

}
