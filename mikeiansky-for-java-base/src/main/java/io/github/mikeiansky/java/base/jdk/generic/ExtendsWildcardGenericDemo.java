package io.github.mikeiansky.java.base.jdk.generic;

/**
 * @author mike ian
 * @date 2024/12/30
 * @desc
 **/
public class ExtendsWildcardGenericDemo {

    public static class GrandFather {
        public void hello() {
            System.out.println("hello grand father");
        }
    }

    public static class Father extends GrandFather {
        public void hello() {
            System.out.println("hello father");
        }

        public void helloFather() {
            System.out.println("hello father explicit");
        }
    }

    public static class Son extends Father {
        public void hello() {
            System.out.println("hello son");
        }

        public void helloSon() {
            System.out.println("hello son explicit");
        }
    }

    public interface BaseTool<T> {
        void useObj(T t);

        T create();
    }

    public static class GrandFatherTool implements BaseTool<GrandFather> {

        @Override
        public void useObj(GrandFather grandFather) {
            grandFather.hello();
        }

        public GrandFather create() {
            return new GrandFather();
        }

    }

    public static class FatherTool implements BaseTool<Father> {

        @Override
        public void useObj(Father father) {
            father.hello();
        }

        @Override
        public Father create() {
            return new Father();
        }
    }

    public static class SonTool implements BaseTool<Son> {

        @Override
        public void useObj(Son son) {
            son.hello();
        }

        public Son create() {
            return new Son();
        }

    }

    public static class GranderFatherClazz<T extends GrandFather> {
        private T grandFather;

        public GranderFatherClazz(T grandFather) {
            this.grandFather = grandFather;
            grandFather.hello();
        }
    }

    public static class FatherClazz<T extends Father> {
        private T father;

        public FatherClazz(T father) {
            this.father = father;
            father.hello();
            father.helloFather();
        }
    }

    public static class SonClazz<T extends Son> {
        private T son;

        public SonClazz(T son) {
            this.son = son;
            son.hello();
            son.helloFather();
            son.helloSon();
        }
    }

    public static void readGrandFatherTool(BaseTool<? super GrandFather> baseTool, GrandFather grandFather) {
        baseTool.useObj(grandFather);
//        baseTool.useObj(new GrandFather());  // ok
//        baseTool.useObj(new Father()); // ok
//        baseTool.useObj(new Son()); // ok
    }

    public static void readFatherTool(BaseTool<? super Father> baseTool, Father father) {
        baseTool.useObj(father);
////        baseTool.useObj(new GrandFather()); // error
//        baseTool.useObj(new Father()); // ok
//        baseTool.useObj(new Son()); // ok
    }

    public static void readSonTool(BaseTool<? super Son> baseTool, Son son) {
        baseTool.useObj(son);
////        baseTool.useObj(new GrandFather()); // error
////        baseTool.useObj(new Father()); // error
//        baseTool.useObj(new Son()); // ok
    }

    public static void readAllTool(BaseTool<?> baseTool, GrandFather grandFather) {
//        baseTool.useObj();
    }

    public static void useGrandFatherToolExtends(BaseTool<? extends GrandFather> baseTool) {
        GrandFather grandFather = baseTool.create();
        grandFather.hello();
    }

    public static void useFatherToolExtends(BaseTool<? extends Father> baseTool) {
        Father father = baseTool.create();
        father.hello();
    }

    public static void useSonToolExtends(BaseTool<? extends Son> baseTool) {
        Son son = baseTool.create();
        son.hello();
    }

    public static void useGrandFatherToolSuper(BaseTool<? super GrandFather> baseTool) {
        Object son = baseTool.create();
    }

    public static void useFatherToolSuper(BaseTool<? super Father> baseTool) {
        Object son = baseTool.create();
    }

    public static void useSonToolSuper(BaseTool<? super Son> baseTool) {
        Object son = baseTool.create();
    }

    public static void combineGrandFatherTool(BaseTool<? super GrandFather> baseTool, BaseTool<? extends GrandFather> grandFatherTool) {
        baseTool.useObj(grandFatherTool.create());
    }

    public static void combineFatherTool(BaseTool<? super Father> baseTool, BaseTool<? extends Father> fatherTool) {
        baseTool.useObj(fatherTool.create());
    }

    public static void combineSonTool(BaseTool<? super Son> baseTool, BaseTool<? extends Son> sonTool) {
        baseTool.useObj(sonTool.create());
    }

    public static GrandFather createGrandFatherByExtends(BaseTool<? extends GrandFather> baseTool) {
        GrandFather grandFather = baseTool.create();
//        Father father = baseTool.create();
//        Son son = baseTool.create();
        return baseTool.create();
    }

    public static Father createFatherByExtends(BaseTool<? extends Father> baseTool) {
        GrandFather grandFather = baseTool.create();
        Father father = baseTool.create();
//        Son son = baseTool.create();
        return baseTool.create();
    }

    public static Son createSonByExtends(BaseTool<? extends Son> baseTool) {
        GrandFather grandFather = baseTool.create();
        Father father = baseTool.create();
        Son son = baseTool.create();
        return baseTool.create();
    }

//    public static GrandFather createGrandFatherBySuper(BaseTool<? super GrandFather> baseTool) {
//        GrandFather grandFather = baseTool.create();
////        Father father = baseTool.create();
////        Son son = baseTool.create();
//        return baseTool.create();
//    }
//
//    public static Father createFatherBySuper(BaseTool<? super Father> baseTool) {
//        GrandFather grandFather = baseTool.create();
//        Father father = baseTool.create();
////        Son son = baseTool.create();
//        return baseTool.create();
//    }
//
//    public static Son createSonBySuper(BaseTool<? super Son> baseTool) {
//        GrandFather grandFather = baseTool.create();
//        Father father = baseTool.create();
//        Son son = baseTool.create();
//        return baseTool.create();
//    }

    public static void main(String[] args) {
        System.out.println("=============> use class start <==============");
        System.out.println("====> GranderFatherClazz");
        GranderFatherClazz<GrandFather> gfc = new GranderFatherClazz<>(new GrandFather());
        GranderFatherClazz<Father> fc = new GranderFatherClazz<>(new Father());
        GranderFatherClazz<Son> sc = new GranderFatherClazz<>(new Son());
        System.out.println("====> FatherClazz");
//        FatherClazz<GrandFather> fgfc = new FatherClazz<GrandFather>(new GrandFather());
        FatherClazz<Father> ffc = new FatherClazz<Father>(new Father());
        FatherClazz<Son> fsc = new FatherClazz<Son>(new Son());
        System.out.println("====> SonClazz");
//        SonClazz<GrandFather> sgfc = new SonClazz<GrandFather>(new GrandFather());
//        SonClazz<Father> sfc = new SonClazz<Father>(new Father());
        SonClazz<Son> ssc = new SonClazz<Son>(new Son());
        System.out.println("=============> use class start <==============");


        System.out.println("=============> read start <==============");
        System.out.println("====> readGrandFatherTool");
        readGrandFatherTool(new GrandFatherTool(), new GrandFather());
        readGrandFatherTool(new GrandFatherTool(), new Father());
        readGrandFatherTool(new GrandFatherTool(), new Son());
//        useGrandFatherTool(new FatherTool(), new GrandFather());
//        useGrandFatherTool(new FatherTool(), new Father());
//        useGrandFatherTool(new FatherTool(), new Son());
//        useGrandFatherTool(new SonTool(), new GrandFather());
//        useGrandFatherTool(new SonTool(), new Father());
//        useGrandFatherTool(new SonTool(), new Son());
        // --
        System.out.println("====> readFatherTool");
//        useFatherTool(new GrandFatherTool(), new GrandFather());
        readFatherTool(new GrandFatherTool(), new Father());
        readFatherTool(new GrandFatherTool(), new Son());
//        useFatherTool(new FatherTool(), new GrandFather());
        readFatherTool(new FatherTool(), new Father());
        readFatherTool(new FatherTool(), new Son());
//        useFatherTool(new SonTool(), new GrandFather());
//        useFatherTool(new SonTool(), new Father());
//        useFatherTool(new SonTool(), new Son());
        // --
        System.out.println("====> readSonTool");
//        useSonTool(new GrandFatherTool(), new GrandFather());
//        useSonTool(new GrandFatherTool(), new Father());
        readSonTool(new GrandFatherTool(), new Son());
//        useSonTool(new FatherTool(), new GrandFather());
//        useSonTool(new FatherTool(), new Father());
        readSonTool(new FatherTool(), new Son());
//        useSonTool(new SonTool(), new GrandFather());
//        useSonTool(new SonTool(), new Father());
        readSonTool(new SonTool(), new Son());
//        System.out.println("====> readAllTool");
//        readAllTool(new GrandFatherTool(), new GrandFather());
//        readAllTool(new GrandFatherTool(), new Father());
//        readAllTool(new GrandFatherTool(), new Son());
//        readAllTool(new FatherTool(), new GrandFather());
//        readAllTool(new FatherTool(), new Father());
//        readAllTool(new FatherTool(), new Son());
//        readAllTool(new SonTool(), new GrandFather());
//        readAllTool(new SonTool(), new Father());
//        readAllTool(new SonTool(), new Son());

        System.out.println("=============> read complete <==============");
        System.out.println("=============> use start <==============");
        System.out.println("====> useGrandFatherToolExtends");
        useGrandFatherToolExtends(new GrandFatherTool());
        useGrandFatherToolExtends(new FatherTool());
        useGrandFatherToolExtends(new SonTool());
        System.out.println("====> useFatherToolExtends");
//        useFatherTool(new GrandFatherTool());
        useFatherToolExtends(new FatherTool());
        useFatherToolExtends(new SonTool());
        System.out.println("====> useSonToolExtends");
//        useSonTool(new GrandFatherTool());
//        useSonTool(new FatherTool());
        useSonToolExtends(new SonTool());

        System.out.println("====> useGrandFatherToolSuper");
        useGrandFatherToolSuper(new GrandFatherTool());
//        useGrandFatherToolSuper(new FatherTool());
//        useGrandFatherToolSuper(new SonTool());
        System.out.println("====> useFatherToolSuper");
        useFatherToolSuper(new GrandFatherTool());
        useFatherToolSuper(new FatherTool());
//        useFatherToolSuper(new SonTool());
        System.out.println("====> useSonToolSuper");
        useSonToolSuper(new GrandFatherTool());
        useSonToolSuper(new FatherTool());
        useSonToolSuper(new SonTool());


        System.out.println("=============> use complete <==============");
        System.out.println("=============> combine start <==============");
        System.out.println("====> combineGrandFatherTool");
        combineGrandFatherTool(new GrandFatherTool(), new GrandFatherTool());
        combineGrandFatherTool(new GrandFatherTool(), new FatherTool());
        combineGrandFatherTool(new GrandFatherTool(), new SonTool());
//        combineGrandFatherTool(new FatherTool(), new GrandFatherTool());
//        combineGrandFatherTool(new FatherTool(), new FatherTool());
//        combineGrandFatherTool(new FatherTool(), new SonTool());
//        combineGrandFatherTool(new SonTool(), new GrandFatherTool());
//        combineGrandFatherTool(new SonTool(), new FatherTool());
//        combineGrandFatherTool(new SonTool(), new SonTool());


        System.out.println("====> combineFatherTool");
//        combineFatherTool(new GrandFatherTool(), new GrandFatherTool());
        combineFatherTool(new GrandFatherTool(), new FatherTool());
        combineFatherTool(new GrandFatherTool(), new SonTool());
//        combineFatherTool(new FatherTool(), new GrandFatherTool());
        combineFatherTool(new FatherTool(), new FatherTool());
        combineFatherTool(new FatherTool(), new SonTool());
//        combineFatherTool(new SonTool(), new GrandFatherTool());
//        combineFatherTool(new SonTool(), new FatherTool());
//        combineFatherTool(new SonTool(), new SonTool());


        System.out.println("====> combineSonTool");
//        combineSonTool(new GrandFatherTool(), new GrandFatherTool());
//        combineSonTool(new GrandFatherTool(), new FatherTool());
        combineSonTool(new GrandFatherTool(), new SonTool());
//        combineSonTool(new FatherTool(), new GrandFatherTool());
//        combineSonTool(new FatherTool(), new FatherTool());
        combineSonTool(new FatherTool(), new SonTool());
//        combineSonTool(new SonTool(), new GrandFatherTool());
//        combineSonTool(new SonTool(), new FatherTool());
        combineSonTool(new SonTool(), new SonTool());

        System.out.println("=============> combine complete <==============");

    }

}
