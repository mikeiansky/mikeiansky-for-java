package io.github.mikeiansky.java.base.jdk.extend;

/**
 * @author mike ian
 * @date 2024/12/25
 * @desc
 **/
public class ThisDemo {

    public static class Father {

        public void hello() {
            System.out.println("father hello");
        }

        public void useHello(){
            Father.this.hello();
        }

    }

    public static class Son extends Father {
        @Override
        public void hello() {
            System.out.println("son hello");
        }
    }

    public static void main(String[] args) {
        Father father = new Son();
        father.useHello();
    }

}
