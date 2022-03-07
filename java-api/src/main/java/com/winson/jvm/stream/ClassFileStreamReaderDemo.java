package com.winson.jvm.stream;

import jdk.internal.org.objectweb.asm.ClassReader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author winson
 * @date 2022/3/7
 **/
public class ClassFileStreamReaderDemo {

    public String name = "winson";

    public static void main(String[] args) throws IOException {
        Class clazz = ClassFileStreamReaderDemo.class;
        System.out.println(clazz.getName());
        String cn = clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1) + ".class";

        URL url = clazz.getResource(cn);
        System.out.println(url);
        InputStream in = clazz.getResourceAsStream(cn);

        FileOutputStream out = new FileOutputStream("D:\\cz.class");
        int length = 0;
        byte[] buf = new byte[1024];
        while ((length = in.read(buf, 0, 1024)) >= 0) {
            out.write(buf, 0, length);
            out.flush();
        }

        out.close();
        in.close();
        System.out.println(cn);


    }

}
