package Panels.Screens;

import Frames.GameFrame;
import GameComponents.DifficultyListner;
import GameComponents.NavigationStack;
import Panels.Medium.SettingPanel;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private static MainPanel instance = null;
    public static MainPanel getInstance(){
        if (instance == null)
            instance = new MainPanel();
        return instance;
    }
    public static NavigationStack<JPanel> navigationStack = new NavigationStack<>();
    public static Rectangle FULL_SCREEN = new Rectangle(new Point(0,0), GameFrame.SCREEN_SIZE);
    private MainPanel(){
        this.setLayout(null);
        this.setPreferredSize(GameFrame.SCREEN_SIZE);

        var welcomePanel = WelcomePanel.getInstance();
        var settingsPanel = SettingPanel.getInstance();

        navigationStack.push(settingsPanel);
        navigationStack.push(welcomePanel);

        this.add(welcomePanel);
        this.add(settingsPanel, 0);

        DifficultyListner.mainPanel = this;
    }
}
