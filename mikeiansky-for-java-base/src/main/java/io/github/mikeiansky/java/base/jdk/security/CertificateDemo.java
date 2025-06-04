package io.github.mikeiansky.java.base.jdk.security;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.Base64;

/**
 * @author mike ian
 * @date 2025/6/4
 * @desc
 **/
public class CertificateDemo {

    public static X509Certificate generateSelfSignedX509Certificate(PublicKey publicKey, PrivateKey privateKey, java.math.BigInteger serial, long now) throws Exception {
        org.bouncycastle.asn1.x500.X500Name issuer = new org.bouncycastle.asn1.x500.X500Name("CN=Test, OU=Dev, O=Example, L=Beijing, ST=Beijing, C=CN");
        org.bouncycastle.cert.X509v3CertificateBuilder certBuilder =
                new org.bouncycastle.cert.X509v3CertificateBuilder(
                        issuer,
                        serial,
                        new java.util.Date(now),
                        new java.util.Date(now + 365L * 24 * 60 * 60 * 1000),
                        issuer,
                        org.bouncycastle.asn1.x509.SubjectPublicKeyInfo.getInstance(publicKey.getEncoded())
                );
        org.bouncycastle.operator.ContentSigner signer = new org.bouncycastle.operator.jcajce.JcaContentSignerBuilder("SHA256withRSA")
                .build(privateKey);
        org.bouncycastle.cert.X509CertificateHolder certHolder = certBuilder.build(signer);
        return new org.bouncycastle.cert.jcajce.JcaX509CertificateConverter()
                .setProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider())
                .getCertificate(certHolder);
    }

    /**
     * PEM编码
     */
    public static String toPem(String type, byte[] encoded) {
        String base64 = Base64.getMimeEncoder(64, "\n".getBytes()).encodeToString(encoded);
        return "-----BEGIN " + type + "-----\n" + base64 + "\n-----END " + type + "-----\n";
    }

    public static void main(String[] args) {

        try {
            // 生成RSA密钥对
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(2048);
            KeyPair keyPair = keyPairGen.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // 生成自签名X.509证书
            long now = System.currentTimeMillis();
            java.math.BigInteger serial = new java.math.BigInteger(64, new SecureRandom());
            X509Certificate cert = generateSelfSignedX509Certificate(publicKey, privateKey, serial, now);

            // 打印公钥、私钥、证书（PEM格式）
            String pubPem = toPem("PUBLIC KEY", publicKey.getEncoded());
            String priPem = toPem("PRIVATE KEY", privateKey.getEncoded());
            String certPem = toPem("CERTIFICATE", cert.getEncoded());
            System.out.println(pubPem);
            System.out.println(priPem);
            System.out.println(certPem);

            // 写入文件
            Files.write(Paths.get("rsa_public.pem"), pubPem.getBytes());
            Files.write(Paths.get("rsa_private.pem"), priPem.getBytes());
            Files.write(Paths.get("rsa_cert.pem"), certPem.getBytes());
            System.out.println("密钥和证书已写入文件：rsa_public.pem, rsa_private.pem, rsa_cert.pem");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
