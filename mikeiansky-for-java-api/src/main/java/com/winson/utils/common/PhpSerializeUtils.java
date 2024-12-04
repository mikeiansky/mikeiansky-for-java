package com.winson.utils.common;

import lombok.Data;

import java.io.*;
import java.util.Base64;

/**
 * @author mike ian
 * @date 2023/5/16
 * @desc
 **/
public class PhpSerializeUtils {

    @Data
    public static class Person implements Serializable{
        private String name;
        private int age;
    }

    public static String serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(out);
        objOut.writeObject(obj);
        byte[] bytes = out.toByteArray();
        return serializeData(bytes, obj.getClass());
    }

    public static Object unserialize(String str) throws IOException, ClassNotFoundException {
        String[] parts = str.split(":");
        Class<?> clazz = getClassFromName(parts[0]);
        byte[] bytes = Base64.getDecoder().decode(parts[1]);
        ObjectInputStream objIn = new ObjectInputStream(new ByteArrayInputStream(bytes));
        return objIn.readObject();
    }

    private static String serializeData(byte[] bytes, Class<?> clazz) {
        String className = clazz.getName();
        String data = Base64.getEncoder().encodeToString(bytes);
        return "O:" + className.length() + ":" + className + ":" + data.length() + ":{" + data + "}";
    }

    private static Class<?> getClassFromName(String name) throws ClassNotFoundException {
        return Class.forName(name);
    }

    public static void main(String[] args) throws IOException {
        Person person = new Person();
        person.setName("ciwei");
        person.setAge(7);

        String[] hello = new String[]{
            "hello","world"
        };

        System.out.println(PhpSerializeUtils.serialize(hello));
    }


}
