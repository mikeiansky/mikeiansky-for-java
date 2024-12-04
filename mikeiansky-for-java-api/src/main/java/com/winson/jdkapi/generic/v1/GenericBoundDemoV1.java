package com.winson.jdkapi.generic.v1;

/**
 * @author winson
 * @date 2022/4/12
 * @desc 通配符以及，extends 和 super 测试
 **/
public class GenericBoundDemoV1 {

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

    public interface MyFunction<T, R> {

        R apply(T t);

        default <S> MyFunction<T, S> andThen(MyFunction<R, S> after) {
            return t -> after.apply(MyFunction.this.apply(t));
        }

        default <V> MyFunction<V, R> compose(MyFunction<? super V, ? extends T> before) {
            return t -> MyFunction.this.apply(before.apply(t));
        }

    }

    public static void main(String[] args) {

        MyFunction<? super MyFlagThree, ? extends MyFlagTwo> function = myFlagOne -> new MyFlagTwo();
        function.apply(new MyFlagFour()).doTwo();

        MyFunction<MyFlagThree, MyFlagThree> second = myFlagThree -> new MyFlagThree();
        second.<MyFlagTwo>compose(new MyFunction<MyFlagOne, MyFlagThree>() {
            @Override
            public MyFlagThree apply(MyFlagOne myFlagTwo) {
                return new MyFlagThree();
            }
        }).apply(new MyFlagTwo());
    }

}
