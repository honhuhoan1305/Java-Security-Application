package view;

import algorithm.Symmetric;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.security.NoSuchAlgorithmException;

import DTO.Symmetric.SymmetricManager;

public class SymmetricFrame {
    private JFrame frame;
    private Symmetric symmetric = new Symmetric();
    SymmetricManager symmetricManager = new SymmetricManager();
    private JButton generateKeyButton;
    private JTextArea keyBase64;
    private JTextArea keyBase64Display;
    private GridLayout gridLayout;
    private JTextArea encryptedField;
    private JTextArea decryptedField;
    private JTextArea outputFieldEncrypt;
    private JTextArea outputFieldDecrypt;
    private JTextArea inputFieldEncrypt;
    private JTextArea inputFieldDecrypt;
    private JComboBox<String> modeEnCryptComboBox;
    private JComboBox<String> modeDecryptComboBox;
    private JComboBox<String> paddingEnCryptComboBox;
    private JComboBox<String> paddingDecryptComboBox;
    private JButton buttonEncrypt;
    private JButton buttonDecrypt;
    private JButton chooseFileEncryptButton;
    private JButton chooseFileDecryptButton;
    private JTextArea showFileNameEncrypt;
    private JTextArea showFileNameDecrypt;
    private JButton copyKeyBase64;
    private String encryptedText;
    private String decryptedText;
    private JButton buttonReset;
    private JComboBox<String> keySizeComboBox;
    private JComboBox<String> algorithmComboBox;
    private JTextArea notication;

    // bo viền cho textara
    public void setBorder(JTextArea textArea) {
        textArea.setWrapStyleWord(true);
        Border roundedBorder = new LineBorder(Color.BLACK, 1, true);
        textArea.setBorder(roundedBorder);
    }

    public SymmetricFrame() {
        frame = new JFrame("Symmetric Generator");
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      //  frame.setSize(400, 900);
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
        JPanel panel7 = new JPanel();
        JPanel panel8 = new JPanel();
        JPanel panel10 = new JPanel();
        // 8 hàng 1 cột
        gridLayout = new GridLayout(8, 1);
        frame.setLayout(gridLayout);

        // đưa giao diện ra giữa màn
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        int frameWidth = 700;
        int frameHeight = 800;
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        frame.setBounds(x, y, frameWidth, frameHeight);

        // mỗi hàng sẽ được chia thành 1 2 hoặc 3 cột, tuỳ vào từng trường dữ liệu
        JPanel panel2Center = new JPanel();
        JPanel panel3Left = new JPanel();
        JPanel panel3Right = new JPanel();
        panel3.add(panel3Left);
        panel3.add(panel3Right);
        JPanel panel4Left = new JPanel();
        JPanel panel4Right = new JPanel();
        panel4.add(panel4Left);
        panel4.add(panel4Right);
        JPanel panel5Center = new JPanel();
        JPanel panel5Right = new JPanel();
        panel5.add(panel5Center);
        panel5.add(panel5Right);
        JPanel panel6Left = new JPanel();
        JPanel panel6Right = new JPanel();
        panel6.add(panel6Left);
        panel6.add(panel6Right);
        JPanel panel7Left = new JPanel();
        JPanel panel7Right = new JPanel();
        panel7.add(panel7Left);
        panel7.add(panel7Right);
        JPanel panel8Left = new JPanel();
        JPanel panel8Right = new JPanel();
        panel8.add(panel8Left);
        panel8.add(panel8Right);

        JPanel panel1Left = new JPanel();
        JPanel panel1Center = new JPanel();
        JPanel panel1Right = new JPanel();
        panel1Right.setLayout(new BorderLayout());
        buttonReset = new JButton("Reset");
        panel1Right.add(buttonReset, BorderLayout.EAST);
// reset
        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyBase64.setText("");
                keyBase64Display.setText("");
                encryptedField.setText("");
                decryptedField.setText("");
                outputFieldEncrypt.setText("Enter file name after Encryption!");
                outputFieldDecrypt.setText("Enter file name after Decryption!");
                inputFieldEncrypt.setText("");
                inputFieldDecrypt.setText("");
                showFileNameEncrypt.setText("");
                showFileNameDecrypt.setText("");
                modeEnCryptComboBox.setSelectedItem("ECB");
                modeDecryptComboBox.setSelectedItem("ECB");
                paddingEnCryptComboBox.setSelectedItem("PKCS5PADDING");
                paddingDecryptComboBox.setSelectedItem("PKCS5PADDING");
                algorithmComboBox.setSelectedItem("AES");

            }
        });


        panel1.setLayout(new GridLayout(1, 3));
        panel1.add(panel1Left);
        panel1.add(panel1Center);
        panel1.add(panel1Right);
        panel1.setBorder(new LineBorder(Color.BLUE));
        String[] keySizes = {"128", "192", "256"};
        String[] algorithm = {"AES", "DES", "DESede", "Blowfish", "Twofish", "Serpent", "IDEA"};
        keySizeComboBox = new JComboBox<>(keySizes);
        keySizeComboBox.setPreferredSize(new Dimension(100, 27));
        algorithmComboBox = new JComboBox<>(algorithm);
        algorithmComboBox.setPreferredSize(new Dimension(100, 27));

        // combobox chọn size sẽ tương ứng với thuật toán được chọn,
        // dữ liệu này được load từ đối tượng trong DTO
        algorithmComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
                String[] keySizes = symmetricManager.getKeySizesForAlgorithm(selectedAlgorithm);
                notication.setText(symmetricManager.getNotication(selectedAlgorithm));
                DefaultComboBoxModel<String> keySizeModel = new DefaultComboBoxModel<>(keySizes);
                keySizeComboBox.setModel(keySizeModel);
            }


        });


        panel1Center.add(new JLabel("Key Size: "));
        panel1Center.add(keySizeComboBox);
        panel1Center.add(new JLabel("Algorithm: "));
        panel1Center.add(algorithmComboBox);

        generateKeyButton = new JButton("Create Key");
        panel1Center.add(generateKeyButton);
        panel1Left.setLayout(new BoxLayout(panel1Left, BoxLayout.Y_AXIS));
        notication = new JTextArea(2, 25);
        notication.setText(symmetricManager.getAes().getNotication());
        notication.setEditable(false);
        panel1Left.add(new JLabel("Note: "));
        panel1Left.add(notication);
        panel1Left.setBorder(new EmptyBorder(10, 10, 10, 10));
        setBorder(notication);

        keySizeComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        generateKeyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(panel1);

        // tạo key dựa vào độ dài và thuật toán được chọn
        generateKeyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int keySize = Integer.parseInt((String) keySizeComboBox.getSelectedItem());
                    String selectKeyBase64 = symmetric.generateBase64Key(keySize, (String) algorithmComboBox.getSelectedItem());
                    keyBase64Display.setText(selectKeyBase64);
                } catch (NoSuchAlgorithmException ex) {
                    ex.printStackTrace();
                }
            }
        });
//PANEL 2
        keyBase64Display = new JTextArea(3, 45);
        copyKeyBase64 = new JButton("Copy All");
        panel2Center.setLayout(new BoxLayout(panel2Center, BoxLayout.Y_AXIS));
        panel2.add(panel2Center);
        keyBase64Display.setEnabled(false);
        panel2Center.add(new JLabel("Key: "));
        panel2Center.add(keyBase64Display);
        setBorder(keyBase64Display);
        panel2Center.add(copyKeyBase64);
        frame.add(panel2);

        //PANEL 3
        panel3.setLayout(new GridLayout(1, 2));
        panel3Left.setLayout(new BoxLayout(panel3Left, BoxLayout.Y_AXIS));
        panel3Right.setLayout(new BoxLayout(panel3Right, BoxLayout.Y_AXIS));
        JLabel label4 = new JLabel("ENCRYPT: ");
        panel3Left.add(label4);
        Font boldFont = new Font(label4.getFont().getName(), Font.BOLD, label4.getFont().getSize());
        label4.setFont(boldFont);
        JLabel label5 = new JLabel("DECRYPT: ");
        panel3Right.add(label5);
        label5.setFont(boldFont);
        chooseFileEncryptButton = new JButton("Choose file");
        chooseFileDecryptButton = new JButton("Choose file");
        showFileNameEncrypt = new JTextArea(5, 20);
        showFileNameDecrypt = new JTextArea(5, 20);
        outputFieldEncrypt = new JTextArea(5, 20);
        outputFieldDecrypt = new JTextArea(5, 20);
        outputFieldEncrypt.setText("Enter file name after Encryption!");
        outputFieldDecrypt.setText("Enter file name after Decryption!");
        showFileNameEncrypt.setEnabled(false);
        showFileNameDecrypt.setEnabled(false);
        setBorder(outputFieldEncrypt);
        setBorder(outputFieldDecrypt);
        setBorder(showFileNameEncrypt);
        setBorder(showFileNameDecrypt);
        panel3Left.add(chooseFileEncryptButton);
        panel3Left.add(showFileNameEncrypt);
        panel3Left.add(outputFieldEncrypt);
        panel3Right.add(chooseFileDecryptButton);
        panel3Right.add(showFileNameDecrypt);
        panel3Right.add(outputFieldDecrypt);
        frame.add(panel3);

//PANEL 4
        inputFieldEncrypt = new JTextArea(5, 20);
        inputFieldDecrypt = new JTextArea(5, 20);
        panel4.setLayout(new GridLayout(1, 2));
        inputFieldEncrypt = new JTextArea(5, 20);
        inputFieldDecrypt = new JTextArea(5, 20);
        setBorder(inputFieldEncrypt);
        setBorder(inputFieldDecrypt);
        panel4Left.setLayout(new BoxLayout(panel4Left, BoxLayout.Y_AXIS));
        panel4Right.setLayout(new BoxLayout(panel4Right, BoxLayout.Y_AXIS));
        panel4Left.add(new JLabel("Input Text: "));
        panel4Left.add(inputFieldEncrypt);
        panel4Right.add(new JLabel("Input Text: "));
        panel4Right.add(inputFieldDecrypt);
        frame.add(panel4);

//PANEL 5
        keyBase64 = new JTextArea(4, 45);
        setBorder(keyBase64);
        panel5Center.setLayout(new BoxLayout(panel5Center, BoxLayout.Y_AXIS));
        panel5.add(panel5Center);
        panel5Center.add(new JLabel("Enter Key: "));
        panel5Center.add(keyBase64);
        frame.add(panel5);

        //PANEL 6
        panel6.setLayout(new GridLayout(1, 2));
        panel6Left.add(new JLabel("Select Mode and Padding"));
        panel6Right.add(new JLabel("Select Mode and Padding"));
        String[] mode = {"ECB", "CBC", "CFB", "OFB", "CTR"};

        modeEnCryptComboBox = new JComboBox<>(mode);
        modeEnCryptComboBox.setPreferredSize(new Dimension(300, 27));
        modeDecryptComboBox = new JComboBox<>(mode);
        modeDecryptComboBox.setPreferredSize(new Dimension(300, 27));
        String[] padding = {"PKCS5PADDING", "PKCS7PADDING", "ISO10126PADDING"};
        paddingEnCryptComboBox = new JComboBox<>(padding);
        paddingDecryptComboBox = new JComboBox<>(padding);
        panel6Left.add(modeEnCryptComboBox);
        paddingEnCryptComboBox.setPreferredSize(new Dimension(300, 27));
        panel6Right.add(modeDecryptComboBox);
        paddingDecryptComboBox.setPreferredSize(new Dimension(300, 27));
        //panel6Left.add(new JLabel("Chọn Padding mã hoá"));
        //panel6Right.add(new JLabel("Chọn Padding giải mã"));
        panel6Left.add(paddingEnCryptComboBox);
        panel6Right.add(paddingDecryptComboBox);
        frame.add(panel6);

        //PANEL 7
        panel7.setLayout(new GridLayout(1, 2));
        encryptedField = new JTextArea(5, 20);
        decryptedField = new JTextArea(5, 20);
        setBorder(encryptedField);
        setBorder(decryptedField);
        panel7Left.setLayout(new BoxLayout(panel7Left, BoxLayout.Y_AXIS));
        panel7Right.setLayout(new BoxLayout(panel7Right, BoxLayout.Y_AXIS));
        panel7Left.add(new JLabel("Encrypted Output: "));
        panel7Left.add(encryptedField);
        panel7Right.add(new JLabel("Decrypted Output: "));
        panel7Right.add(decryptedField);
        frame.add(panel7);

// chọn file để mã hoá
        chooseFileEncryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String selectedFile = fileChooser.getSelectedFile().getAbsolutePath();
                    showFileNameEncrypt.setText(selectedFile);
                }
            }
        });

// chọn file để giải mã
        chooseFileDecryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String selectedFile = fileChooser.getSelectedFile().getAbsolutePath();
                    showFileNameDecrypt.setText(selectedFile);
                }
            }
        });

        panel8.setLayout(new GridLayout(1, 2));
        buttonEncrypt = new JButton("Encrypt");
        buttonDecrypt = new JButton("Decrypt");
        panel8Left.add(buttonEncrypt);
        panel8Right.add(buttonDecrypt);
        frame.add(panel8);

// Sự kiện để reset file về null khi có văn bản được truyền vào inputfield
        //(Tức là sẽ chuyển sang mã hoá text
        inputFieldEncrypt.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!inputFieldEncrypt.getText().isEmpty() || !inputFieldDecrypt.getText().isEmpty()) {
                    showFileNameEncrypt.setText("");
                    showFileNameDecrypt.setText("");
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!inputFieldEncrypt.getText().isEmpty() || !inputFieldDecrypt.getText().isEmpty()) {
                    showFileNameEncrypt.setText("");
                    showFileNameDecrypt.setText("");
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        outputFieldEncrypt.addFocusListener(new FocusListener() {
            @Override
            // đưa trỏ chuột vào sẽ xoá văn bản gốc được set

            public void focusGained(FocusEvent e) {
                if (outputFieldEncrypt.getText().equals("Enter file name after Encryption!")) {
                    outputFieldEncrypt.setText("");
                }
            }


            @Override
            // nếu không nhập gì thì set lại văn bản gốc
            public void focusLost(FocusEvent e) {
                if (outputFieldEncrypt.getText().isEmpty()) {
                    outputFieldEncrypt.setText("Enter file name after Encryption!");
                }
            }
        });
        outputFieldDecrypt.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (outputFieldDecrypt.getText().equals("Enter file name after Decryption!")) {
                    outputFieldDecrypt.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (outputFieldDecrypt.getText().isEmpty()) {
                    outputFieldDecrypt.setText("Enter file name after Decryption!");
                }
            }
        });

// MÃ HOÁ
        buttonEncrypt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String inputText = null;
                    String algorithmSelect = (String) algorithmComboBox.getSelectedItem();
                    String encryptKeyBase64 = keyBase64.getText();
                    String mode = (String) modeEnCryptComboBox.getSelectedItem();
                    String padding = (String) paddingEnCryptComboBox.getSelectedItem();
                    int ivLength = symmetricManager.getIvLenth(algorithmSelect);
                    // Kiểm tra nếu có file
                    if (!showFileNameEncrypt.getText().isEmpty()) {
                        inputText = showFileNameEncrypt.getText();
                        String fileEncrypt = new File(inputText).getParent() + "/" + outputFieldEncrypt.getText();
                        encryptedText = symmetric.encryptFile(inputText, fileEncrypt, encryptKeyBase64, algorithmSelect, mode, padding, ivLength);
                    } else {
                        // Nếu không có tên file được chọn, lấy dữ liệu từ inputFieldEncrypt
                        inputText = inputFieldEncrypt.getText();
                        encryptedText = symmetric.encrypt(inputText, encryptKeyBase64, algorithmSelect, mode, padding, ivLength);
                    }
                    // Nếu không có văn bản hay file nào
                    if (inputText.isEmpty()) {
                        encryptedText = "Please enter text or \nselect a file to Encryption!";
                    }
                    encryptedField.setText(encryptedText);
                } catch (Exception ex) {
                    encryptedField.setText("");
                    JOptionPane.showMessageDialog(frame, "Error Encryption: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

//        buttonDecrypt.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    String decrypted = inputFieldDecrypt.getText();
//                    String privateKeyBase64 = privateKeyField.getText();
//                    String mode = (String) decryptComboBox.getSelectedItem();
//                    String decryptedText = rsa.decrypt(decrypted, privateKeyBase64, mode);
//                    decryptedField.setText(decryptedText);
//                } catch (Exception ex) {
//                    decryptedField.setText("");
//                    JOptionPane.showMessageDialog(frame, "Lỗi khi giải mã: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        });


        // GIẢI MÃ
        buttonDecrypt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String inputText = null;
                    String algorithmSelect = (String) algorithmComboBox.getSelectedItem();
                    String decryptKeyBase64 = keyBase64.getText();
                    String mode = (String) modeDecryptComboBox.getSelectedItem();
                    String padding = (String) paddingDecryptComboBox.getSelectedItem();
                    int ivLength = symmetricManager.getIvLenth(algorithmSelect);

                    // Kiểm tra nếu có chọn file (ưu tiên)
                    if (!showFileNameDecrypt.getText().isEmpty()) {
                        inputText = showFileNameDecrypt.getText();
                        String fileDecrypt = new File(inputText).getParent() + "/" + outputFieldDecrypt.getText();
                        decryptedText = symmetric.decryptFile(inputText, fileDecrypt, decryptKeyBase64, algorithmSelect, mode, padding, ivLength);
                    } else {
                        // Nếu không có  file được chọn
                        inputText = inputFieldDecrypt.getText();
                        decryptedText = symmetric.decrypt(inputText, decryptKeyBase64, algorithmSelect, mode, padding, ivLength);
                    }
                    if (inputText.isEmpty()) {
                        decryptedText = "Please enter text or \nselect a file to Decryption!";
                    }
                    decryptedField.setText(decryptedText);
                } catch (Exception ex) {
                    decryptedField.setText("");
                    JOptionPane.showMessageDialog(frame, "Error Decryption: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

// copy key vừa khởi tạo
        copyKeyBase64.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String publicKeyText = keyBase64Display.getText();
                copyToClipboard(publicKeyText);
            }
        });


        //bật để test
        frame.setVisible(true);
    }

    public void setVisible(boolean b) {

    }

    private void copyToClipboard(String text) {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                SymmetricFrame gui = new SymmetricFrame();
//            }
//        });
//    }


}