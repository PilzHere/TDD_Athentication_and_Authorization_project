package org.login.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

/**
 * @author pilzhere
 * @created 18/11/2021 - 3:49 PM
 * @project TDD_Athentication_and_Authorization_project
 */
public class PasswordUtils {
    /*public static boolean generateNewHashedPassword (String password) {
        final String salt = generateSalt(512).get();
        final String key = hashPassword(password, salt).get();

        return verifyPassword(password, key, salt);
    }*/

    public static String generateNewHashedPassword (String salt, String password) {
        final String key = hashPassword512(password, salt).get();

        //return verifyPassword(password, key, salt);

        return key;
    }

    private static final int ITERATIONS = (int) (65536 * Math.PI);

    public static Optional<String> hashPassword512 (String password, String salt) {
        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        final int keyLength = 512;
        final String algo = "PBKDF2WithHmacSHA512";

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, keyLength);

        Arrays.fill(chars, Character.MIN_VALUE);

        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(algo);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(securePassword));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            System.err.println("Exception encountered in hashPassword()");
            return Optional.empty();

        } finally {
            spec.clearPassword();
        }
    }

    public static Optional<String> hashPassword384 (String password, String salt) {
        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        final int keyLength = 384;
        final String algo = "PBKDF2WithHmacSHA384";

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, keyLength);

        Arrays.fill(chars, Character.MIN_VALUE);

        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(algo);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(securePassword));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            System.err.println("Exception encountered in hashPassword()");
            return Optional.empty();

        } finally {
            spec.clearPassword();
        }
    }

    public static Optional<String> hashPassword256 (String password, String salt) {
        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        final int keyLength = 256;
        final String algo = "PBKDF2WithHmacSHA256";

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, keyLength);

        Arrays.fill(chars, Character.MIN_VALUE);

        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(algo);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(securePassword));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            System.err.println("Exception encountered in hashPassword()");
            return Optional.empty();

        } finally {
            spec.clearPassword();
        }
    }

    private static final SecureRandom RAND = new SecureRandom();

    public static Optional<String> generateSalt (final int length) {

        if (length < 1) {
            System.err.println("error in generateSalt: length must be > 0");
            return Optional.empty();
        }

        byte[] salt = new byte[length];
        RAND.nextBytes(salt);

        return Optional.of(Base64.getEncoder().encodeToString(salt));
    }

    public static boolean verifyPassword (String password, String key, String salt) {
        Optional<String> optEncrypted = hashPassword512(password, salt);
        if (!optEncrypted.isPresent()) return false;
        return optEncrypted.get().equals(key);
    }
}
