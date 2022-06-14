package com.winson.jdkapi.annotation.v1;

import java.lang.reflect.Proxy;

/**
 * @author winson
 * @date 2021/6/15
 **/
public class TestAnnotationV2 {


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        System.out.println("test annotation v2 start ... ");

        AnnotationService service = (AnnotationService) Proxy.newProxyInstance(AnnotationServiceImpl.class.getClassLoader(),
                AnnotationServiceImpl.class.getInterfaces(),
                new AnnotationServiceHandler(new AnnotationServiceImpl()));
        service.addUser("12345678912345678912");
//        service.addUser("123456789123456789123");
        AnnotationUser user = new AnnotationUser();
        user.setUserName("winson-123");
        service.editUser(user);
        service.addFriend("china", 20, "yang", "chang", "jiang");

//        AnnotationUser user = new AnnotationUser();
////        user.setUserName("winson");
//        Field field = AnnotationUser.class.getDeclaredField("userName");
//        field.setAccessible(true);
//        Object value = field.get(user);
//        System.out.println("value : " + value);

//        Stream.of(AnnotationService.class.getMethods())
//                .forEach(method -> {
//                    System.out.println("---------- method ----------");
//                    System.out.println("method name : " + method.getName());
//                    Stream.of(method.getParameters())
//                            .forEach(parameter -> {
//                                System.out.println(parameter.getName());
//                            });
//                });

        System.out.println("test annotation v2 stop ... ");
    }

}
