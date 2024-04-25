package Panels.Medium;

import GameComponents.DifficultyListner;
import GameComponents.GameDifficulty;
import UiComponents.GameButton;

import javax.swing.*;
import java.awt.*;

public class DifficultyPanel extends JPanel{
    private final static Color BACKGROUND_COLOR;
    private final static int BORDER_RADIUS;
    private final static Dimension PANEL_SIZE;
    static {
        BACKGROUND_COLOR = new Color(0x592B1B);
        PANEL_SIZE = new Dimension(310, 246);
        BORDER_RADIUS = 30;
    }
    public DifficultyPanel(){
        this.setOpaque(false);
        this.setBackground(BACKGROUND_COLOR);
        this.setPreferredSize(PANEL_SIZE);
        this.setLayout(null);
        this.setBounds(195, 97, PANEL_SIZE.width, PANEL_SIZE.height);

        Font buttonTextFont = new Font("Roboto", Font.BOLD, 12);
        Dimension buttonSize = new Dimension(172, 32);
        int arc = 15;
        GameButton easy = new GameButton("Beginner", buttonTextFont, new Point(61, 8), buttonSize, arc, GameDifficulty.EASY);
        GameButton medium = new GameButton("Intermediate", buttonTextFont, new Point(50, 8), buttonSize, arc, GameDifficulty.MEDIUM);
        GameButton hard = new GameButton("Expert", buttonTextFont, new Point(68, 8), buttonSize, arc, GameDifficulty.HARD);

        easy.setBounds(69, 40, buttonSize.width, buttonSize.height);
        medium.setBounds(69, 105, buttonSize.width, buttonSize.height);
        hard.setBounds(69, 170, buttonSize.width, buttonSize.height);

        easy.addMouseListener(new DifficultyListner());
        medium.addMouseListener(new DifficultyListner());
        hard.addMouseListener(new DifficultyListner());

        this.add(easy);
        this.add(medium);
        this.add(hard);
    }

    public void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setPaint(this.getBackground());
        graphics2D.fillRoundRect(0,0, this.getWidth(), this.getHeight(), BORDER_RADIUS, BORDER_RADIUS);

    }
}
