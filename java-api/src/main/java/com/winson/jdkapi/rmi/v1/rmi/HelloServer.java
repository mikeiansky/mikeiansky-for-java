package com.winson.jdkapi.rmi.v1.rmi;

//import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.NamingException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @author winson
 * @date 2021/12/28
 **/
public class HelloServer {

    HelloDefine hello;

    public void server() throws RemoteException, MalformedURLException, AlreadyBoundException, NamingException {
        hello = new HelloDefineImpl();

        LocateRegistry.createRegistry(8888);
        Naming.bind("rmi://localhost:8888/hello",hello);
//        Naming.bind("http://localhost:8888/hello",hello);

//        Reference exec = new Reference("Exec", "Exec", "http://127.0.0.1:8080/");
//        ReferenceWrapper refWrap = new ReferenceWrapper(exec);
//        Naming.bind("Exec",hello);
        System.out.println("server bind success !");


//        Registry registry = LocateRegistry.createRegistry(8888);
//        Reference exec = new Reference("exec", "exec", "http://127.0.0.1:8080/");
//        ReferenceWrapper refWrap = new ReferenceWrapper(exec);
//        System.out.println("Binding 'refObjWrapper' to 'rmi://127.0.0.1:8888/exec");
//        registry.bind("exec", refWrap);

    }

    public static void main(String[] args) throws MalformedURLException, AlreadyBoundException, RemoteException, NamingException {

//        HelloServer server = new HelloServer();
//        server.server();

//        String url = "http://127.0.0.1:8080/";
//        Registry registry = LocateRegistry.createRegistry(1099);
//        Reference reference = new Reference("test", "test", url);
//        ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
//        registry.bind("obj",referenceWrapper);
//        System.out.println("running");

    }

}
