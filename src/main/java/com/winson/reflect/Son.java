package com.winson.reflect;

/**
 * @author winson
 * @date 2021/6/15
 **/
public class Son extends Parent implements Make {

    public volatile String sonVolatileName;

    public String sonPublicName;

    private String sonPrivateName;

    String sonDefaultName;

    protected String sonProtectedName;

    public static String staticField;

    public static volatile  String staticVolatileField;

    public static void staticMethod(){

    }

    public void sonPublicMethod() {

    }

    private void sonPrivateMethod() {

    }

    void sonDefaultMethod() {

    }

    protected void sonProtectedMethod() {

    }

}
