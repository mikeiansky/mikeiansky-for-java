package com.winson.spring.boot.docker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mike ian
 * @date 2023/6/8
 * @desc
 **/
@Slf4j
@RestController
@RequestMapping("api")
public class HelloController {


    @GetMapping("hello")
    public String hello(@RequestParam(name = "msg", defaultValue = "winson") String msg) {
        log.info("hello message change word is : {} ", msg);
        return msg;
    }

}
