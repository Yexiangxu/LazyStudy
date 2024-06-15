package lazyxu;


import org.junit.Test;

public class Rc4Use {

    public static byte[] rc4(String key, byte[] data) {
        int[] sbox = new int[256];
        int i = 0, j = 0;

        // 初始化S盒
        int[] keyArray = new int[256];
        for (i = 0; i < 256; i++) {
            keyArray[i] = key.charAt(i % key.length());
            sbox[i] = i;
        }

        // 打乱S盒
        for (i = 0, j = 0; i < 256; i++) {
            j = (j + sbox[i] + keyArray[i]) % 256;
            int temp = sbox[i];
            sbox[i] = sbox[j];
            sbox[j] = temp;
        }

        // 加密
        byte[] result = new byte[data.length];
        for (int k = 0, x = 0, y = 0; k < data.length; k++) {
            x = (x + 1) % 256;
            y = (y + sbox[x]) % 256;
            int temp = sbox[x];
            sbox[x] = sbox[y];
            sbox[y] = temp;
            result[k] = (byte) (data[k] ^ sbox[(sbox[x] + sbox[y]) % 256]);
        }

        return result;
    }

    @Test
    public  void main() {
        String key = "SecretKey";
        String plaintext = "Hello, RC4!";

        try {
            byte[] keyBytes = key.getBytes("UTF-8");
            byte[] plaintextBytes = plaintext.getBytes("UTF-8");

            // 加密
            byte[] encrypted = rc4(key, plaintextBytes);
            System.out.println("Encrypted: " + bytesToHex(encrypted));

            // 解密
            byte[] decrypted = rc4(key, encrypted);
            System.out.println("Decrypted: " + new String(decrypted, "UTF-8"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X ", b));
        }
        return result.toString();
    }
}
