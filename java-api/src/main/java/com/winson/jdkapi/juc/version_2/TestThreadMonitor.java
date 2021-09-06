package com.winson.jdkapi.juc.version_2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author winson
 * @date 2021/5/7
 **/
public class TestThreadMonitor {

    public static class Account {

        private ReentrantLock lock = new ReentrantLock();
        private Condition notEmpty = lock.newCondition();
        private Condition notFull = lock.newCondition();
        private List<Integer> coll = new ArrayList<>();

        private void addAccount(int ele){
            lock.lock();
            try {
                while (!coll.isEmpty()){
                    notEmpty.await();
                }
//                System.out.println("is empty ... ");

                coll.add(ele);

                notFull.signalAll();

            }catch (Exception e){
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

        private void removeAccount(){
            lock.lock();
            try {
                while (coll.isEmpty()){
                    notFull.await();
                }
                coll.remove(coll.get(0));
                notEmpty.signalAll();
            }catch (Exception e){
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void print(){
            System.out.println("coll size : " + coll.size());
        }

    }

    public static void main(String[] args) {
        System.out.println("test thread monitor start ... ");
        int size = 100000;
        final Account account = new Account();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < size; i++) {
                    account.addAccount(i);
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < size; i++) {
                    account.removeAccount();
                }
            }
        });
        t2.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        account.print();
        System.out.println("test thread monitor end ... ");
    }

}
