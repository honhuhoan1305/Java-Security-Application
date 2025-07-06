package view;

import algorithm.Hash;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class HashFrame {
    private JFrame frame;
    private JComboBox<String> algorithmComboBox;
    private JTextArea inputField;
    private JTextArea showFileName;
    private JTextArea resultArea;
    private JButton chooseFileButton;
    private JButton reset;

    private File selectedFile;
    private GridLayout gridLayout;
    private JButton hashButton;

    public void setBorder(JTextArea textArea) {
        textArea.setWrapStyleWord(true);
        Border roundedBorder = new LineBorder(Color.BLACK, 1, true);
        textArea.setBorder(roundedBorder);
    }

    public HashFrame() {

        // to cửa sổ
        frame = new JFrame("Hash Generator");
        //  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

       // frame.setSize(800, 900);
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        gridLayout = new GridLayout(5, 1);
        frame.setLayout(gridLayout);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        int frameWidth = 750;
        int frameHeight = 500;
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        frame.setBounds(x, y, frameWidth, frameHeight);

        inputField = new JTextArea(4, 45);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.add(new JLabel("Input Text: "));
        panel1.add(inputField);
        setBorder(inputField);
        frame.add(panel1);


        showFileName = new JTextArea(1, 45);
        showFileName.setEnabled(false);
        chooseFileButton = new JButton("Choose File");
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.add(new JLabel("Select file to Hash: "));
        panel2.add(chooseFileButton);
        panel2.add(showFileName);
        setBorder(showFileName);
        frame.add(panel2);

        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    showFileName.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        JPanel optionsPanel = new JPanel();
        JPanel panel3Left = new JPanel();
        JPanel panel3Center = new JPanel();
        JPanel panel3Right = new JPanel();
        optionsPanel.setLayout(new GridLayout(1, 3));
        optionsPanel.add(panel3Left);
        optionsPanel.add(panel3Center);
        optionsPanel.add(panel3Right);
        hashButton = new JButton("Hash");
        JLabel algorithmLabel = new JLabel("Select Algorithm:");
        algorithmComboBox = new JComboBox<>(new String[]{"SHA-1", "SHA-256", "SHA-224", "SHA-384", "SHA-512", "SHA3-224", "SHA3-256",
                "SHA3-384", "SHA3-512", "MD5", "MD4", "MD2", "SHAKE128", "SHAKE256", "RIPEMD160", "WHIRLPOOL", "Tiger", "GOST3411",
                "BLAKE2B-160", "BLAKE2B-256", "BLAKE2B-384", "BLAKE2B-512", "BLAKE2S-128", "BLAKE2S-160", "BLAKE2S-224"});
        algorithmLabel.setPreferredSize(new Dimension(160, 27));
        panel3Center.add(algorithmLabel);
        panel3Center.add(algorithmComboBox);
        //panel3Center.add(hashButton);
        // algorithmLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(optionsPanel);


        resultArea = new JTextArea(4, 45);
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        panel3.add(new JLabel("Result Hash: "));
        panel3.add(resultArea);
        setBorder(resultArea);
        frame.add(panel3);

        JPanel resultPanel = new JPanel();
        JPanel panel4Left = new JPanel();
        JPanel panel4Center = new JPanel();
        JPanel panel4Right = new JPanel();
        resultPanel.setLayout(new GridLayout(1, 3));
        resultPanel.add(panel4Center);
        resultPanel.add(panel4Center);
        resultPanel.add(panel4Center);
        hashButton = new JButton("Hash");
        reset = new JButton("Reset");

        panel4Center.add(hashButton);
        panel4Center.add(reset);
        //panel3Center.add(hashButton);
        // algorithmLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(resultPanel);
// thêm các panel vào panel chính
        hashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = inputField.getText();
                String algorithm = (String) algorithmComboBox.getSelectedItem();
                Hash hasher = new Hash(algorithm);// gọi class Hash truyền vào tên thuật toán đc chojn
                if (!showFileName.getText().isEmpty()) {
                    // Hash the selected file
                    String hash = hasher.hashFileForFrame(selectedFile.getAbsolutePath());
                    resultArea.setText(hash);
                } else {
                    String hash = hasher.hashForFrame(text);
                    resultArea.setText(hash);
                }
            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Đặt lại
                inputField.setText("");
                showFileName.setText("");
                resultArea.setText("");
            }
        });

        inputField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!inputField.getText().isEmpty()) {
                    showFileName.setText("");
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!inputField.getText().isEmpty()) {
                    showFileName.setText("");
                }

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }

        });
        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                HashFrame gui = new HashFrame();
//            }
//        });
//    }

    public void setVisible(boolean b) {

    }
}
