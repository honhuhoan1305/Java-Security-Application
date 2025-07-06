package DTO.Symmetric;

public class DESede {
    private final String name = "DESede";
    private final int ivLength = 8;
    private final String[] keySupport = {"112", "168"};
    private final String notication = "DESede hỗ trợ độ dài khoá \nlà 112-bit và 168-bit";

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
