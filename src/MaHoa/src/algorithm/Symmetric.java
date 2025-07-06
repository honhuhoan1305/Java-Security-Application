package algorithm;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Symmetric {
    public String notication;
    // Tạo key mã hoá từ mã base64
    private SecretKey createSecretKeyFromBase64(String keyBase64, String algorithm) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        byte[] keyBytes = Base64.getDecoder().decode(keyBase64);
        return new SecretKeySpec(keyBytes, algorithm);
    }
    // Tạo keybase64 từ size và thuật toán nhập vào
    public String generateBase64Key(int keySize, String algorithm) throws NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm); // Sử dụng DES hoặc thuật toán mã hóa đối xứng khác tùy chọn
        keyGenerator.init(keySize); // Độ dài khóa tùy chỉnh
        SecretKey key = keyGenerator.generateKey();
        byte[] keyBytes = key.getEncoded();
        // CHuyển mảng byte thành dạng base64
        String keyBase64 = Base64.getEncoder().encodeToString(keyBytes);
        return keyBase64;
    }

    public String encrypt(String plainText, String keyBase64, String algorithm, String mode, String padding, int ivLength) throws Exception {
        // Kiểm tra nếu độ dài key hợp lệ thì tiến hành triển khai thuật toán
        if (checkLengthKey(keyBase64, algorithm)) {
            SecretKey key = createSecretKeyFromBase64(keyBase64, algorithm);
            // Import thư viện
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding);
            if (!mode.equals("ECB")) {
                byte[] iv = new byte[ivLength]; // IV cần có độ dài 8 bytes
                IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
                cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
            } else {
                //  byte[] iv = new byte[8]; // IV cần có độ dài 8 bytes
                // IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
                cipher.init(Cipher.ENCRYPT_MODE, key);
            }
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        }
        return checkError(algorithm);

    }

    public String decrypt(String encryptedText, String keyBase64, String algorithm, String mode, String padding, int ivLength) throws Exception {
        if (checkLengthKey(keyBase64, algorithm)) {
            SecretKey key = createSecretKeyFromBase64(keyBase64, algorithm);
            Security.addProvider(new BouncyCastleProvider());

            Cipher cipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding);
            if (!mode.equals("ECB")) {
                byte[] iv = new byte[ivLength]; // IV cần có độ dài 8 bytes
                IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
                cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
            } else {
                //  byte[] iv = new byte[8]; // IV cần có độ dài 8 bytes
                // IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
                cipher.init(Cipher.DECRYPT_MODE, key);
            }
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes, "UTF-8");
        }         return checkError(algorithm);

    }

    public String encryptFile(String sourceFile, String destFile, String keyBase64, String algorithm, String mode, String padding, int ivLength) throws Exception {
        if (checkLengthKey(keyBase64, algorithm)) {
            SecretKey key = createSecretKeyFromBase64(keyBase64, algorithm);
            Security.addProvider(new BouncyCastleProvider());

            Cipher cipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding);
            if (!mode.equals("ECB")) {
                byte[] iv = new byte[ivLength]; // IV cần có độ dài 8 bytes
                IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
                cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
            } else {
                //  byte[] iv = new byte[8]; // IV cần có độ dài 8 bytes
                // IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
                cipher.init(Cipher.ENCRYPT_MODE, key);
            }
            try (InputStream inputStream = new FileInputStream(sourceFile);
                 OutputStream outputStream = new FileOutputStream(destFile);
                 CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher)) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    cipherOutputStream.write(buffer, 0, bytesRead);
                }
            }
            notication = "Xong! Đã lưu tại \n" + destFile;
            return notication;
        }         return checkError(algorithm);

    }

    public String decryptFile(String sourceFile, String destFile, String keyBase64, String algorithm, String mode, String padding, int ivLength) throws Exception {
        if (checkLengthKey(keyBase64, algorithm)) {
            SecretKey key = createSecretKeyFromBase64(keyBase64, algorithm);
            Security.addProvider(new BouncyCastleProvider());

            Cipher cipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding);
            if (!mode.equals("ECB")) {
                byte[] iv = new byte[ivLength]; // IV cần có độ dài 8 bytes
                IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
                cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
            } else {
                //  byte[] iv = new byte[8]; // IV cần có độ dài 8 bytes
                // IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
                cipher.init(Cipher.DECRYPT_MODE, key);
            }

            try (InputStream inputStream = new FileInputStream(sourceFile);
                 OutputStream outputStream = new FileOutputStream(destFile);
                 CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher)) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = cipherInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
            notication = "Xong! Đã lưu tại \n" + destFile;
            return notication;
        }
        return checkError(algorithm);
    }
    // xử lý để trả về kết quả khi key không hợp lệ
    private String checkError(String algorithm) {
        switch (algorithm) {
            case "DESede":
                notication = "Key cần có độ dài là 112-bit hoặc 168-bit";
                return notication;
            case "Symmetric":
                notication = "Key cần có độ dài là 128-bit hoặc 256-bit";
                return notication;
            case "AES":
                notication = "Key cần có độ dài là 128-bit, 192-bit hoặc 256-bit";
                return notication;
            case "DES":
                notication = "Key cần có độ dài là 64-bit";
                return notication;
            case "Twofish":
                notication = "Key cần có độ dài là 128-bit, 192-bit hoặc 256-bit";
                return notication;
            case "Serpent":
                notication = "Key cần có độ dài là 128-bit, 192-bit hoặc 256-bit";
                return notication;
            case "IDEA":
                notication = "Key cần có độ dài là 128-bit";
                return notication;
            default:
                notication = "Thuật toán không được hỗ trợ hoặc không tồn tại";
                return notication;
        }
    }

    public boolean checkLengthKey(String keyBase64, String algorithm) {

        try {
            Security.addProvider(new BouncyCastleProvider());
            byte[] keyBytes = Base64.getDecoder().decode(keyBase64);
            int keyLength = keyBytes.length * 8; // Đổi độ dài khóa từ byte sang bit
            System.out.println(keyLength);

            switch (algorithm) {
                case "DESede":
                    return keyLength == 192 || keyLength == 128;
                case "Blowfish":
                    return keyLength == 256 || keyLength == 128;
                case "AES":
                    return keyLength == 256 || keyLength == 128 || keyLength == 192;
                case "DES":
                    return keyLength == 64;
                case "Twofish":
                    return keyLength == 256 || keyLength == 128 || keyLength == 192;
                case "Serpent":
                    return keyLength == 256 || keyLength == 128 || keyLength == 192;
                case "IDEA":
                    return keyLength == 128;
                default:
                    notication = "Độ dài khoá không được hỗ trợ!";
                    return false;
            }

            //Kiểm tra độ dài 112-bit hoặc 168-bit của khoá
            /*
            A 3DES key has length 128 or 192 bits. Note that, internally, the algorithm will use only
            112 (respectively 168) bits out of those 128 (respectively 192) bits; however,
            the key itself, as encoded into bytes, stored and exchanged, must have length 16 or 24 bytes.
             */
        } catch (Exception e) {
            // Xảy ra lỗi khi giải mã chuỗi Base64 hoặc xác định độ dài khóa
            notication = "Độ dài khoá không được hỗ trợ!";
            return false;
        }
    }


//    public static void main(String[] args) throws NoSuchAlgorithmException {
//        Symmetric encryption = new Symmetric();
//        String algorithm = "DES"; // Thuật toán mã hóa
//        String keyBase64 = encryption.generateBase64Key(56, algorithm);
//        System.out.println(keyBase64);
//        System.out.println(encryption.checkLengthKey(keyBase64, algorithm));
//        String keyBase641 = "Mbxt+zfQO8s=ádfasdf"; // Chuỗi Base64 hợp lệ làm khóa
//        //  System.out.println(isValidDESKey(keyBase641));
//
//        try {
//            String mode = "ECB"; // Chế độ mã hóa
//            String padding = "PKCS7PADDING"; // Phương thức padding
//            //PKCS5Padding
//            // ISO10126PADDING
//
//
////            String plainText = "Hello, World!r5yreytrtyrtyrtyrtyry";
////            String encryptedText = encryption.encrypt(plainText, keyBase64, algorithm, mode, padding, 16);
////            System.out.println("Encrypted text: " + encryptedText);
////
////            String decryptedText = encryption.decrypt(encryptedText, keyBase64, algorithm, mode, padding, 16);
////            System.out.println("Decrypted text: " + decryptedText);
////
//            String file = "/Users/macbook/Downloads/Rycle/quán net Đồng Nai.jpeg";
//            String out = "/Users/macbook/Downloads/Rycle/111111231.jpeg";
//            String a = "/Users/macbook/Downloads/Rycle/m1iu.jpeg";
//            encryption.encryptFile( file, out, keyBase64, algorithm, mode, padding, 16);
//            encryption.decryptFile( out, a, keyBase64, algorithm, mode, padding, 16);
//            System.out.println(encryption.notication);
//
//            /*
//            Symmetric (Độ dài key 128, 256), iv 8
//
//DESede 112, 168 . iv 8
//
//aes 128, 192, 256  iv 16
//
//des 64. iv 8
//
//Twofish,128, 192, 256, iv 16
//
//Serpent  ,128, 192, 256, iv 16
//
//IDEA 128, iv 8
//             */
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
