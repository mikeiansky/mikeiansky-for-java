package com.winson.jdkapi.juc.v1.version_2;

/**
 * @author winson
 * @date 2021/5/6
 * @desc 破坏循环等待的方式来避免死锁
 **/
public class ThreadAccountNoDeadLockBreakLoopWait {

    public static class Account{
        public int id;
        public int balance;

        public Account(int id, int balance) {
            this.id = id;
            this.balance = balance;
        }

        public void transfer(Account target, int amt){
            Account first = this;
            Account second = target;
            if(first.id > target.id){
                first = target;
                second = this;
            }
            synchronized (first){
                synchronized (second){
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }

    public static void main(String[] args) {
        final Account a1 = new Account(1,2222);
        final Account a2 = new Account(2, 33333);
        final int size = 1000000;

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < size; i++) {
                a1.transfer(a2, 2);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < size; i++) {
                a2.transfer(a1, 3);
            }
        });

        t1.start();
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

        System.out.println("transfer end ... ");
    }

}
