package DTO.Symmetric;

public class IDEA {
    private final String name = "IDEA";
    private final int ivLength = 8;
    private final String[] keySupport = {"128"};
    private final String notication = "IDEA hỗ trợ độ dài \nkhoá là 128-bit";

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
