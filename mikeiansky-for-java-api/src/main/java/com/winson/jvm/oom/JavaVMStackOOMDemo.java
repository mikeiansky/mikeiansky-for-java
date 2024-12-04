package com.winson.jvm.oom;

/**
 * @author winson
 * @date 2021/6/25
 **/
public class JavaVMStackOOMDemo {

    private void dontStop(){
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        while (true){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    JavaVMStackOOMDemo demo = new JavaVMStackOOMDemo();
                    demo.dontStop();
                }
            }).start();

        }
    }

}
