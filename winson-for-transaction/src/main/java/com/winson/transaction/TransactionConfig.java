package com.winson.transaction;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author mike ian
 * @date 2023/6/7
 * @desc
 **/
@Configuration
@Import({SimpleTXManager.class, TransactionService.class})
@EnableTransactionManagement
public class TransactionConfig {



}
