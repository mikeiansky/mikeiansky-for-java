package io.github.mikeiansky.java.base.jdk.initialization;

/**
 * @author mike ian
 * @date 2024/12/9
 * @desc
 **/
public class InitializationSequenceDemo {

    public static class Inner {
        public Inner() {
            System.out.println("Inner created");
        }
    }

    private Inner inner = new Inner();

    public InitializationSequenceDemo() {
        System.out.println("InitializationSequenceDemo created");
    }

    public static void main(String[] args) {
        InitializationSequenceDemo demo = new InitializationSequenceDemo();
    }

}
