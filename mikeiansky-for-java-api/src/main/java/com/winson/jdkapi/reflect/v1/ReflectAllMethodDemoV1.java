package com.winson.jdkapi.reflect.v1;

import java.lang.reflect.Method;

/**
 * @author winson
 * @date 2021/10/9
 **/
public class ReflectAllMethodDemoV1 {

    interface Interface_1 {
        void public_ifm_1();
    }

    interface Interface_2 {
        void public_ifm_2();

    }

    interface Interface_3 {
        void public_ifm_3();

    }

    interface Interface_4 {
        void public_ifm_4();

    }

    interface Interface_5 {
        void public_ifm_5();

    }

    interface Interface_6 {
        void public_ifm_6();

    }

    public static class Father_1 {

        public void public_fm_1() {

        }

        public void public_fm_2() {

        }

        void default_fm_3() {

        }

        void default_fm_32() {

        }

        protected void protected_fm_4() {

        }

        private void private_fm_5() {

        }

        public static void static_public_fm_6(){

        }

        public static void static_public_fm_7(){

        }

        static void static_default_fm_8(){

        }

        static void static_default_fm_82(){

        }

        protected static void static_protected_fm_9(){

        }

        private static void static_private_fm_10(){

        }

    }

    public static class Son_1 {

    }

    public static class Son_2 {

    }

    public static class Son_3 extends Father_1 implements Interface_6 {

        @Override
        public void public_ifm_6() {
        }

        public void public_sm_1() {

        }

        public void public_fm_2() {

        }

        void default_sm_3() {

        }

        void default_fm_32() {

        }

        protected void protected_sm_4() {

        }

        private void private_sm_5() {

        }

        public static void static_public_fm_7(){

        }

        static void static_default_sm_8(){

        }

        static void static_default_fm_82(){

        }

        protected static void static_protected_sm_9(){

        }

        private static void static_private_sm_10(){

        }

    }

    public static void main(String[] args) {
        System.out.println("=============== father =============");
        Class<?> fclazz = Father_1.class;
        System.out.println("======== father displayGetMethods ========");
        Method[] fmethods = fclazz.getMethods();
        displayMethods(fmethods);

        System.out.println("======== father displayGetDeclaredMethods ========");
        Method[] fdeclaredMethods = fclazz.getDeclaredMethods();
        displayMethods(fdeclaredMethods);

        System.out.println("=============== son =============");
        Class<?> clazz = Son_3.class;
        System.out.println("======== son displayGetMethods ========");
        Method[] methods = clazz.getMethods();
        displayMethods(methods);

        System.out.println("======== son displayGetDeclaredMethods ========");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        displayMethods(declaredMethods);
    }

    public static void displayMethods(Method[] methods) {
        for (Method method : methods) {
            System.out.println("methodName: " + method.getName()
                    + " , declareInClass : " + method.getDeclaringClass().getSimpleName()
                    + " , isBridge : " + method.isBridge());
        }
    }

}
