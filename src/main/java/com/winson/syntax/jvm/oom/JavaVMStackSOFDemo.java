package com.winson.syntax.jvm.oom;

/**
 * @author winson
 * @date 2021/6/25
 **/
public class JavaVMStackSOFDemo {

    private int stackLength = 1;

    private void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOFDemo demo = new JavaVMStackSOFDemo();
        try {
            demo.stackLeak();
        }catch (Throwable e){
            System.err.println("stack length : " + demo.stackLength);
            throw e;
        }
    }

}
