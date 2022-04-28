package com.winson.springboot.demo;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//@RunWith(SpringRunner.class)
//@SpringBootTest
class WinsonForSpringBootApplicationTests {

//    @Autowired
//    public WebApplicationContext webApplicationContext;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    public void before() {
////        System.out.println("----------> ");
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }
//
//    @Test
//    void getUserInfo() {
////        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user") ;
//
//        try {
//            MvcResult mvcResult = mockMvc.perform(requestBuilder)
//                    .andDo(MockMvcResultHandlers.print())
//                    .andReturn();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("getUserInfo  === > ");
//    }

}
