package io.github.mikeiansky.maven.scope;


import io.github.mikeiansky.utils.CollKit;

import java.util.Collections;
import java.util.function.Function;

/**
 * @author mike ian
 * @date 2024/12/16
 * @desc
 **/
public class MavenProviderDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("hello maven");

        // 放在这里是 NoClassDefFoundError
        try {
            System.out.println(CollKit.toList(Collections.emptyList(), Function.identity()));
        } catch (Throwable e) {
            // Exception 错误无法捕捉
            e.printStackTrace();
        }

        System.out.println("==========> MavenClassNotFound");
        // 放在这里是 NoClassDefFoundError
        try {
            MavenNoClassDefFoundError.action();
        } catch (Throwable e) {
            // Exception 错误无法捕捉
            e.printStackTrace();
        }

        // class not found exception
        try {
            Class.forName("dddd.dddd");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("other start ... ");
        Other.hello();
        System.out.println("other complete ... ");

    }

}
