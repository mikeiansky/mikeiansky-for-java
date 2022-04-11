package com.winson.jdkapi.reflect_v2;

/**
 * @author winson
 * @date 2022/4/11
 **/
@MySonAnnotation
public class TargetObj<H, J> extends TargetSuperObj<MyFlagOne, MyFlagTwo, MyFlagThree> implements TargetInterfaceObj<MyFlagOne> {

    public static class MyStaticObj {

    }

    public class MyNormalObj {

    }

    public interface MyInterfaceObj {

    }

    enum MyEnum {
        ZHOU, WEN, XIANG;
    }

    public int size;

    public String flag;

    @MyFatherAnnotation
    public H h;

    @MySonAnnotation
    public MyFlagThree<J> three;

    public TargetObj() {

    }

    private TargetObj(String name) {

    }

    public void sayHello(H h) {

    }

    J analyze() {
        return (J) new Object();
    }

    protected void consumeThree(MyFlagThree<H> three) {

    }

    MyFlagThree<H> createThree() {
        return new MyFlagThree<>();
    }

    public static void myStaticMethod() {

    }


    @Override
    public MyFlagOne createAV2(MyFlagThree myFlagThree) {
        System.out.println("TargetObj createAV2 : " + myFlagThree);
        return new MyFlagOne();
    }

    //    @MyFatherAnnotation
    @MySonAnnotation
    @Override
    public void consumeBV2(MyFlagTwo myFlagTwo) {
        System.out.println("TargetObj consumeBV2 : " + myFlagTwo);
    }

    //    public void useSelfH(H h) {
//        System.out.println("TargetObj useSelfH : " + h);
//    }

    @Override
    public void make(MyFlagOne myFlagOne) {
        System.out.println("TargetObj make : " + myFlagOne);
    }

}
