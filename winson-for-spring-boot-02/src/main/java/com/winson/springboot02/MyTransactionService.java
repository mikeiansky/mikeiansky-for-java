package com.winson.springboot02;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author mike ian
 * @date 2023/6/6
 * @desc
 **/
@Slf4j
@Service
public class MyTransactionService {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Transactional
    public void hello(String message) {

        log.info("hello message : {}", message);

        // 获取相应数据源配置信息

//        System.out.println(transactionManager.getTransaction(null));
//        System.out.println(((DataSourceTransactionManager) transactionManager).getDataSource());
//        System.out.println(
//
//                TransactionSynchronizationManager.getResource(
//                        ((DataSourceTransactionManager) transactionManager).getDataSource()
//                )
//
//        );
//
//        ConnectionHolder connectionHolder = (ConnectionHolder)TransactionSynchronizationManager.getResource(
//                ((DataSourceTransactionManager) transactionManager).getDataSource()
//        );
//
//        System.out.println(connectionHolder.getConnection());
//        System.out.println(connectionHolder.getConnection());
//        System.out.println(connectionHolder.getConnection());
//        try {
//            connectionHolder.getConnection().commit();
//            System.out.println("commit 1");
//            connectionHolder.getConnection().commit();
//            System.out.println("commit 2");
//            connectionHolder.getConnection().commit();
//            System.out.println("commit 3");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

    }

}
