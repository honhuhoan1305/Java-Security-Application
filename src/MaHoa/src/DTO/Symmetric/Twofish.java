package DTO.Symmetric;

public class Twofish {
    private final String name = "Twofish";
    private final int ivLength = 16;
    private final String[] keySupport = {"128", "192", "256"};
    private final String notication = "Twofish hỗ trợ độ dài khoá \nlà 128-bit, 192-bit và 256-bit";

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