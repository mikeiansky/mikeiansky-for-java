package com.winson.shiro;

import com.mchange.v2.c3p0.DriverManagerDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

/**
 * @author winson
 * @date 2021/9/13
 **/
public class JdbcShiroQuickStartV1 {

    public static void main(String[] args) {

        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        SecurityUtils.setSecurityManager(securityManager);

        JdbcRealm jdbcRealm = new JdbcRealm();
        String jdbcUrl = "jdbc:mysql://localhost:3306/winson_test?useSSL=false&serverTimezone=UTC";
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser("root");
        dataSource.setPassword("123456");
        jdbcRealm.setDataSource(dataSource);
        jdbcRealm.setAuthenticationQuery("select cpwd from ciwei_user where cname = ?");
        securityManager.setRealms(Collections.singleton(jdbcRealm));

        UsernamePasswordToken token = new UsernamePasswordToken("winson", "1234568");

        Subject user = SecurityUtils.getSubject();
        System.out.println("use is authenticated : " + user.isAuthenticated());

        if (!user.isAuthenticated()) {
            System.out.println("need login");
            try {
                user.login(token);
            } catch (AuthenticationException e){
                e.printStackTrace();
            }
            System.out.println("login result : " + user.isAuthenticated());
        }

//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println("app end ... ");
    }

}
