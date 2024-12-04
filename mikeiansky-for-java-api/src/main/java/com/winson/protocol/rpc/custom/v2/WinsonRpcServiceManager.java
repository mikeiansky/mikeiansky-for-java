package com.winson.protocol.rpc.custom.v2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author winson
 * @date 2022/5/23
 **/
public class WinsonRpcServiceManager {

    private ConcurrentHashMap<String,Object> serviceMap = new ConcurrentHashMap<>();

    public WinsonRpcServiceManager() {
        WinsonHelloServiceImpl helloService = new WinsonHelloServiceImpl();
        serviceMap.put(WinsonHelloService.class.getName(), helloService);
    }

    public Object doService(WinsonRpcBody rpcBody) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object target = serviceMap.get(rpcBody.getServiceName());
        if(target == null){
            return null;
        }
        Method method = target.getClass().getMethod(rpcBody.getMethodName(), rpcBody.getParamTypes());
        Object result = method.invoke(target, rpcBody.getArgs());
        return result;
    }

}
