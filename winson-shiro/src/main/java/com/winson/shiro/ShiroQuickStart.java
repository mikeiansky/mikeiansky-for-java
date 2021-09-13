package com.winson.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.util.ThreadContext;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author winson
 * @date 2021/9/13
 **/
public class ShiroQuickStart {

    public static void main(String[] args) {

        DefaultSecurityManager sm = new DefaultSecurityManager();
        ThreadContext.bind(sm);
        SecurityUtils.getSecurityManager();
        Subject currentUser = SecurityUtils.getSubject();
        String md5 = Md5Hash.toString("123456".getBytes());
        SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
        simpleAccountRealm.addAccount("winson", md5);
        simpleAccountRealm.addAccount("ciwei", "ciwei2019");
        SimpleCredentialsMatcher matcher = new SimpleCredentialsMatcher();
        simpleAccountRealm.setCredentialsMatcher(matcher);

        sm.setRealms(Collections.singleton(simpleAccountRealm));


        UsernamePasswordToken token = new UsernamePasswordToken("winson", md5);
        token.setRememberMe(true);
        System.out.println("login token is : " + token);
        System.out.println("login is remember before authentic : "+currentUser.isRemembered());

//        AuthenticationInfo authenticationInfo = simpleAccountRealm.getAuthenticationInfo(token);
        if(!currentUser.isAuthenticated()){
            System.out.println("current user login ... ");
            System.out.println("before login principle : " + currentUser.getPrincipal());
            currentUser.login(token);
            System.out.println("current user login success ... ");
            System.out.println("after login principle : " + currentUser.getPrincipal());
        }else {
            System.out.println("current user has login");
        }

        System.out.println("login is remember after authentic : "+currentUser.isRemembered());
        System.out.println("login state after authentic : "+currentUser.isAuthenticated());



    }

}
