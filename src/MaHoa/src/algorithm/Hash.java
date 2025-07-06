package algorithm;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class Hash {
    private String algorithm;
//? hash là cái mẹ gì
    //hash this hash that
    //- 1 điểm
    public Hash(String algorithm) {
        this.algorithm = algorithm;
    }

    private String hash(String text) {
        try {
            Security.addProvider(new BouncyCastleProvider());
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] encodedhash = digest.digest(text.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "Error: Algorithm not supported.";
    }

    public String hashForFrame(String input) {
        return hash(input);
    }

    private String hashFile(String filePath) {
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
            e.printStackTrace();
            return null;
        }
    }

    public String hashFileForFrame(String input) {
        return hashFile(input);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

//    public static void main(String[] args) {
//        Hash h = new Hash("GOST3411");
//        String a = "/Users/macbook/Downloads/Rycle/truoc.jpeg";
//        System.out.println(h.hashFileForFrame(a));
//
//    }
}

