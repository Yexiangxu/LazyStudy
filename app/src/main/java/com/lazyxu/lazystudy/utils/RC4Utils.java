package com.lazyxu.lazystudy.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class RC4Utils {
    private final byte[] S = new byte[256];
    private final byte[] T = new byte[256];
    private  int keylen;

    public static void main(String[] args) throws IOException {

    }

    public RC4Utils(final byte[] key) {
        if (key.length < 1 || key.length > 256) {
            throw new IllegalArgumentException(
                    "key must be between 1 and 256 bytes");
        } else {
            keylen = key.length;
            for (int i = 0; i < 256; i++) {
                S[i] = (byte) i;
                T[i] = key[i % keylen];
            }
            int j = 0;
            for (int i = 0; i < 256; i++) {
                j = (j + S[i] + T[i]) & 0xFF;
                byte temp = S[i];
                S[i] = S[j];
                S[j] = temp;
            }
        }
    }


    public byte[] encrypt(final byte[] plaintext) {
        final byte[] ciphertext = new byte[plaintext.length];
        int i = 0, j = 0, k, t;
        for (int counter = 0; counter < plaintext.length; counter++) {
            i = (i + 1) & 0xFF;
            j = (j + S[i]) & 0xFF;
            byte temp = S[i];
            S[i]=S[j];
            S[j]=temp;
            t = (S[i] + S[j]) & 0xFF;
            k = S[t];
            ciphertext[counter] = (byte) (plaintext[counter] ^ k);
        }
        return ciphertext;
    }

    public static byte[] encoding(String text, String key) {

        RC4Utils rc4 = null;
        try {
            rc4 = new RC4Utils(key.getBytes());
            return rc4.encrypt(text.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {

        }
        return null;
    }

    public byte[] decrypt(final byte[] ciphertext) {
        return encrypt(ciphertext);
    }
}
