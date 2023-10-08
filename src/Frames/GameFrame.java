package Frames;

import GameComponents.GameBoard;
import GameComponents.GameDifficulty;
import Panels.BombsPanel;
import Panels.FlagPanel;
import Panels.MovesPanel;
import Panels.TimePanel;
import UiComponents.FacePanel;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame(){
        this.setIconImage(new ImageIcon("assets/images/MINESWEEPER.PNG").getImage());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        Dimension SCREEN_SIZE = new Dimension(700, 442);
        Color BACKGROUND_COLOR = new Color(0x400E03);

        JPanel gamePanel = new JPanel(null);
        gamePanel.setBackground(BACKGROUND_COLOR);
        gamePanel.setPreferredSize(SCREEN_SIZE);


        FacePanel facePanel = new FacePanel();
        TimePanel timePanel = new TimePanel(new JLabel("000"));
        FlagPanel flagPanel = new FlagPanel(new JLabel("25"));
        MovesPanel movesPanel = new MovesPanel(new JLabel("10"));
        BombsPanel bombsPanel = new BombsPanel(new JLabel("0"));

        GameBoard gameBoard = new GameBoard(GameDifficulty.HARD);

//        timePanel.getLabel().setText("0:32");
        bombsPanel.getLabel().setText(movesPanel.getLabel().getText());
        bombsPanel.getTotalBombsLabel().setText(flagPanel.getLabel().getText());
        facePanel.setCurrentImage(FacePanel.HAPPY_FACE);

        gamePanel.add(facePanel);
        gamePanel.add(timePanel);
        gamePanel.add(flagPanel);
        gamePanel.add(movesPanel);
        gamePanel.add(bombsPanel);

        gamePanel.add(gameBoard);

        this.add(gamePanel);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
