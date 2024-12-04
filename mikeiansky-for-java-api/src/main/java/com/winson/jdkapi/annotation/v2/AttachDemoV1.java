package com.winson.jdkapi.annotation.v2;

import java.lang.reflect.Method;

/**
 * @author winson
 * @date 2022/6/10
 **/
public class AttachDemoV1 {

    public static void main(String[] args) throws NoSuchMethodException {

        Method parentActiveMethod = ParentAttach.class.getMethod("active");
        System.out.println("parent with attach : " + parentActiveMethod.isAnnotationPresent(Attach.class));
        Method sonActiveMethod = SonAttach.class.getMethod("active");
        System.out.println("son with attach : " + sonActiveMethod.isAnnotationPresent(Attach.class));

        // 这是从写了，起始是另外一个内，是内部类
        ParentAttach pat1 = new ParentAttach() {
            @Override
            public void active() {
                super.active();
            }
        };
        System.out.println(pat1.getClass());
        Method pat1ActiveMethod = pat1.getClass().getMethod("active");
        System.out.println("pat1 with attach : " + pat1ActiveMethod.isAnnotationPresent(Attach.class));

        ParentAttach pat2 = new ParentAttach() {
            @Attach
            @Override
            public void active() {
                super.active();
            }
        };
        Method pat2ActiveMethod = pat2.getClass().getMethod("active");
        System.out.println("pat2 with attach : " + pat2ActiveMethod.isAnnotationPresent(Attach.class));

    }

}
