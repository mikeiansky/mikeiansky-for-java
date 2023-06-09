package com.winson.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author mike ian
 * @date 2023/6/7
 * @desc
 **/
@Service
public class TransactionService {

    @Transactional
    public String hello(String message) {
        System.out.println("hello : " + message);
        return "change : " + message;
    }

}
