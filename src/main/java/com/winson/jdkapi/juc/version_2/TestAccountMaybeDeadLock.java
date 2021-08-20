package com.winson.jdkapi.juc.version_2;

/**
 * @author winson
 * @date 2021/5/6
 **/
public class TestAccountMaybeDeadLock {

    public static class Account {

        private int balance;

        public Account(int balance){
            this.balance = balance;
        }

        public void transfer(Account target, int amt){
            synchronized (this){
                // 这里可能会发生死锁
                synchronized (target){
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }

    }

    public static void main(String[] args){
        final Account zhangsan = new Account(100000);
        final Account lisi = new Account(200000);
        final int size = 1000000;
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < size; i++) {
                zhangsan.transfer(lisi, 2);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < size; i++) {
                lisi.transfer(zhangsan, 2);
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
        System.out.println("test end ... ");
    }

}
