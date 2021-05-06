package com.winson.juc.version_2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winson
 * @date 2021/5/6
 * @desc 破坏占用且等待条件来解除死锁，也就是一次性申请所有需要的资源，否则不继续
 **/
public class TestAccountNoDeadLockApplyAllResource {

    public static class Account {

        public int balance;

        public Account(int balance) {
            this.balance = balance;
        }

        public void transfer(Account target, int amt) {
            synchronized (this) {
                synchronized (target){
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }

    }

    public static class AccountManager {

        public List<Account> accountList = new ArrayList<>();

        public boolean applyResource(Account from, Account to) {
            synchronized (this) {
                if (!accountList.contains(from) && !accountList.contains(to)) {
                    accountList.add(from);
                    accountList.add(to);
                    return true;
                }
            }
            return false;
        }

        public void freeResource(Account from, Account to) {
            synchronized (this) {
                accountList.remove(from);
                accountList.remove(to);
            }
        }

        public void transfer(Account from, Account to, int amt) {
            while (!(applyResource(from, to))) ;
            from.transfer(to, amt);
//            System.out.println("transfer --- ");
            freeResource(to, from);
        }

    }

    public static void main(String[] args) {
        final Account from = new Account(100000);
        final Account to = new Account(100000);
        final AccountManager am = new AccountManager();
        final int size = 1000000;
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < size; i++) {
                am.transfer(from, to, 1);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < size; i++) {
                am.transfer(to, from, 1);
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
