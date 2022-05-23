package com.winson.protocol.rpc.custom.v3;

import lombok.Data;

import java.io.Serializable;

/**
 * @author winson
 * @date 2022/5/23
 **/
@Data
public class WinsonRpcResponseHeader implements Serializable {

    public static final int RESPONSE_HEAD_LENGTH = 122;
    private int version = 1;
    private long id;
    private int dataLength;

}
