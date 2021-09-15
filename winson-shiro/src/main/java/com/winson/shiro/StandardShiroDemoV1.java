package com.winson.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;

import java.util.Collections;


/**
 * @author winson
 * @date 2021/9/14
 **/
public class StandardShiroDemoV1 {

    public static void main(String[] args) {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
//        securityManager.setAuthenticator(new AbstractAuthenticator() {
//            @Override
//            protected AuthenticationInfo doAuthenticate(AuthenticationToken token) throws AuthenticationException {
//                System.out.println("doAuthenticate ---> token " + token);
//                return null;
//            }
//        });

        ThreadContext.bind(securityManager);

        System.out.println(SecurityUtils.getSubject());

        

        System.out.println(SecurityUtils.getSubject());


        SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
        simpleAccountRealm.addAccount("winson","123456");
        securityManager.setRealms(Collections.singleton(simpleAccountRealm));

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("winson", "123456");
        subject.login(token);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
