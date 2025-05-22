package io.github.mikeiansky.java.base.jdk.clazz.resource;

import cn.hutool.core.util.HexUtil;
import com.alibaba.fastjson2.JSON;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author mike ian
 * @date 2025/5/22
 * @desc
 **/
public class JarUrlResourceDemo {

    public static void main(String[] args) throws IOException {

        String clazzName = JSON.class.getName();
        System.out.println("clazzName : " + clazzName);
        String resourcePath = clazzName.replace('.', '/') + ".class";
        System.out.println("JSON.class : "+JSON.class);
        URL resourceURL = JSON.class.getClassLoader().getResource(resourcePath);
        System.out.println("resourceURL : " + resourceURL);
        System.out.println("resourceURL.getFile() : "+resourceURL.getFile());
        System.out.println("resourceURL.getPath() : "+resourceURL.getPath());
        File file = new File(resourceURL.getFile());
        System.out.println("file : "+file);
        System.out.println("file.exists() : "+file.exists());
        System.out.println("file.isDirectory() : "+file.isDirectory());
        System.out.println("file.isFile() : "+file.isFile());
        System.out.println("file.isAbsolute() : "+file.isAbsolute());
        System.out.println("file.getPath() : "+file.getPath());
        System.out.println("file.length() : "+file.length());

        System.out.println("============= jar connection =============");
        JarURLConnection urlConnection = (JarURLConnection) resourceURL.openConnection();
        System.out.println("urlConnection : "+urlConnection);

        JarEntry jarEntry = urlConnection.getJarEntry();
        System.out.println("jarEntry.getRealName() : "+jarEntry.getRealName());
        System.out.println("jarEntry.getName() : "+jarEntry.getName());
        System.out.println("jarEntry.getAttributes() : "+jarEntry.getAttributes());
        System.out.println("jarEntry.getCertificates() : "+jarEntry.getCertificates());
        System.out.println("jarEntry.getCodeSigners() : "+jarEntry.getCodeSigners());
        JarFile jarFile = urlConnection.getJarFile();
        System.out.println("jarFile : "+jarFile);
        System.out.println("jarFile.getName() : "+jarFile.getName());
        System.out.println("jarFile.getVersion() : "+jarFile.getVersion());
        System.out.println("jarFile.getComment() : "+jarFile.getComment());
        System.out.println("jarFile.getManifest() : "+jarFile.getManifest());
//        System.out.println(HexUtil.encodeHex(urlConnection.getInputStream().readAllBytes()));

        String filePath = resourceURL.getPath().split("!")[0];
        System.out.println("filePath : "+filePath);
        String classPath = resourceURL.getPath().split("!")[1];
        System.out.println("classPath : "+classPath);

        try (InputStream in = urlConnection.getInputStream()){
            System.out.println("use input stream .... ");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
