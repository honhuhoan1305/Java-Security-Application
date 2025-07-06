package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame {
    private JFrame frame;
    private JPanel panel;
    private JButton buttonSymmetric;
    private JButton buttonAsymmetric;
    private JButton buttonHash;
    private JButton buttonDigitalSignature;

    public MainFrame() {
        frame = new JFrame("Ứng dụng Bảo mật");
        //  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        int frameWidth = 700;
        int frameHeight = 150;
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        frame.setBounds(x, y, frameWidth, frameHeight);


        panel = new JPanel();
        frame.add(panel);

        buttonSymmetric = new JButton("Mã hóa đối xứng");
        buttonAsymmetric = new JButton("Mã hóa bất đối xứng");
        buttonHash = new JButton("Mã hóa Hash");
        buttonDigitalSignature = new JButton("Chữ ký điện tử");


        panel.add(buttonSymmetric);
        panel.add(buttonAsymmetric);
        panel.add(buttonHash);
        panel.add(buttonDigitalSignature);

        buttonSymmetric.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SymmetricFrame().setVisible(true);
            }
        });

        buttonAsymmetric.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RSAFrame().setVisible(true);
            }
        });

        buttonHash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HashFrame().setVisible(true);
            }
        });

        buttonDigitalSignature.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DigitalSignatureFrame().setVisible(true);
            }
        });

    }
    public void setVisible(boolean b) {
        frame.setVisible(b);

    }

    public static void main(String[] args) {
        MainFrame a = new MainFrame();
        a.setVisible(true);
    }


}
