package com.winson.jvm.common;

/**
 * @author winson
 * @date 2021/9/1
 **/
public class InnerOuterClassDemoV1 {

    private int flag = 20;

    public class MyInnerClass {

        public MyInnerClass(){
            System.out.println("inner construct flag : " + flag);
        }

        public void sayInner(){
            System.out.println("inner say inner flag : " + flag);
        }
    }

    public MyInnerClass getMyInnerClass(){
        return new MyInnerClass();
    }

    public static void main(String[] args) {
        InnerOuterClassDemoV1 app = new InnerOuterClassDemoV1();
        app.getMyInnerClass().sayInner();


//        MyInnerClass innerClass = new app.MyInnerClass();
//        innerClass.sayInner();

    }

}
