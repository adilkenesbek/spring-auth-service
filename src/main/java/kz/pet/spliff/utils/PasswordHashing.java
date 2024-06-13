package kz.pet.spliff.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class PasswordHashing {
    // Method to generate a random salt
    public static byte[] generateSalt() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstanceStrong();
        byte[] salt = new byte[16]; // You can change the size as needed
        random.nextBytes(salt);
        return salt;
    }

    // Method to hash a password with a given salt
    public static String hashPassword(char[] password, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Define the iterations and key length
        int iterations = 65536;
        int keyLength = 256;

        // Create PBEKeySpec with password, salt, iterations, and key length
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = keyFactory.generateSecret(spec).getEncoded();

        // Return the hashed password as a Base64 encoded string
        return Base64.getEncoder().encodeToString(hash);
    }

    // Method to convert byte array to Base64 string
    public static String toBase64(byte[] array) {
        return Base64.getEncoder().encodeToString(array);
    }

    // Method to convert Base64 string to byte array
    public static byte[] fromBase64(String str) {
        return Base64.getDecoder().decode(str);
    }
}
