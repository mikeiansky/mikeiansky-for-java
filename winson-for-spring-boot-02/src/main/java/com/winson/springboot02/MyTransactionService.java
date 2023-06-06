package com.winson.springboot02;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author mike ian
 * @date 2023/6/6
 * @desc
 **/
@Slf4j
@Service
public class MyTransactionService {

    @Transactional
    public void hello(String message){
        log.info("hello message : {}",message);
    }

}
