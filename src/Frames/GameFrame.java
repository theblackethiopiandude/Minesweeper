package Frames;

import Panels.FlagPanel;
import Panels.TimePanel;
import UiComponents.FacePanel;

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

        FacePanel facePanel = new FacePanel();
        TimePanel timePanel = new TimePanel(new JLabel("000"));
        FlagPanel flagPanel = new FlagPanel(new JLabel("10132"));
        timePanel.getTimeLabel().setText("Eyosi");
        flagPanel.getFlagLabel().setText("15");


        gamePanel.add(facePanel);
        gamePanel.add(timePanel);
        gamePanel.add(flagPanel);

        this.add(gamePanel);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
