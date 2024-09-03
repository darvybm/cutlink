package edu.pucmm.eict.utils;

import java.util.Base64;
import java.util.UUID;

public class UUIDShortener {

    public static String Shorten(UUID uuid) {
        byte[] uuidBytes = asBytes(uuid);
        String base64 = Base64.getUrlEncoder().withoutPadding().encodeToString(uuidBytes);
        return base64;
    }

    public static byte[] asBytes(UUID uuid) {
        byte[] bytes = new byte[16];
        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();
        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte) ((msb >>> 8 * (7 - i)) & 0xFF);
            bytes[8 + i] = (byte) ((lsb >>> 8 * (7 - i)) & 0xFF);
        }
        return bytes;
    }
}
