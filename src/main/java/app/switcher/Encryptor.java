package app.switcher;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class Encryptor {

    public static String encrypt(String plainText, String password) {
        try {
            // GENERATE password (not needed if you have a password already)
            if (password == null || password.isEmpty()) {
                return null;
            }

            // GENERATE random salt (needed for PBKDF2)
            final byte[] salt = new byte[64];

            // DERIVE key (from password and salt)
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            KeySpec passwordBasedEncryptionKeySpec = new PBEKeySpec(password.toCharArray(), salt, 10000, 256);
            SecretKey secretKeyFromPBKDF2 = secretKeyFactory.generateSecret(passwordBasedEncryptionKeySpec);
            SecretKey key = new SecretKeySpec(secretKeyFromPBKDF2.getEncoded(), "AES");

            // GENERATE random nonce (number used once)
            final byte[] nonce = new byte[32];
            //random.nextBytes(nonce);

            // ENCRYPTION
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec spec = new GCMParameterSpec(16 * 8, nonce);
            cipher.init(Cipher.ENCRYPT_MODE, key, spec);

            byte[] cipherTextBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            // CONVERSION of raw bytes to BASE64 representation
            String cipherText = Base64.getEncoder().encodeToString(cipherTextBytes);

            return cipherText;

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidParameterException | InvalidAlgorithmParameterException | InvalidKeySpecException e) {
            return null;
        }
    }

    public static String decrypt(String cipherText, String password) {
        try {
            final byte[] salt = new byte[64];
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            KeySpec passwordBasedEncryptionKeySpec = new PBEKeySpec(password.toCharArray(), salt, 10000, 256);
            SecretKey secretKeyFromPBKDF2 = secretKeyFactory.generateSecret(passwordBasedEncryptionKeySpec);
            SecretKey key = new SecretKeySpec(secretKeyFromPBKDF2.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            final byte[] nonce = new byte[32];
            GCMParameterSpec spec = new GCMParameterSpec(16 * 8, nonce);

            cipher.init(Cipher.DECRYPT_MODE, key, spec);
            byte[] decryptedCipherTextBytes = cipher.doFinal(Base64.getDecoder().decode(cipherText));
            String decryptedCipherText = new String(decryptedCipherTextBytes, StandardCharsets.UTF_8);

            return decryptedCipherText;
        } catch (Exception e) {
            return null;
        }
    }

//    public static void main(String[] args) {
//        String e = encrypt("Text that is going to be sent over an insecure channel and must be encrypted at all costs!","abc123");
//        System.out.println(e);
//        String d = decrypt(e, "abc123");
//        System.out.println(d);
//    }

}
