package com.winson.shiro;

import com.hazelcast.util.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.Sha256CredentialsMatcher;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.PermissionUtils;
import org.apache.shiro.util.ThreadContext;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * @author winson
 * @date 2021/9/13
 **/
public class ShiroQuickStartV2 {

    public static void main(String[] args) {

        DefaultSecurityManager securityManager = new DefaultSecurityManager();
//        ThreadContext.bind(securityManager);
//        SecurityUtils.setSecurityManager(securityManager);


        Sha256CredentialsMatcher sha256CredentialsMatcher = new Sha256CredentialsMatcher();
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher("md5");

        String pwd = "123456";
        String md5 = MD5Util.toMD5String(pwd);
        System.out.println(md5);
        String salt = "ciwei";
        String sha256Hash = new Sha256Hash(pwd, salt, 1024).toString();
        System.out.println("sha256 : " + sha256Hash);

        SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
        simpleAccountRealm.setCredentialsMatcher(sha256CredentialsMatcher);
//        simpleAccountRealm.addAccount("winson", sha256Hash, "manager", "owner");
//        simpleAccountRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        simpleAccountRealm.addAccount("winson", sha256Hash, "manager", "owner");

        DomainPermission managerPermission = new DomainPermission("select,update");
        WildcardPermission managerPermission2 = new WildcardPermission("insert,delete");

        RolePermissionResolver rpr = new RolePermissionResolver() {
            @Override
            public Collection<Permission> resolvePermissionsInRole(String s) {
                return Arrays.asList(managerPermission,managerPermission2);
            }
        };
//        simpleAccountRealm.setPermissionResolver(pr);
        simpleAccountRealm.setRolePermissionResolver(rpr);
        simpleAccountRealm.addAccount("ciwei", "ciwei2019", "admin", "manager", "test", "guest");
//        Permission permission = null;



        SimplePrincipalCollection spc = new SimplePrincipalCollection();
        spc.add("winson", simpleAccountRealm.getName());

//        WildcardPermission domainPermission = new WildcardPermission("add");
//        simpleAccountRealm.checkPermission(spc, domainPermission);

        System.out.println("md5 --> " + pwd);
        securityManager.setRealms(Collections.singleton(simpleAccountRealm));
//        Authenticate;
        DefaultSubjectContext subjectContext = new DefaultSubjectContext();
        Subject user1 = securityManager.createSubject(subjectContext);
        Subject user2 = securityManager.createSubject(subjectContext);
        if(!user1.isAuthenticated()){

            UsernamePasswordToken token = new UsernamePasswordToken("winson", pwd);
//            UsernamePasswordToken token = new UsernamePasswordToken("winson", sha256Hash);

            System.out.println("before login user1 is authenticated : " + user1.isAuthenticated());
            System.out.println(user1.getPrincipal());
//            System.out.println(token.getCredentials());
            System.out.println(user1.hasRole("manager"));
            System.out.println(user1.hasRole("admin"));
            System.out.println("-------- login");
            user1.login(token);
            System.out.println("after login user1 is authenticated : " + user1.isAuthenticated());
            System.out.println(user1.hasRole("manager"));
            System.out.println(user1.getPrincipal());
//            System.out.println(token.getCredentials());
            System.out.println(user1.hasRole("manager"));
            System.out.println(user1.hasRole("admin"));
            System.out.println(user1.getPrincipals());
            System.out.println("has insert permission : "+user1.isPermitted("insert"));
            System.out.println("has select permission : "+user1.isPermitted("select"));
            System.out.println("is permited manager : "+user1.isPermitted(managerPermission2));
        }
        user1.logout();
        System.out.println("-------- logout");
        System.out.println("after logout user1 is authenticated : " + user1.isAuthenticated());
        System.out.println(user1.hasRole("manager"));
        System.out.println(user1.getPrincipal());
//            System.out.println(token.getCredentials());
        System.out.println(user1.hasRole("manager"));
        System.out.println(user1.hasRole("admin"));
    }

}
