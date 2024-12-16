package io.github.mikeiansky.java.base.jdk.clazz;

/**
 * @author mike ian
 * @date 2024/12/16
 * @desc
 **/
public class EncloseClazzDemo {

    public static class Outer {

        public class Inner {

        }

        public void testObjInner() {
            System.out.println("============> testObjInner");
            Class clazz = Inner.class;
            System.out.println(clazz);
            System.out.println(clazz.getEnclosingClass());
            System.out.println(clazz.getEnclosingConstructor());
            System.out.println(clazz.getEnclosingMethod());
        }

        public void testStaticInner() {
            System.out.println("============> testStaticInner");
            Class clazz = Inner.class;
            System.out.println(clazz);
            System.out.println(clazz.getEnclosingClass());
            System.out.println(clazz.getEnclosingConstructor());
            System.out.println(clazz.getEnclosingMethod());
        }

        public void testMethodEncloseClazz() {
            System.out.println("============> testMethodEncloseClazz");
            Inner methodInnerClazz = new Inner() {};
            System.out.println(methodInnerClazz);
            System.out.println(methodInnerClazz.getClass().getEnclosingClass());
            System.out.println(methodInnerClazz.getClass().getEnclosingConstructor());
            System.out.println(methodInnerClazz.getClass().getEnclosingMethod());
            System.out.println(methodInnerClazz instanceof Inner);
            System.out.println(Inner.class.isAssignableFrom(methodInnerClazz.getClass()));
        }

    }

    public static void main(String[] args) {
        System.out.println("==========> main");
        Class outerClazz = Outer.class;
        System.out.println(outerClazz.getEnclosingClass());
        System.out.println(outerClazz.getEnclosingConstructor());
        System.out.println(outerClazz.getEnclosingMethod());

        Outer outer = new Outer();
        outer.testObjInner();
        outer.testStaticInner();
        outer.testMethodEncloseClazz();

    }

}
