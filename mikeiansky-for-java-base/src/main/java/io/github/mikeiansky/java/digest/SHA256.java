package io.github.mikeiansky.java.digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author mike ian
 * @date 2025/6/10
 * @desc
 **/
public class SHA256 {

    public static String sha256(String input) {
        try {
            // 创建 MessageDigest 实例，指定算法为 SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // 计算哈希值（输入需转换为字节数组）
            byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));

            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b); // 转换为无符号十六进制
                if (hex.length() == 1) hexString.append('0'); // 补零
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException e) {
            throw new RuntimeException("SHA-256 计算失败", e);
        }
    }

    public static void main(String[] args) {
        String input = "hello";
        String sha256Hash = sha256(input);
        System.out.println("SHA-256(\"" + input + "\") = " + sha256Hash);
    }

}
