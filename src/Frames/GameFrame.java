package Frames;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        Dimension SCREEN_SIZE = new Dimension(700, 442);
        Color BACKGROUND_COLOR = new Color(0x400E03);

        JPanel gamePanel = new JPanel(null);
        gamePanel.setBackground(BACKGROUND_COLOR);
        gamePanel.setPreferredSize(SCREEN_SIZE);

        this.add(gamePanel);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
