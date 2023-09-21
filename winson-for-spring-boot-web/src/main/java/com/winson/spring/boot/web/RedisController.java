package com.winson.spring.boot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mike ian
 * @date 2023/9/21
 * @desc
 **/
@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("read")
    public String read(@RequestParam("key") String key){

        Object result = redisTemplate.boundValueOps(key).get();
        System.out.println("get result : "+ result);
        return String.valueOf(result);
    }

    @GetMapping("write")
    public String set(@RequestParam("key") String key,
                      @RequestParam("value") String value){
        redisTemplate.boundValueOps(key).set(value);
        return "success";
    }

}
