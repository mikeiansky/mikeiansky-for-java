package io.github.mikeiansky.java.digest;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.security.Security;

/**
 *
 * @author mike ian
 * @date 2025/6/10
 * @desc
 **/
public class BLAKE2sExample {

    public static String blake2s128(String input) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        MessageDigest digest = MessageDigest.getInstance("BLAKE2s-128");
        byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString(); // 32 字符
    }

    public static void main(String[] args) throws Exception {
        String input = "hello";
        String blake2sHash = blake2s128(input);
        System.out.println("BLAKE2s-128 (32字符): " + blake2sHash);
    }

}
