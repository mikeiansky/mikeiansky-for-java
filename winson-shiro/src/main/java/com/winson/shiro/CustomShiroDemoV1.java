package com.winson.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.text.TextConfigurationRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;

import java.util.Collections;


/**
 * @author winson
 * @date 2021/9/14
 **/
public class CustomShiroDemoV1 {

    public static class WinsonRealm implements Realm {

        @Override
        public String getName() {
            return "winson-realm";
        }

        @Override
        public boolean supports(AuthenticationToken token) {
            System.out.println("check support token : " + token);
            return true;
        }

        @Override
        public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
            UsernamePasswordToken pt = (UsernamePasswordToken) token;
            System.out.println("getAuthenticationInfo token ------> 1");
            System.out.println("token principal : " + token.getPrincipal());
            System.out.println("token credential : " + ((char[])token.getCredentials()).length);
            System.out.println("token password : " + new String(pt.getPassword()));
            System.out.println("getAuthenticationInfo token ------> 2");
            if(token.getPrincipal().equals("winson")){
                SimpleAccount account = new SimpleAccount("winson222","123456",getName(),null,null);
                return account;
            }
            return null;
        }
    }

    public static void main(String[] args) {

        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        SecurityUtils.setSecurityManager(securityManager);


        SimpleAccountRealm accountRealm = new SimpleAccountRealm();
        accountRealm.addAccount("winson", "123456");
//        securityManager.setRealms(Collections.singleton(accountRealm));

        TextConfigurationRealm realm = new TextConfigurationRealm();
//        realm.setUserDefinitions("winson=123456");
//        realm.init();
//        securityManager.setRealms(Collections.singleton(realm));

        WinsonRealm winsonRealm = new WinsonRealm();
        securityManager.setRealms(Collections.singleton(winsonRealm));

        DefaultSubjectContext context = new DefaultSubjectContext();
        Subject subject1 = SecurityUtils.getSecurityManager().createSubject(context);
        System.out.println("subject1 is : " + subject1);
        Subject subject2 = SecurityUtils.getSecurityManager().createSubject(context);
        System.out.println("subject2 is : " + subject2);
//        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token1 = new UsernamePasswordToken("winson", "123456");
        UsernamePasswordToken token2 = new UsernamePasswordToken("winson", "ciwei");

        System.out.println("1 before login : " + subject1.isAuthenticated());
        System.out.println("2 before login : " + subject2.isAuthenticated());

        subject1.login(token1);
        subject2.login(token2);

        System.out.println("1 after login : " + subject1.isAuthenticated());
        System.out.println("2 after login : " + subject2.isAuthenticated());
        System.out.println("2 after login : " + subject2.getPrincipal());


    }

}
