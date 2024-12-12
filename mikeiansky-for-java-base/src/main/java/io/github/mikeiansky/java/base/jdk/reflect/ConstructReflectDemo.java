package io.github.mikeiansky.java.base.jdk.reflect;

import java.lang.reflect.Constructor;

/**
 * @author mike ian
 * @date 2024/12/12
 * @desc
 **/
public class ConstructReflectDemo {

    public static class GrandFather {
        public GrandFather() {
            System.out.println("GrandFather c no tag");
        }

        public GrandFather(String tag) {
            System.out.println("GrandFather c tag 1");
        }

        public GrandFather(String tag1, String tag2) {
            System.out.println("GrandFather c tag 2");
        }
    }

    public static class Father extends GrandFather {
        public Father() {
            System.out.println("Father c no tag");
        }

        public Father(String tag) {
            System.out.println("Father c tag 1");
        }

        public Father(String tag1, String tag2) {
            System.out.println("Father c tag 2");
        }
    }

    public static class Son extends Father {
        public Son() {
            System.out.println("Son c no tag");
        }

        protected Son(String tag) {
            System.out.println("Son c tag 1");
        }

        private Son(String tag1, String tag2) {
            System.out.println("Son c tag 2");
        }
    }

    public static void main(String[] args) {
        Son son = new Son();
        System.out.println("----------- 1 ");
        Son son1 = new Son("tag1");
        System.out.println("----------- 2 ");
        Son son2 = new Son("tag2-01", "tag2-02");

        System.out.println("------- reflect -------- ");
        System.out.println("------ getDeclaredConstructors ---------");
        for (Constructor constructor : Son.class.getDeclaredConstructors()) {
            System.out.println(constructor + ", synthetic : " + constructor.isSynthetic());
        }
        System.out.println("------ getConstructors ---------");
        for (Constructor constructor : Son.class.getConstructors()) {
            System.out.println(constructor + ", synthetic : " + constructor.isSynthetic());
        }

    }

}
