package com.winson.springbootlog4jbugdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootLog4jBugDemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringBootLog4jBugDemoApplication.class, args);

        // 要配合的测试工程
        // https://github.com/welk1n/JNDI-Injection-Exploit

//        ExecService service = context.getBean(ExecService.class);
//        service.testLog4JBug();

        Log4jService ls = context.getBean(Log4jService.class);
        ls.testBug();
    }

}
