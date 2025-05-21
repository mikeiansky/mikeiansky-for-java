package io.github.mikeiansky.jvm.base.clazz;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * @author mike ian
 * @date 2025/5/21
 * @desc
 **/
public class ClazzInitDemo {

    public static class EnumClazzDemo {

        public static class EnumClazzTag {

            static {
                System.out.println("EnumClazzTag initialized");
            }

        }

        public enum EnumClazzEngine {

            INSTANCE(new EnumClazzTag());

            private final EnumClazzTag tag;

            private EnumClazzEngine(EnumClazzTag tag) {
                this.tag = tag;
            }

            public EnumClazzTag getTag() {
                return tag;
            }
        }

    }

    public static class StaticClazzDirectTouchDemo {
        static {
            System.out.println("StaticClazzDirectTouchDemo initialized");
        }

        private static class StaticClazzDirectTouchTag {
            static {
                System.out.println("StaticClazzDirectTouchTag initialized");
            }
        }

        private static Class<StaticClazzDirectTouchTag> clazz = StaticClazzDirectTouchTag.class;

        public static void directTouch() {

        }

    }

    public static class StaticClazzIndirectTouchDemo {
        static {
            System.out.println("StaticClazzIndirectTouchDemo initialized");
        }

        private static class StaticClazzIndirectTouchTag {
            static {
                System.out.println("StaticClazzIndirectTouchTag initialized");
            }
        }

        public static void indirectTouch() {
        }

        public static void directTouch(){
            StaticClazzIndirectTouchTag tag = new StaticClazzIndirectTouchTag();
        }

    }

    public static class AnnotationClazzDemo {

        private static class AnnotationClazzTag {
            static {
                System.out.println("AnnotationClazzTag initialized");
            }
            public static void hello(){
                System.out.println("hello this is annotation clazz tag");
            }
        }

        @Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD})
        @Retention(RetentionPolicy.RUNTIME)
        public @interface MyAnnotation {
            Class value();
        }

        public static void normalStaticMethod() {
            System.out.println("normalStaticMethod");
        }

        public void normalMethod() {
            System.out.println("normalMethod");
        }

        @MyAnnotation(AnnotationClazzTag.class)
        public static void testStaticAnnotation(){
            System.out.println("testAnnotation");
        }

        @MyAnnotation(AnnotationClazzTag.class)
        public void testAnnotation(){
            System.out.println("testAnnotation");
        }

    }

    public static void main(String[] args) throws Exception {

        System.out.println("=====> enum clazz initialize");
        System.out.println(EnumClazzDemo.EnumClazzEngine.class);
        System.out.println(EnumClazzDemo.EnumClazzEngine.INSTANCE);
        System.out.println(EnumClazzDemo.EnumClazzEngine.INSTANCE.getTag());
        System.out.println(EnumClazzDemo.EnumClazzEngine.INSTANCE.getTag());

        System.out.println("=====> static clazz initialize use direct touch");
        System.out.println(StaticClazzDirectTouchDemo.class);
        System.out.println("before touch");
        StaticClazzDirectTouchDemo.directTouch();
        System.out.println("after touch");

        System.out.println("=====> static clazz initialize use indirect touch");
        System.out.println(StaticClazzIndirectTouchDemo.class);
        System.out.println("before indirect touch");
        StaticClazzIndirectTouchDemo.indirectTouch();
        System.out.println("after indirect touch");
        System.out.println("before direct touch");
        StaticClazzIndirectTouchDemo.directTouch();
        System.out.println("after direct touch");

        System.out.println("=====> annotation clazz initialize");
        System.out.println(AnnotationClazzDemo.class);
        System.out.println("before normal static method");
        AnnotationClazzDemo.normalStaticMethod();
        System.out.println("after normal static method");
        System.out.println("before testStaticAnnotation");
        AnnotationClazzDemo.testStaticAnnotation();
        System.out.println("after testStaticAnnotation");
        AnnotationClazzDemo annotationClazzDemo = new AnnotationClazzDemo();
        System.out.println("before normal method");
        annotationClazzDemo.normalMethod();
        System.out.println("after normal method");
        System.out.println("before testAnnotation");
        annotationClazzDemo.testAnnotation();
        System.out.println("after testAnnotation");
        System.out.println("before find Annotation Method");
        Method method = AnnotationClazzDemo.class.getMethod("testAnnotation");
        AnnotationClazzDemo.MyAnnotation myAnnotation = method.getAnnotation(AnnotationClazzDemo.MyAnnotation.class);
        System.out.println("myAnnotation = " + myAnnotation);
        System.out.println("after find Annotation Method");
        System.out.println("before use Annotation Method");
        Class<AnnotationClazzDemo.AnnotationClazzTag> annotationClazzTagClass = myAnnotation.value();
        System.out.println("myAnnotation.value() : "+annotationClazzTagClass);
        System.out.println("after use Annotation Method");
        System.out.println("before use Annotation Method");
        annotationClazzTagClass.getMethod("hello").invoke(null);
        System.out.println("after use Annotation Method");

    }

}
