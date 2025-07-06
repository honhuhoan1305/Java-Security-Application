package DTO.Symmetric;

// trong package DTO, mooxi class sẽ tương ứng với thoong tin của 1 thuật
// toán. Trong đó chứa các thông tin cần thiết để  Symmetric hoạt động
public class AES {
    // dữ liệu ở đây sẽ được sử dụng trong giao diện, giao diện sau khi load
    // dữ liệu lên sẽ truyền cho lớp Symmetric để thực thi
    private final String name = "AES";
    private final int ivLength = 16;
    private final String[] keySupport = {"128", "192", "256"};
    private  final String notication = "AES hỗ trợ độ dài khoá \nlà 128-bit, 192-bit và 256-bit";

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
