package com.winson.springboot02;


import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author mike ian
 * @date 2023/6/5
 * @desc
 **/
//@RunWith(SpringRunner.class)
//@TestPropertySource(locations = {"classpath:./hello/second/app*.properties"})
//@SpringBootTest

@RunWith(SpringRunner.class)
@SpringBootTest
//@TestPropertySource(locations = {"classpath:./hello/second/applicatio*.properties"})
public class WinsonSB02Test extends TestCase {

    @Value("${hello:0}")
    private String word;

    @Autowired
    private MyService myService;

    @Test
    public void testMain() {

        System.out.println(word);
        System.out.println(myService);

    }

}