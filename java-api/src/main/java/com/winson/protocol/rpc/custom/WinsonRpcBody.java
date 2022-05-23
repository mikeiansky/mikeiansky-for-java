package com.winson.protocol.rpc.custom;

import lombok.Data;

import java.io.Serializable;

/**
 * @author winson
 * @date 2022/5/23
 **/
@Data
public class WinsonRpcBody implements Serializable {

    private String serviceName;
//    private String clazz;
    private Class[] paramTypes;
    private String methodName;
    private Object[] args;

}
