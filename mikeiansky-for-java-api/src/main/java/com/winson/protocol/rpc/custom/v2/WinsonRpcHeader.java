package com.winson.protocol.rpc.custom.v2;

import lombok.Data;

import java.io.Serializable;

/**
 * @author winson
 * @date 2022/5/23
 **/
@Data
public class WinsonRpcHeader implements Serializable {

    public static final int HEAD_LENGTH = 115;
    private int version = 2;
    private long id;
    private int totalLength;


}
