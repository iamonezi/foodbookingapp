package com.phunlh2001.prm392_beverages.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    public static String Md5(String str) {
        final String MD5 = "MD5";

        try {
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(str.getBytes());
            byte[] messageDigest = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aDigest: messageDigest) {
                String hex = Integer.toHexString(0xFF & aDigest);
                while (hex.length() < 2) {
                    hex = "0" + hex;
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.fillInStackTrace();
        }
        return "";
    }
}
