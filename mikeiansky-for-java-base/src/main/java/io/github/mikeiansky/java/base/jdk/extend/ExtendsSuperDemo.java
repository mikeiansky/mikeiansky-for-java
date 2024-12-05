package io.github.mikeiansky.java.base.jdk.extend;

/**
 * @author mike ian
 * @date 2024/12/5
 * @desc
 **/
public class ExtendsSuperDemo {

    public static class Father {
        public Father(){
            System.out.println("father create ... ");
        }
        public Father(String tag){
            System.out.println("father create with tag " + tag);
        }
    }

    public static class Son extends Father {
        public Son(){
            super("son");
            System.out.println("son create ... ");
        }
    }

    public static void main(String[] args) {
        Son son = new Son();
    }

}
