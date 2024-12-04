package com.winson.jvm.reference;

/**
 * @author winson
 * @date 2022/3/13
 * @desc this逃逸分析
 **/
public class EscapeDemo {

    public static class One {

        public void sayHello(Two two) {
            System.out.println(two.flag);
        }
    }

    public static class Two {

        private final String flag;

        public Two(One one, String flag) {
            one.sayHello(this);
            this.flag = flag;
            one.sayHello(this);
        }

    }

    public static void main(String[] args) {
        Two two = new Two(new One(), "never");
    }

}
