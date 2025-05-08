package io.github.mikeiansky.java.base.jdk.reflect;

/**
 * @author mike ian
 * @date 2025/5/8
 * @desc
 **/
public class BaseReflectDemo {

    public static class One {

        public static class OneStaticInner  {}

        public class OneObjectInner {}

        static class ObjectStaticDefaultInner {}

        final static class ObjectStaticDefaultFinalInner {}

        private class OnePrivateInner {}

        private static class OnePrivateFinalInner {}

        public One() {
            Object obj1 = new Object();
            System.out.println(obj1.getClass());
            Object obj2 = new Object(){

            };
            System.out.println(obj2.getClass());
            System.out.println(obj2.getClass().getDeclaredClasses().length);
            System.out.println(obj2.getClass().getSuperclass());
            System.out.println(obj2.getClass().getEnclosingClass());
            System.out.println(obj2.getClass().getEnclosingConstructor());
            System.out.println(obj2.getClass().getEnclosingMethod());
        }

    }

    public static void main(String[] args) {
        Class oneClazz = One.class;
        Class[] memberClazz = oneClazz.getDeclaredClasses();
        for (Class clazz : memberClazz) {
            System.out.println(clazz);
        }
        new One();
    }

}
