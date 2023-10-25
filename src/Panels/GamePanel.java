package Panels;

import GameComponents.GameBoard;
import UiComponents.FacePanel;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
    public static Dimension SCREEN_SIZE = new Dimension(700, 442);
    public GamePanel(int gameDifficulty){
        Color BACKGROUND_COLOR = new Color(0x400E03);

        this.setLayout(null);
        this.setBackground(BACKGROUND_COLOR);
        this.setPreferredSize(SCREEN_SIZE);


        FacePanel facePanel = new FacePanel();
        TimePanel timePanel = new TimePanel(new JLabel("000"));
        FlagPanel flagPanel = new FlagPanel(new JLabel("25"));
        MovesPanel movesPanel = new MovesPanel(new JLabel("10"));
        BombsPanel bombsPanel = new BombsPanel(new JLabel("0"));

        GameBoard gameBoard = new GameBoard(gameDifficulty, facePanel, timePanel, flagPanel, movesPanel, bombsPanel);

//        timePanel.getLabel().setText("0:32");
//        bombsPanel.getLabel().setText(movesPanel.getLabel().getText());
        bombsPanel.getTotalBombsLabel().setText(flagPanel.getLabel().getText());
        facePanel.setCurrentImage(FacePanel.HAPPY_FACE);

        this.add(facePanel);
        this.add(timePanel);
        this.add(flagPanel);
        this.add(movesPanel);
        this.add(bombsPanel);
        this.add(gameBoard);
    }
}
