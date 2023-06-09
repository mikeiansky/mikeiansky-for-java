package com.winson.transaction;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AutoProxyRegistrar;
import org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration;

/**
 * @author mike ian
 * @date 2023/6/7
 * @desc
 **/
public class TransactionDemoV1 {

    public static void main(String[] args) {

        System.out.println("hello world");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(TransactionConfig.class);
//        context.register(ProxyTransactionManagementConfiguration.class);
//        context.register(AutoProxyRegistrar.class);
//        context.register(ProxyTransactionManagementConfiguration.class);

        context.refresh();

        TransactionConfig transactionConfig = context.getBean(TransactionConfig.class);
        System.out.println(transactionConfig);

        TransactionService transactionService = context.getBean(TransactionService.class);
//        System.out.println(transactionService);
        transactionService.hello("ciwei");

    }

}
