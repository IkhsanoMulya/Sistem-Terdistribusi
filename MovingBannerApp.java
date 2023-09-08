import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovingBannerApp {
    private static String inputText = "";

    public static void main(String[] args) {
        JFrame frame = new JFrame("Moving Banner App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel("Masukkan Teks:");
        JTextField textField = new JTextField(20);
        JButton startButton = new JButton("Mulai");
        JButton stopButton = new JButton("Berhenti");
        JLabel bannerLabel = new JLabel();

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputText = textField.getText();
                startBannerAnimation(bannerLabel);
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopBannerAnimation();
            }
        });

        panel.add(label);
        panel.add(textField);
        panel.add(startButton);
        panel.add(stopButton);

        frame.add(panel);
        frame.add(bannerLabel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private static void startBannerAnimation(JLabel bannerLabel) {
        Thread animationThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        for (int i = 0; i <= inputText.length(); i++) {
                            String bannerText = inputText.substring(i) + inputText.substring(0, i);
                            bannerLabel.setText(bannerText);
                            Thread.sleep(1000); // Menghentikan thread selama 200 milidetik
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        animationThread.start();
    }

    private static void stopBannerAnimation() {
        inputText = "";
    }
}