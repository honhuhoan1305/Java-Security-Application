package algorithm;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSA {
    private String privateKeyBase64;
    private String publicKeyBase64;
    private String notication;

    public String getPublicKeyBase64() {
        return publicKeyBase64;
    }

    public String getPrivateKeyBase64() {
        return privateKeyBase64;
    }

    public boolean isValidPublicKey(String publicKeyBase64) {
        try {
            byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyBase64);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isValidPrivateKey(String privateKeyBase64) {
        try {
            byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyBase64);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void createKey(int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        byte[] privateKeyBytes = privateKey.getEncoded();
        byte[] publicKeyBytes = publicKey.getEncoded();

        privateKeyBase64 = Base64.getEncoder().encodeToString(privateKeyBytes);
        publicKeyBase64 = Base64.getEncoder().encodeToString(publicKeyBytes);

        System.out.println("Private Key (Base64): " + privateKeyBase64);
        System.out.println("Public Key (Base64): " + publicKeyBase64);
    }

    public String encrypt(String plainText, String publicKeyBase64, String mode) throws Exception {
        if (isValidPublicKey(publicKeyBase64)) {
            byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyBase64);
            PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKeyBytes));

            Cipher encryptCipher = Cipher.getInstance(mode);
            encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

            byte[] encryptedBytes = encryptCipher.doFinal(plainText.getBytes());

            return Base64.getEncoder().encodeToString(encryptedBytes);
        } else {
            notication = "Key không hợp lệ";
            return notication;
        }
    }

    public String decrypt(String encryptedBase64, String privateKeyBase64, String mode) throws Exception {
        if (isValidPrivateKey(privateKeyBase64)) {
            byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyBase64);
            PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));

            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedBase64);

            Cipher decryptCipher = Cipher.getInstance(mode);
            decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);

            byte[] decryptedBytes = decryptCipher.doFinal(encryptedBytes);

            return new String(decryptedBytes);
        } else {
            notication = "Key không hợp lệ";
            return notication;
        }
    }

    public String encryptFile(String inputFilePath, String outputFilePath, String publicKeyBase64, String mode) throws Exception {
        if (isValidPublicKey(publicKeyBase64)) {
            byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyBase64);
            PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKeyBytes));

            Cipher encryptCipher = Cipher.getInstance(mode);
            encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

            try (FileInputStream inputFileStream = new FileInputStream(inputFilePath);
                 FileOutputStream outputFileStream = new FileOutputStream(outputFilePath)) {
                byte[] inputBuffer = new byte[245]; // Độ dài của một khối khi sử dụng khóa RSA 2048 bit
                byte[] encryptedBlock;
                int bytesRead;

                while ((bytesRead = inputFileStream.read(inputBuffer)) != -1) {
                    encryptedBlock = encryptCipher.doFinal(inputBuffer, 0, bytesRead);
                    outputFileStream.write(encryptedBlock);
                }
            }
            notication = "Xong! Đã lưu tại \n" + outputFilePath;
            return notication;
        } else {
            notication = "Key không hợp lệ";
            return notication;
        }
    }

    public String decryptFile(String inputFilePath, String outputFilePath, String privateKeyBase64, String mode) throws Exception {
        if (isValidPrivateKey(privateKeyBase64)) {
            byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyBase64);
            PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));

            Cipher decryptCipher = Cipher.getInstance(mode);
            decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);

            try (FileInputStream inputFileStream = new FileInputStream(inputFilePath);
                 FileOutputStream outputFileStream = new FileOutputStream(outputFilePath)) {
                byte[] inputBuffer = new byte[256]; // Độ dài của một khối đã mã hóa bằng RSA
                byte[] decryptedBlock;
                int bytesRead;

                while ((bytesRead = inputFileStream.read(inputBuffer)) != -1) {
                    decryptedBlock = decryptCipher.doFinal(inputBuffer, 0, bytesRead);
                    outputFileStream.write(decryptedBlock);
                }
            }
            notication = "Xong! Đã lưu tại \n" + outputFilePath;
            return notication;
        } else {
            notication = "Key không hợp lệ";
            return notication;
        }
    }

//    public static void main(String[] args) throws Exception {
//        RSA rsa = new RSA();
//        rsa.createKey(5113);
//
//        String a = "anh em";
//        String b = rsa.encrypt(a, rsa.getPublicKeyBase64(), "RSA");
//        System.out.println(b);
//        System.out.println(rsa.decrypt(b, rsa.getPrivateKeyBase64(), "RSA"));
//
//    }
}
