package com.winson.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

/**
 * @author mike ian
 * @date 2023/6/7
 * @desc
 **/
@Service
public class SimpleTXManager implements PlatformTransactionManager {

    @Override
    public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
        System.out.println("getTransaction : " + definition);
        return null;
    }

    @Override
    public void commit(TransactionStatus status) throws TransactionException {
        System.out.println("commit : " + status);
    }

    @Override
    public void rollback(TransactionStatus status) throws TransactionException {
        System.out.println("rollback : " + status);
    }

}
