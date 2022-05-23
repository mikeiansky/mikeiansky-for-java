package com.winson.protocol.rpc.custom;

import lombok.Data;

import java.io.Serializable;

/**
 * @author winson
 * @date 2022/5/23
 **/
@Data
public class WinsonRpcHeader implements Serializable {

    public static final int HEAD_LENGTH = 112;
    private int version = 1;
    private long id;
    private int totalLength;


}
