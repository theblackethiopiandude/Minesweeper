package Panels.Screens;

import GameComponents.GameBoard;
import Panels.Medium.SettingPanel;
import Panels.Small.BombsPanel;
import Panels.Small.FlagPanel;
import Panels.Small.MovesPanel;
import Panels.Small.TimePanel;
import Panels.Small.FacePanel;
import UiComponents.GameButton;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
    public static Dimension SCREEN_SIZE = new Dimension(700, 442);
    private static GamePanel instance = null;
    public static GamePanel getInstance(){
        if (instance == null)
            instance = new GamePanel();
        return instance;
    }
    private final GameButton settingsButton;
    private final FacePanel facePanel;
    private final TimePanel timePanel;
    private final FlagPanel flagPanel;
    private final MovesPanel movesPanel;
    private final BombsPanel bombsPanel;
    private GameBoard gameBoard;
    private GamePanel(){
        Color BACKGROUND_COLOR = new Color(0x400E03);

        this.setLayout(null);
        this.setBackground(BACKGROUND_COLOR);
        this.setPreferredSize(SCREEN_SIZE);

        settingsButton = new GameButton(new ImageIcon("assets/images/SETTINGS.png"), new ImageIcon("assets/images/SETTINGS.png"), new Point(3,3), new Dimension(27, 27), 15);
        facePanel = new FacePanel();
        timePanel = new TimePanel(new JLabel("0:00"));
        flagPanel = new FlagPanel(new JLabel("25"));
        movesPanel = new MovesPanel(new JLabel("10"));
        bombsPanel = new BombsPanel(new JLabel("0"));

        settingsButton.setBounds(668,10,27, 27);
        settingsButton.setBackground(BACKGROUND_COLOR);
        settingsButton.addActionListener(e -> SettingPanel.getInstance().easeIn());

        this.add(settingsButton);
        this.add(facePanel);
        this.add(timePanel);
        this.add(flagPanel);
        this.add(movesPanel);
        this.add(bombsPanel);

    }
    public void disableDynamicComponent(){
        settingsButton.setVisible(false);
        timePanel.getLabel().setVisible(false);
        bombsPanel.getLabel().setVisible(false);
    }
    public void enableSettings(){
        settingsButton.setVisible(true);
    }
    public void enableTime(){
        timePanel.getLabel().setVisible(true);
    }
    public void enableBombs(){
        bombsPanel.getLabel().setVisible(true);
    }
    public void setDifficulty(int gameDifficulty){
        if (gameBoard != null){
            this.remove(gameBoard);
        }
        gameBoard = new GameBoard(gameDifficulty, facePanel, timePanel, flagPanel, movesPanel, bombsPanel);
        this.add(gameBoard);
        this.revalidate();
        this.repaint();
    }
    public void stopTimer(){
        timePanel.stopTimer();
        timePanel.getLabel().setText("0:00");
    }
}
