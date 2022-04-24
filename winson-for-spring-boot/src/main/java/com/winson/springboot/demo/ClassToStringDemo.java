package com.winson.springboot.demo;

//import com.winson.autoconfig.TestDependency;
//import com.winson.autoconfig.WinsonAutoConfig;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.MergedAnnotations;

import java.util.Map;

/**
 * @author winson
 * @date 2022/4/24
 **/
public class ClassToStringDemo {

    public static void main(String[] args) {

//        TestDependency.doAction();

//        System.out.println(WinsonAutoConfig.class.getAnnotations());

//        System.out.println(MybatisAutoConfiguration.class.getAnnotations());
//        System.out.println(MybatisAutoConfiguration.class.getDeclaredAnnotations());

//        MybatisAutoConfiguration ma = new MybatisAutoConfiguration();
//        ConditionalOnClass coc = AnnotationUtils.findAnnotation(MybatisAutoConfiguration.class, ConditionalOnClass.class);
//        MergedAnnotations.from(MybatisAutoConfiguration.class);
//        System.out.println("coc : " + coc);

//        Map<String,Object> result = AnnotationUtils.getAnnotationAttributes(coc);
//        System.out.println("result : " + result);

//        Map<String,Object> result2 = AnnotationUtils.getAnnotationAttributes(coc, true);
//        System.out.println("result2 : " + result2);

    }

}
