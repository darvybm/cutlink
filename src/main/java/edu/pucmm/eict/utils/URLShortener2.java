package edu.pucmm.eict.utils;

import java.security.MessageDigest;
import java.util.Base64;

public class URLShortener2 {
    // 6 bytes = 48 bits = 8 characters
    // Es recomendable usar un número superior a 6 bytes para evitar colisiones
    private static final int CODE_LENGTH = 6;
    private static final String HASH_ALGORITHM = "SHA-256";


    public static String generateShortCode(String longUrl) {
        try {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] hash = digest.digest(longUrl.getBytes());
            byte[] codeBytes = new byte[CODE_LENGTH];
            System.arraycopy(hash, 0, codeBytes, 0, CODE_LENGTH);
            return Base64.getUrlEncoder().withoutPadding().encodeToString(codeBytes);
        } catch (Exception e) {
            throw new RuntimeException("Could not generate short code", e);
        }
    }

}