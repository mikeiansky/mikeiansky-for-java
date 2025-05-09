package io.github.mikeiansky.java.base.jdk.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author mike ian
 * @date 2025/5/9
 * @desc
 **/
public class GenericBaseDemo {

    public static class Tag {
    }

    public static class TagFather extends Tag {

    }

    public static class TagSon extends TagFather {

    }

    public static class TagOther {
    }

    public static class TagOtherFather extends TagOther {
    }

    public static class TagOtherSon extends TagOtherFather {
    }

//    public interface Face<T extends Tag> {
//    }

    public interface FaceTwo<T extends Tag, V extends TagOther> {
    }

    public interface FaceThree {
    }

    public static class One implements Face<TagFather> {

    }

    public static class Two implements FaceTwo<TagFather, TagOtherSon> {

    }

    public static class Three implements FaceThree {

    }

    public static class Four<R extends Tag, S, T> {

    }

    public static class Five extends Four<TagFather, TagSon, TagOther> {

    }

    public static class Help<T> {

    }

    public static void userMethod(Help<? super TagSon> help1, Help<? extends TagOther> help2) {
        System.out.println("============ help1   ============");
        for (TypeVariable<? extends Class<? extends Help>> typeParameter : help1.getClass().getTypeParameters()) {
            System.out.println(typeParameter);
            for (Type bound : typeParameter.getBounds()) {
                System.out.println(bound);
            }
        }
        System.out.println("============ help2   ============");
        for (TypeVariable<? extends Class<? extends Help>> typeParameter : help2.getClass().getTypeParameters()) {
            System.out.println(typeParameter);
            for (Type bound : typeParameter.getBounds()) {
                System.out.println(bound);
            }        }
    }

    public static void main(String[] args) {
        System.out.println("============ one   ============");
        Class<One> oneClazz = One.class;
        System.out.println(oneClazz);
        System.out.println(oneClazz.getGenericInterfaces()[0].getClass());
        ParameterizedType oneType = (ParameterizedType) oneClazz.getGenericInterfaces()[0];
        System.out.println("getTypeName : " + oneType.getTypeName());
        System.out.println("getOwnerType : " + oneType.getOwnerType());
        System.out.println("getRawType : " + oneType.getRawType());
        for (Type actualTypeArgument : oneType.getActualTypeArguments()) {
            System.out.println("actualTypeArgument : " + actualTypeArgument);
        }

        System.out.println("============ two   ============");
        Class<Two> twoClazz = Two.class;
        System.out.println(twoClazz);
        System.out.println(twoClazz.getGenericInterfaces()[0].getClass());
        ParameterizedType twoType = (ParameterizedType) twoClazz.getGenericInterfaces()[0];
        System.out.println("getTypeName : " + twoType.getTypeName());
        System.out.println("getOwnerType : " + twoType.getOwnerType());
        System.out.println("getRawType : " + twoType.getRawType());
        for (Type actualTypeArgument : twoType.getActualTypeArguments()) {
            System.out.println("actualTypeArgument : " + actualTypeArgument);
        }

        System.out.println("============ three ============");
        Class<Three> threeClazz = Three.class;
        System.out.println(threeClazz);
        System.out.println(threeClazz.getGenericInterfaces()[0].getClass());
        System.out.println(threeClazz.getInterfaces().length);
        for (TypeVariable<Class<Three>> typeParameter : threeClazz.getTypeParameters()) {
            System.out.println("typeParameter : " + typeParameter);
        }

//        ParameterizedType threeType = (ParameterizedType) threeClazz.getGenericInterfaces()[0];
//        System.out.println("getTypeName : " + threeType.getTypeName());
//        System.out.println("getOwnerType : " + threeType.getOwnerType());
//        System.out.println("getRawType : " + threeType.getRawType());
//        for (Type actualTypeArgument : threeType.getActualTypeArguments()) {
//            System.out.println("actualTypeArgument : " + actualTypeArgument);
//        }

        System.out.println("============ four ============");
        Class<? extends Four> fourClazz = Four.class;
        System.out.println(fourClazz);
        for (TypeVariable<? extends Class<? extends Four>> typeParameter : fourClazz.getTypeParameters()) {
            System.out.println("typeParameter : " + typeParameter);
            for (Type bound : typeParameter.getBounds()) {
                System.out.println("bound : " + bound);
//                System.out.println("bound : " + bound.getClass());
            }
        }

        System.out.println("============ five ============");
        Class<Five> fiveClazz = Five.class;
        System.out.println(fiveClazz);
        for (TypeVariable<Class<Five>> typeParameter : fiveClazz.getTypeParameters()) {
            System.out.println("typeParameter : " + typeParameter);
        }
        System.out.println("five.getSuperclass() : " + fiveClazz.getSuperclass());
        for (TypeVariable<? extends Class<? super Five>> typeParameter : fiveClazz.getSuperclass().getTypeParameters()) {
            System.out.println("typeParameter : " + typeParameter);
        }
        System.out.println("five.getGenericSuperclass() : " + fiveClazz.getGenericSuperclass());
        System.out.println("five.getGenericSuperclass().getClass() : " + fiveClazz.getGenericSuperclass().getClass());
        ParameterizedType fiveParameterType = (ParameterizedType) fiveClazz.getGenericSuperclass();
        System.out.println("fiveParameterType : " + fiveParameterType);
        System.out.println("getOwnerType() : " + fiveParameterType.getOwnerType());
        System.out.println("getRawType() : " + fiveParameterType.getRawType());
        for (Type actualTypeArgument : fiveParameterType.getActualTypeArguments()) {
            System.out.println("actualTypeArgument : " + actualTypeArgument);
        }

        userMethod(new Help<Tag>(), new Help<>());

    }

}
