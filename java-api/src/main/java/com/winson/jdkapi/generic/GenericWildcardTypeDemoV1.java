package com.winson.jdkapi.generic;

/**
 * @author winson
 * @date 2022/4/12
 **/
public class GenericWildcardTypeDemoV1 {

    public static class MyFlagOne<A> {
        public void doOne() {
            System.out.println("MyFlagOne doOne");
        }
        public void doWithType(A a){
            System.out.println("type is :" + a);
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

    public static class MyActionSuper<A, B> {
        public B createB() {
            return (B) new Object();
        }

        public void consumeB(B b) {
            System.out.println("consumeB b:" + b);
        }

        public A createA() {
            return (A) new Object();
        }

        public void consumeA(A a) {
            System.out.println("consumeA a:" + a);
        }
    }

    public static class MyAction<H extends MyFlagThree, J> extends MyActionSuper<H, J> {

        public void doCreate(MyAction<? extends MyFlagThree, ? super MyFlagFour> action,
                             MyAction<? extends MyFlagThree, ? super MyFlagFour> action2) {
//            action.consumeA(three);
//            action2.consumeA(action.createA());
        }

        public void doExtend(H h) {

        }

    }

    public static void main(String[] args) {

        MyActionSuper<? extends MyFlagThree, ? extends MyFlagFour> myActionSuper = new MyActionSuper();
        MyActionSuper<? super MyFlagThree, ? super MyFlagFour> myActionSuper2 = new MyActionSuper();
        myActionSuper2.consumeA(myActionSuper.createA());
        // 不在范围内，不能进行传递
//        myActionSuper2.consumeA(myActionSuper2.createA());
        myActionSuper2.consumeB(myActionSuper.createB());
//        myActionSuper2.consumeB(myActionSuper2.createB());

        // 不行
//        myActionSuper2.createA().doOne();
        // 可以
//        myActionSuper.createA().doThree();

        // 只要在范围内的就可以进行传递
        myActionSuper2.consumeA(new MyFlagFour());
        myActionSuper2.consumeA(new MyFlagThree());

        MyAction<MyFlagThree, MyFlagThree> myAction = new MyAction<>();
        myAction.doExtend(new MyFlagThree());
        myAction.doExtend(new MyFlagFour());
//        myAction.doExtend(new MyFlagTwo());


        MyFlagOne<MyAction> myFlagOne = new MyFlagOne<>();
        myFlagOne.doOne();
//        myFlagOne.doWithType(myAction);
//        myFlagOne.doWithType(myAction);
//        myFlagOne.doWithType(myActionSuper);

        Class<MyFlagOne> clazz = MyFlagOne.class;
        MyFlagOne cc = clazz.cast(myAction);

    }

}
