package com.winson.syntax.jvm.oom;

import java.util.HashSet;
import java.util.Set;

/**
 * @author winson
 * @date 2021/6/25
 **/
public class RuntimeConstantPoolOOMDemo {

    // -XX:PermSize=6M -XX:MaxPermSize=6M
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        short i= 0;
        while (true){
            set.add(String.valueOf(i++).intern());
        }
    }

}
