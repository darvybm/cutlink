package edu.pucmm.eict.utils;

import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class URLShortener {

        public static String shortenUrl(String longUrl) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hashBytes = md.digest(longUrl.getBytes());
                BigInteger hashNum = new BigInteger(1, hashBytes);
                String shortUrl = hashNum.toString(36);
                return shortUrl.substring(0, 7); // return the first 7 characters of the hash as the shortened URL
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            }
        }

}