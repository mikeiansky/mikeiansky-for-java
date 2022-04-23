package com.winson.springboot.demo;

import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.ArrayList;

/**
 * @author winson
 * @date 2022/3/1
 **/
@Service
//@MyAnnotation
public class MyService {

    public void function1() {
        function2();
    }

    public void function2() {
        function3();
    }

    public void function3() {
        String a = "a";
        String b = null;
        String c = "c";
        System.out.println(a.length());
        boolean isTrue = true;
        if(isTrue){
            throw new IllegalArgumentException("test");
        } else {
            System.out.println(b.length());
        }
        System.out.println(c.length());
    }

    public void function() {
        function1();
    }

    @Transactional
    public void doTransaction(){
        System.out.println("doTransaction =====> start");
//        System.out.println(TransactionSynchronizationManager.getResourceMap().keySet());
//        System.out.println("Threadname : " + Thread.currentThread().getName());
        String key = "HikariDataSource (HikariPool-1)";
        Object source = new ArrayList<>(TransactionSynchronizationManager.getResourceMap().values()).get(0);
//        System.out.println(source);
        ConnectionHolder connectionHolder = (ConnectionHolder) source;
        System.out.println(connectionHolder.getConnection());
//        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
//            @Override
//            public void afterCommit() {
//                System.out.println("doTransaction =====> afterCommit");
//            }
//        });
        System.out.println("doTransaction =====> end");
    }

}
