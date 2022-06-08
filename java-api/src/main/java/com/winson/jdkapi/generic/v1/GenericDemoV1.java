package com.winson.jdkapi.generic.v1;

/**
 * @author winson
 * @date 2021/10/5
 **/
public class GenericDemoV1 {

    public static class GenericOne {
        public void doOne() {
            System.out.println("do at one");
        }

        public void doSelf() {
            System.out.println("do self at one");
        }
    }

    public static class GenericTwo extends GenericOne {
        public void doTwo() {
            System.out.println("do at two");
        }

        public void doSelf() {
            System.out.println("do self at two");
        }
    }

    public static class GenericThree extends GenericTwo {
        public void doThree() {
            System.out.println("do at three");
        }

        public void doSelf() {
            System.out.println("do self at three");
        }
    }

    public static class GenericFour extends GenericThree {
        public void doFour() {
            System.out.println("do at four");
        }

        public void doSelf() {
            System.out.println("do self at four");
        }
    }

    public static class GenericFive extends GenericFour {
        public void doFive() {
            System.out.println("do at five");
        }

        public void doSelf() {
            System.out.println("do self at five");
        }
    }

    public static class GenericSix extends GenericFive {
        public void doSix() {
            System.out.println("do at six");
        }

        public void doSelf() {
            System.out.println("do self at six");
        }
    }

    interface GenericInvokeInterface<S, R> {

        R invokeWithSuperSelf(S s);

        S invokeWithExtendSelf(R r);

    }

    public static class GenericAddition<O> {

        private O o;

        public GenericAddition(O o) {
            this.o = o;
        }

        public O getTarget() {
            return o;
        }

        public void doWithTarget(O o){
            System.out.println("target : " + o);
        }
    }

    public static abstract class AbstractGenericImpl<S extends GenericThree, R extends GenericFour>
            implements GenericInvokeInterface<GenericAddition<? extends S>, GenericAddition<? super R>> {

        @Override
        public GenericAddition<? super R> invokeWithSuperSelf(GenericAddition<? extends S> genericAddition) {
            return new GenericAddition<>(genericAddition.getTarget());
        }

        @Override
        public GenericAddition<? extends S> invokeWithExtendSelf(GenericAddition<? super R> r) {
            return new GenericAddition<>((S) r.getTarget());
        }
    }

    public static class GenericImpl<S extends GenericThree, R extends GenericFour> extends AbstractGenericImpl<S, R> {

    }

    public static void main(String[] args) {
        GenericOne genericOne = new GenericOne();
        GenericTwo genericTwo = new GenericTwo();
        GenericThree genericThree = new GenericThree();
        GenericFour genericFour = new GenericFour();
        GenericFive genericFive = new GenericFive();
        GenericSix genericSix = new GenericSix();

        GenericAddition<GenericOne> genericOneAddition = new GenericAddition<>(genericOne);
        GenericAddition<GenericTwo> genericTwoAddition = new GenericAddition<>(genericTwo);
        GenericAddition<GenericThree> genericThreeAddition = new GenericAddition<>(genericThree);
        GenericAddition<GenericFour> genericFourAddition = new GenericAddition<>(genericFour);
        GenericAddition<GenericFive> genericFiveAddition = new GenericAddition<>(genericFive);
        GenericAddition<GenericSix> genericSixAddition = new GenericAddition<>(genericSix);

        // 这里编译有问题
//        GenericImpl<GenericFour, GenericThree> genericCompileError = new GenericImpl();
        GenericImpl<GenericThree, GenericFour> generic = new GenericImpl<>();
        System.out.println("======== invoke super ==========");
        // invoke happen error
//        GenericAddition<? super GenericFour> superGenericAdditionError = generic.invokeWithSuperSelf(genericTwoAddition);
        GenericAddition<? super GenericFour> superGenericAddition = generic.invokeWithSuperSelf(genericFourAddition);
        System.out.println("superGenericAddition.getTarget() : " + superGenericAddition.getTarget());
        System.out.println("superGenericAddition.getTarget().getClass() : " + superGenericAddition.getTarget().getClass());
//        superGenericAddition.doWithTarget(superGenericAddition.getTarget());

        System.out.println("======== invoke extend ==========");
        // error
        GenericAddition<? extends GenericThree> extendGenericAddition = generic.invokeWithExtendSelf(genericFourAddition);
        System.out.println("extendGenericAddition.getTarget() : " + extendGenericAddition.getTarget());
        System.out.println("extendGenericAddition.getTarget().getClass() : " + extendGenericAddition.getTarget().getClass());
        extendGenericAddition.getTarget().doOne();
        extendGenericAddition.getTarget().doTwo();
        extendGenericAddition.getTarget().doThree();
        extendGenericAddition.getTarget().doSelf();

    }

}
