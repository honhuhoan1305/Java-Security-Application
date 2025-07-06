package DTO.Symmetric;

public class DES {
    private final String name = "DES";
    private final int ivLength = 8;
    private final String[] keySupport = {"64"};
    private final String notication = "DES hỗ trợ độ dài \nkhoá là 64-bit";

    public String getName() {
        return name;
    }

    public int getIvLength() {
        return ivLength;
    }

    public String[] getKeySupport() {
        return keySupport;
    }

    public String getNotication() {
        return notication;
    }
}