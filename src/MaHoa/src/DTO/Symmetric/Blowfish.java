package DTO.Symmetric;

public class Blowfish {
    private final String name = "Blowfish";
    private final int ivLength = 8;
    private final String[] keySupport = {"128", "256"};
    private final String notication = "Blowfish hỗ trợ độ dài khoá \nlà 128-bit và 256-bit";

    public String getName() {
        return name;
    }

    public int getIvLength() {
        return ivLength;
    }

    public String[] KeySupport() {
        return keySupport;
    }

    public String getNotication() {
        return notication;
    }
}
