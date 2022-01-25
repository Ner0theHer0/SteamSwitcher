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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Scanner;

public class Encryptor {

    private static String path = "src/main/resources/app/switcher/crypt.bin";

    public static String encrypt(String plainText, String password) {
        try {
            // GENERATE password (not needed if you have a password already)
            if (password == null || password.isEmpty()) {
                return null;
            }

            // GENERATE random salt (needed for PBKDF2)
            Scanner in = new Scanner(new FileReader(path));
            String thisLine = in.nextLine();
            byte[] salt = Base64.getDecoder().decode(thisLine);

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

        } catch (FileNotFoundException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidParameterException | InvalidAlgorithmParameterException | InvalidKeySpecException e) {
            return null;
        }
    }

    public static String decrypt(String cipherText, String password) {
        try {
            Scanner in = new Scanner(new FileReader(path));
            String thisLine = in.nextLine();
            byte[] salt = Base64.getDecoder().decode(thisLine);

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

    public static void createHash(String password) {
        try {

            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[32];
            random.nextBytes(salt);

            FileWriter writer = new FileWriter(path);
            writer.write(Base64.getEncoder().encodeToString(salt));
            writer.write(System.getProperty("line.separator"));

            System.out.println(Base64.getEncoder().encodeToString(salt));

            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            byte[] hash = factory.generateSecret(spec).getEncoded();
            String out = Base64.getEncoder().encodeToString(hash);
            System.out.println(out);
            writer.write(out);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static boolean checkHash(String key) {
        try {
            Scanner in = new Scanner(new FileReader(path));
            String s = in.nextLine();
            byte[] salt = Base64.getDecoder().decode(s);
            String thisLine = in.nextLine();

            KeySpec spec = new PBEKeySpec(key.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            byte[] hash = factory.generateSecret(spec).getEncoded();
            String out = Base64.getEncoder().encodeToString(hash);
            System.out.println();
            return out.equals(thisLine);

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }


    }

//    public static void main(String[] args) {
//        String e = encrypt("Text that is going to be sent over an insecure channel and must be encrypted at all costs!","abc123");
//        System.out.println(e);
//        String d = decrypt(e, "abc123");
//        System.out.println(d);
//        createHash("asdf");
//        System.out.println(checkHash("asd"));
//    }

}
