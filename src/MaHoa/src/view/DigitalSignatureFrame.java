package view;




import algorithm.DigitalSignature;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DigitalSignatureFrame {
    private JFrame frame;
    private JComboBox<String> algorithmComboBox;
    private JTextArea showFileName;
    private JTextArea inputHash;
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

    public DigitalSignatureFrame() {

        // to cửa sổ
        frame = new JFrame("Digital Signature Generator");
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        showFileName = new JTextArea(4, 45);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.add(new JLabel("Select file to check: "));
        chooseFileButton = new JButton("Choose File");
        panel1.add(chooseFileButton);
        panel1.add(showFileName);
        setBorder(showFileName);
        showFileName.setEditable(false);
        frame.add(panel1);


        inputHash = new JTextArea(1, 45);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.add(new JLabel("Enter Hash String: "));
        panel2.add(inputHash);
        setBorder(inputHash);
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
        hashButton = new JButton("Check");
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
        panel3.add(new JLabel("Result: "));
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
                String filePath = showFileName.getText();
                String algorithm = (String) algorithmComboBox.getSelectedItem();
                String hash = inputHash.getText();
                DigitalSignature digitalSignature = new DigitalSignature();// gọi class Hash truyền vào tên thuật toán đc chojn
                if (digitalSignature.check(filePath, algorithm, hash)) {
                    resultArea.setText("Đã xác thực");
                } else {
                    resultArea.setText("Chưa xác thực");
                }
            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Đặt lại
                showFileName.setText("");
                inputHash.setText("");
                resultArea.setText("");
            }
        });
        frame.setVisible(true);

    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                DigitalSignatureFrame gui = new DigitalSignatureFrame();
//            }
//        });
//    }

    public void setVisible(boolean b) {

    }
}
