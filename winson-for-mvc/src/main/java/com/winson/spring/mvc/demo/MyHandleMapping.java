package com.winson.spring.mvc.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author winson
 * @date 2022/5/2
 **/
public class MyHandleMapping extends BeanNameUrlHandlerMapping implements Ordered {

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    protected void extendInterceptors(List<Object> interceptors) {
        ApplicationContext context = obtainApplicationContext();
        String[] beans = context.getBeanNamesForType(HandlerInterceptor.class);
        StringBuilder sb = new StringBuilder();
        Arrays.stream(beans).forEach(sb::append);
        System.out.println("extendInterceptors -------> " + sb);
        for (String bean : beans) {
            interceptors.add(context.getBean(bean));
        }

    }
}
