package DTO.Symmetric;

public class SymmetricManager {
    private AES aes = new AES();
    private DES des = new DES();
    private DESede desede = new DESede();
    private Blowfish blowfish = new Blowfish();
    private Twofish twofish = new Twofish();
    private Serpent serpent = new Serpent();
    private IDEA idea = new IDEA();

    // Hàm này có chức năng tương tự như 1 lớp cha,
    // dùng để truy xuất dữ liệu của từng lớp con (tương ứng mỗi thuật toán)
    public AES getAes() {
        return aes;
    }

    public DES getDes() {
        return des;
    }

    public DESede getDesede() {
        return desede;
    }

    public Blowfish getBlowfish() {
        return blowfish;
    }

    public Twofish getTwofish() {
        return twofish;
    }

    public Serpent getSerpent() {
        return serpent;
    }

    public IDEA getIdea() {
        return idea;
    }


    public String getNotication(String selectedAlgorithm) {
        switch (selectedAlgorithm) {
            case "Blowfish":
                return getBlowfish().getNotication();
            case "DESede":
                return getDesede().getNotication();
            case "AES":
                return getAes().getNotication();
            case "DES":
                return getDes().getNotication();
            case "Twofish":
                return getTwofish().getNotication();
            case "Serpent":
                return getSerpent().getNotication();
            case "IDEA":
                return getIdea().getNotication();
            default:
                return "";
        }
    }


    public String[] getKeySizesForAlgorithm(String algorithm) {
        switch (algorithm) {
            case "Blowfish":
                return getBlowfish().KeySupport();
            case "DESede":
                return getDesede().getKeySupport();
            case "AES":
                return getAes().getKeySupport();
            case "DES":
                return getDes().getKeySupport();
            case "Twofish":
                return getTwofish().getKeySupport();
            case "Serpent":
                return getSerpent().getKeySupport();
            case "IDEA":
                return getIdea().getKeySupport();
            default:
                return new String[]{};
        }
    }
    public int getIvLenth(String algorithm) {
        switch (algorithm) {
            case "Blowfish":
                return getBlowfish().getIvLength();
            case "DESede":
                return getDesede().getIvLength();
            case "AES":
                return getAes().getIvLength();
            case "DES":
                return getDes().getIvLength();
            case "Twofish":
                return getTwofish().getIvLength();
            case "Serpent":
                return getSerpent().getIvLength();
            case "IDEA":
                return getIdea().getIvLength();
            default:
                return 0;
        }
    }

}
/*Blowfish (Độ dài key 128, 256), iv 8
        String[] keySizes = {"128", "256"};

DESede 112, 168 . iv 8
        String[] keySizes = {"112", "168"};

aes 128, 192, 256  iv 16
        String[] keySizes = {"128", "192", "256"};

des 64. iv 8
        String[] keySizes = {"64"};

Twofish,128, 192, 256, iv 16
        String[] keySizes = {"128", "192", "256"};

Serpent  ,128, 192, 256, iv 16
        String[] keySizes = {"128", "192", "256"};

IDEA 128, iv 8
        String[] keySizes = {"128"};
*/
