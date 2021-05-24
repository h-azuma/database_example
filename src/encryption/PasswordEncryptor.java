package encryption;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PasswordEncryptor {
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final int COUNT = 10000;
    private static final int LENGTH = 256;

    public static String encryptPassword(String password, String salt) {

        char[] charPassword = password.toCharArray();
        byte[] hashedSalt = getHashedSalt(salt);

        PBEKeySpec keySpec = new PBEKeySpec(charPassword, hashedSalt, COUNT, LENGTH);

        SecretKeyFactory skf = null;
        try {
            skf = SecretKeyFactory.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        SecretKey secretKey = null;
        try {
            assert skf != null;
            secretKey = skf.generateSecret(keySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        assert secretKey != null;
        byte[] encryptedPassword = secretKey.getEncoded();
        StringBuilder sb = new StringBuilder(64);
        for (byte b : encryptedPassword) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    static byte[] getHashedSalt(String salt) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        assert messageDigest != null;
        messageDigest.update(salt.getBytes());
        return messageDigest.digest();
    }
}
