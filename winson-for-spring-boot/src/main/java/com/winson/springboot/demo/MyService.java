package com.winson.springboot.demo;

import com.winson.lib.two.LibTwoManager;
import org.apache.logging.slf4j.SLF4JLoggerContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Reload4jLoggerAdapter;
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

    private Logger logger = LoggerFactory.getLogger(MyService.class);

    @Transactional
    public void doTransaction(){
//        Reload4jLoggerAdapter r = null;
        System.out.println("logger class : " + logger.getClass());
        logger.info("logger ----> doTransaction");

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

    public void testLibTwo(){
        LibTwoManager libTwoManager = new LibTwoManager();
        System.out.println("testLibTwo : " + libTwoManager);
        libTwoManager.doSomething();
        libTwoManager.doFour();
    }

}
