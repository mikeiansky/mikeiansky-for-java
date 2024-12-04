package com.winson.jdkapi.generic.v1;

/**
 * @author winson
 * @date 2022/4/12
 * @desc 泛型循环测试
 **/
public class GenericCircleDemoV1 {

    public static class MyFlagOne<A> {
        public void doOne() {
            System.out.println("MyFlagOne doOne");
        }
    }

    public static class MyFlagTwo<B> extends MyFlagOne<B> {
        public void doTwo() {
            System.out.println("MyFlagTwo doTwo");
        }
    }

    public static class MyFlagThree<C> extends MyFlagTwo<C> {
        public void doThree() {
            System.out.println("MyFlagThree doThree");
        }
    }

    public static class MyFlagFour<D> extends MyFlagThree<D> {
        public void doFour() {
            System.out.println("MyFlagFour doFour");
        }
    }

    public static class MyFlagFive<E> extends MyFlagFour<E> {
        public void doFive() {
            System.out.println("MyFlagFive doFive");
        }
    }

    public static class MyFlagSix<E extends MyFlagOne<E>> {

        public void doSix(E e) {
            System.out.println("doSix e:" + e);
        }

    }

    // 这里还有疑问，不知道怎么弄，后面再看。
//    public static class MyFlagSeven<E extends MyFlagTwo> extends MyFlagSix<> {
//
//    }

    public static void main(String[] args) {
        MyFlagSix six = new MyFlagSix();
        six.doSix(new MyFlagFive<MyFlagFour>());
    }

}
