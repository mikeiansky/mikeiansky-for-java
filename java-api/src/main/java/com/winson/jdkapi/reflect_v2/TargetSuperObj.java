package com.winson.jdkapi.reflect_v2;

/**
 * @author winson
 * @date 2022/4/11
 **/
@MyFatherAnnotation
public abstract class TargetSuperObj<A, B, C> {

    public String name;
    protected int age;
    String city;

    public A createAV1(C c) {
        System.out.println("TargetSuperObj createAV1 : " + c);
        return (A) c;
    }

    public abstract A createAV2(C c);

    public void consumeBV1(B b) {
        System.out.println("TargetSuperObj consumeBV1 : " + b);
    }

    @MyFatherAnnotation
    public void consumeBV2(B b) {
        System.out.println("TargetSuperObj consumeBV2 : " + b);
    }

}
