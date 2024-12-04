package com.winson.jdkapi.annotation.v1;

/**
 * @author winson
 * @date 2021/6/15
 **/
public class ValidUser {

    @NotEmpty
    @MaxLength(10)
    public String userName;

}
