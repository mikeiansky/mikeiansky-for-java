package io.github.mikeiansky.maven.scope;

import io.github.mikeiansky.utils.CollKit;

import java.util.Collections;
import java.util.function.Function;

/**
 * @author mike ian
 * @date 2024/12/16
 * @desc
 **/
public class MavenNoClassDefFoundError {

    public static void action(){
        System.out.println(CollKit.toList(Collections.emptyList(), Function.identity()));
    }

}
