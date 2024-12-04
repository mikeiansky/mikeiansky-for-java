package com.winson.protocol.rpc.custom.v3;

import lombok.Data;

import java.io.Serializable;

/**
 * @author winson
 * @date 2022/5/23
 **/
@Data
public class WinsonRpcBody implements Serializable {

    private String serviceName;
    private Class[] paramTypes;
    private String methodName;
    private Object[] args;

}
