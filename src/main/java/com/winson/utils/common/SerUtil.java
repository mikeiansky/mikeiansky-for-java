package com.winson.utils.common;

import java.io.*;

/**
 * @author winson
 * @date 2021/8/30
 **/
public class SerUtil {

    public static byte[] encode(Object obj) {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            return bo.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T decode(byte[] bs, Class<T> clazz) {
        ObjectInputStream oi = null;
        try {
            oi = new ObjectInputStream(new ByteArrayInputStream(bs));
            return (T) oi.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
