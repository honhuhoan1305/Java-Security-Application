package algorithm;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.Security;

public class DigitalSignature {

    private String hashFile(String filePath, String algorithm) {
        try {
            Security.addProvider(new BouncyCastleProvider());
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            FileInputStream fileInputStream = new FileInputStream(filePath);
            DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, digest);
            byte[] buffer = new byte[4096];
            while (digestInputStream.read(buffer) != -1) {
                // DigestInputStream automatically updates the digest
            }

            byte[] hashedBytes = digest.digest();

            return bytesToHex(hashedBytes);
        } catch (Exception e) {
            return "Lỗi khi load file!";
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    public boolean check(String filePath, String algorithm, String hash) {
        return hash.equals(hashFile(filePath, algorithm));
    }

//    public static void main(String[] args) {
//        DigitalSignature d = new DigitalSignature();
//        String a = "/Users/macbook/Downloads/Rycle/trắng.jpeg";
//        System.out.println(d.hashFile(a, "MD5"));
//    }
}
