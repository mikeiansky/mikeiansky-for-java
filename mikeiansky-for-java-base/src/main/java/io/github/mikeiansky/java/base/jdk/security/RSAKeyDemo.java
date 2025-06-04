package io.github.mikeiansky.java.base.jdk.security;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * @author mike ian
 * @date 2025/6/4
 * @desc
 **/
public class RSAKeyDemo {

    public static void main(String[] args) {

        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(2048);
            KeyPair keyPair = keyPairGen.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            System.out.println("Public Key: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
            System.out.println("Private Key: " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));
            Files.write(Paths.get("public.key"), publicKey.getEncoded());
            Files.write(Paths.get("private.key"), privateKey.getEncoded());
            System.out.println(Paths.get("public.key"));
            System.out.println(Paths.get("private.key"));

            Files.write(Paths.get("public.pem"),
                    ("-----BEGIN PUBLIC KEY-----\n" +
                            Base64.getMimeEncoder(64, "\n".getBytes()).encodeToString(publicKey.getEncoded()) +
                            "\n-----END PUBLIC KEY-----\n").getBytes());
            Files.write(Paths.get("private.pem"),
                    ("-----BEGIN PRIVATE KEY-----\n" +
                            Base64.getMimeEncoder(64, "\n".getBytes()).encodeToString(privateKey.getEncoded()) +
                            "\n-----END PRIVATE KEY-----\n").getBytes());
            System.out.println("公钥已写入 public.pem，私钥已写入 private.pem");

            System.out.println("公钥已写入 public.key，私钥已写入 private.key");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
