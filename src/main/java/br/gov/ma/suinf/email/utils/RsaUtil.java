package br.gov.ma.suinf.email.utils;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RsaUtil {

    @Value("${rsa.private-key-path}")
    private String privateKeyPath;

    public String decrypt(String base64Encrypted) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(base64Encrypted);
        PrivateKey privateKey = getPrivateKey();

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(encryptedBytes), StandardCharsets.UTF_8);
    }

    private PrivateKey getPrivateKey() throws Exception {
        byte[] keyBytes = Files.readAllBytes(Paths.get(privateKeyPath));
        String privateKeyContent = new String(keyBytes)
            .replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("-----END PRIVATE KEY-----", "")
            .replaceAll("\\s+", "");

        byte[] decoded = Base64.getDecoder().decode(privateKeyContent);
        return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
    }
}
