package view;

import algorithm.RSA;
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

public class RSAFrame {
    private JFrame frame;
    private RSA rsa = new RSA();
    private JButton generateKeyButton;
    private JTextArea publicKeyField;
    private JTextArea privateKeyField;
    private JTextArea publicKeyDisplay;
    private JTextArea privateKeyDisplay;
    private GridLayout gridLayout;
    private JTextArea encryptedField;
    private JTextArea decryptedField;
    private JTextArea outputFieldEncrypt;
    private JTextArea outputFieldDecrypt;
    private JTextArea inputFieldEncrypt;
    private JTextArea inputFieldDecrypt;
    private JComboBox<String> encryptComboBox;
    private JComboBox<String> decryptComboBox;
    private JButton buttonEncrypt;
    private JButton buttonDecrypt;
    private JButton chooseFileEncryptButton;
    private JButton chooseFileDecryptButton;
    private JTextArea showFileNameEncrypt;
    private JTextArea showFileNameDecrypt;
    private JButton copyPublicKey;
    private JButton copyPrivateKey;
    private String encryptedText;
    private String decryptedText;
    private JButton buttonReset;
    private JTextArea notication;
private JComboBox<String> keySizeComboBox;
    public void setBorder(JTextArea textArea) {
        textArea.setWrapStyleWord(true);
        Border roundedBorder = new LineBorder(Color.BLACK, 1, true);
        textArea.setBorder(roundedBorder);
    }

    // Giao diện tương tự frame.SymmetricFrame
    public RSAFrame() {
        frame = new JFrame("RSA Generator");
        //   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       // frame.setSize(800, 900);
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
        JPanel panel7 = new JPanel();
        JPanel panel8 = new JPanel();
        JPanel panel10 = new JPanel();
        gridLayout = new GridLayout(8, 1);
        frame.setLayout(gridLayout);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        int frameWidth = 750;
        int frameHeight = 800;
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        frame.setBounds(x, y, frameWidth, frameHeight);

        JPanel panel2Left = new JPanel();
        JPanel panel2Right = new JPanel();
        panel2.add(panel2Left);
        panel2.add(panel2Right);
        JPanel panel3Left = new JPanel();
        JPanel panel3Right = new JPanel();
        panel3.add(panel3Left);
        panel3.add(panel3Right);
        JPanel panel4Left = new JPanel();
        JPanel panel4Right = new JPanel();
        panel4.add(panel4Left);
        panel4.add(panel4Right);
        JPanel panel5Left = new JPanel();
        JPanel panel5Right = new JPanel();
        panel5.add(panel5Left);
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
        panel1Left.add(new JLabel("Note: "));
        notication = new JTextArea(2, 20);
        panel1Left.add(notication);
        notication.setText("Mã hoá file cần\nđộ dài key > 2048-bit");
        panel1Left.setBorder(new EmptyBorder(10, 10, 10, 10));
        setBorder(notication);        panel1Right.setLayout(new BorderLayout());
        buttonReset = new JButton("Reset");
        panel1Right.add(buttonReset, BorderLayout.EAST);

        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                privateKeyField.setText("");
                publicKeyField.setText("");
                encryptedField.setText("");
                decryptedField.setText("");
                outputFieldEncrypt.setText("Enter file name after Encryption!");
                outputFieldDecrypt.setText("Enter file name after Decryption!");
                inputFieldEncrypt.setText("");
                inputFieldDecrypt.setText("");
                showFileNameEncrypt.setText("");
                showFileNameDecrypt.setText("");
                publicKeyDisplay.setText("");
                privateKeyDisplay.setText("");
                keySizeComboBox.setSelectedItem("512");
                encryptComboBox.setSelectedItem("RSA");
                decryptComboBox.setSelectedItem("RSA");
            }
        });


        panel1.setLayout(new GridLayout(1, 3));
        panel1.add(panel1Left);
        panel1.add(panel1Center);
        panel1.add(panel1Right);
        String[] keySizes = {"512", "1024", "2048", "3072", "4096"};
        keySizeComboBox = new JComboBox<>(keySizes);
        panel1Center.add(new JLabel("Key Size: "));
        panel1Center.add(keySizeComboBox);
        generateKeyButton = new JButton("Create Key");
        panel1Center.add(generateKeyButton);
        keySizeComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        generateKeyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(panel1);
        generateKeyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedKeySize = (String) keySizeComboBox.getSelectedItem();
                int keySize = Integer.parseInt(selectedKeySize);
                try {
                    rsa.createKey(keySize);
                    String publicKeyBase64 = rsa.getPublicKeyBase64();
                    String privateKeyBase64 = rsa.getPrivateKeyBase64();
                    publicKeyDisplay.setText(publicKeyBase64);
                    privateKeyDisplay.setText(privateKeyBase64);
                } catch (NoSuchAlgorithmException ex) {
                    ex.printStackTrace();
                }
            }
        });
//PANEL 2
        panel2.setLayout(new GridLayout(1, 2));
        publicKeyDisplay = new JTextArea(5, 20);
        privateKeyDisplay = new JTextArea(5, 20);
        copyPublicKey = new JButton("Copy All");
        copyPrivateKey = new JButton("Copy All");
        panel2Left.setLayout(new BoxLayout(panel2Left, BoxLayout.Y_AXIS));
        panel2Right.setLayout(new BoxLayout(panel2Right, BoxLayout.Y_AXIS));
        publicKeyDisplay.setEnabled(false);
        privateKeyDisplay.setEnabled(false);
        panel2Left.add(new JLabel("Public Key: "));
        panel2Left.add(publicKeyDisplay);
        setBorder(publicKeyDisplay);
        panel2Left.add(copyPublicKey);
        panel2Right.add(new JLabel("Private Key: "));
        panel2Right.add(privateKeyDisplay);
        setBorder(privateKeyDisplay);
        panel2Right.add(copyPrivateKey);
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
        panel5.setLayout(new GridLayout(1, 2));
        publicKeyField = new JTextArea(5, 20);
        privateKeyField = new JTextArea(5, 20);
        setBorder(publicKeyField);
        setBorder(privateKeyField);
        panel5Left.setLayout(new BoxLayout(panel5Left, BoxLayout.Y_AXIS));
        panel5Right.setLayout(new BoxLayout(panel5Right, BoxLayout.Y_AXIS));
        panel5Left.add(new JLabel("Enter Public Key: "));
        panel5Left.add(publicKeyField);
        panel5Right.add(new JLabel("Enter Private Key: "));
        panel5Right.add(privateKeyField);
        frame.add(panel5);

        //PANEL 6
        panel6.setLayout(new GridLayout(1, 2));
        panel6Left.add(new JLabel("Select Mode Encrypt"));
        panel6Right.add(new JLabel("Select Mode Decrypt"));
        String[] encryptionOptions = {"RSA", "RSA/ECB/PKCS1Padding", "RSA/ECB/OAEPWithSHA-1AndMGF1Padding", "RSA/ECB/OAEPWithSHA-256AndMGF1Padding"};
        encryptComboBox = new JComboBox<>(encryptionOptions);
        decryptComboBox = new JComboBox<>(encryptionOptions);
        panel6Left.add(encryptComboBox);
        panel6Right.add(decryptComboBox);
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

        chooseFileEncryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    // Lấy file đã chọn
                    String selectedFile = fileChooser.getSelectedFile().getAbsolutePath();
                    showFileNameEncrypt.setText(selectedFile);
                }
            }
        });

        chooseFileDecryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    // Lấy file đã chọn
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
// Chưa xử lý xong
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
            public void focusGained(FocusEvent e) {
                if (outputFieldEncrypt.getText().equals("Enter file name after Encryption!")) {
                    outputFieldEncrypt.setText("");
                }
            }

            @Override
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


        buttonEncrypt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String inputText = null;

                    if (!showFileNameEncrypt.getText().isEmpty()) {
                        // Đọc nội dung của file đã chọn
                        inputText = showFileNameEncrypt.getText();
                        String fileEncrypt = new File(inputText).getParent() + "/" + outputFieldEncrypt.getText();
                        String mode = (String) encryptComboBox.getSelectedItem();
                        String publicKey = publicKeyField.getText();
                        encryptedText = rsa.encryptFile(inputText, fileEncrypt, publicKey, mode);
                    } else {
                        // Nếu không có tên file được chọn, lấy dữ liệu từ inputFieldEncrypt
                        inputText = inputFieldEncrypt.getText();
                        String publicKeyBase64 = publicKeyField.getText();
                        String mode = (String) encryptComboBox.getSelectedItem();
                        encryptedText = rsa.encrypt(inputText, publicKeyBase64, mode);
                    }

                    // Kiểm tra xem đã có dữ liệu để mã hóa hay không
                    if (inputText.isEmpty()) {
                        encryptedText = "Please enter text or \nselect a file to Encryption!";
                    }

                    encryptedField.setText(encryptedText);
                } catch (Exception ex) {
                    // Xử lý ngoại lệ nếu có lỗi khi mã hóa
                    encryptedField.setText("");
                    JOptionPane.showMessageDialog(frame, "Error Encryption: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        buttonDecrypt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String inputText = null;

                    if (!showFileNameDecrypt.getText().isEmpty()) {
                        inputText = showFileNameDecrypt.getText();
                        String fileDecrypt = new File(inputText).getParent() + "/" + outputFieldDecrypt.getText();
                        String mode = (String) decryptComboBox.getSelectedItem();
                        String privateKey = privateKeyField.getText();
                        decryptedText = rsa.decryptFile(inputText, fileDecrypt, privateKey, mode);
                    } else {
                        inputText = inputFieldDecrypt.getText();
                        String privateKeyBase64 = privateKeyField.getText();
                        String mode = (String) decryptComboBox.getSelectedItem();
                        decryptedText = rsa.decrypt(inputText, privateKeyBase64, mode);
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


        copyPublicKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String publicKeyText = publicKeyDisplay.getText();
                copyToClipboard(publicKeyText);
            }
        });

        copyPrivateKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String privateKeyText = privateKeyDisplay.getText();
                copyToClipboard(privateKeyText);
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
//                RSAFrame gui = new RSAFrame();
//            }
//        });
//    }


}