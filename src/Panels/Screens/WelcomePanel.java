package Panels.Screens;

import GameComponents.NavigationStack;
import Panels.Medium.DifficultyPanel;
import UiComponents.GameButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WelcomePanel extends JPanel implements MouseListener {
    private final static Image BACKGROUND_IMAGE;
    public static final NavigationStack<JPanel> navigationStack;
    static {
        BACKGROUND_IMAGE = new ImageIcon("assets/images/START_GAME_BACKGROUND.PNG").getImage();
        navigationStack = new NavigationStack<>();
    }
    private static WelcomePanel instance = null;
    public static WelcomePanel getInstance(){
        if (instance == null)
            instance = new WelcomePanel();
        return instance;
    }
    private final GameButton startGameButton, loadGameButton;
    private final JLabel welcomeLabel;
    private final DifficultyPanel difficultyPanel;
    private WelcomePanel(){
        this.setLayout(null);
        this.setBounds(MainPanel.FULL_SCREEN);
        Color welcomeTextColor = new Color(0x000000);
        Font welcomeTextFont = new Font("Roboto", Font.PLAIN, 40);
        Dimension welcomeTextSize = new Dimension(230, 37);

        welcomeLabel = new JLabel("WELCOME!");
        welcomeLabel.setFont(welcomeTextFont);
        welcomeLabel.setForeground(welcomeTextColor);
        welcomeLabel.setPreferredSize(welcomeTextSize);

        welcomeLabel.setBounds(247,117, welcomeTextSize.width, welcomeTextSize.height);

        Font buttonTextFont = new Font("Roboto", Font.BOLD, 12);
        Dimension buttonSize = new Dimension(184, 37);
        int arc = 15;
        Color buttonBackground = new Color(0x400E03);

        startGameButton = new GameButton("Start New Game", buttonTextFont, new Point(49, 11), buttonSize, arc, buttonBackground);
        loadGameButton = new GameButton("Load Save Game", buttonTextFont, new Point(48, 11), buttonSize, arc, buttonBackground);

        startGameButton.addMouseListener(this);
        loadGameButton.addMouseListener(this);

        startGameButton.setBounds(258, 191, buttonSize.width, buttonSize.height);
        loadGameButton.setBounds(258, 238, buttonSize.width, buttonSize.height);

        difficultyPanel = new DifficultyPanel();
        difficultyPanel.setVisible(false);

        this.add(welcomeLabel);
        this.add(difficultyPanel);
        this.add(startGameButton);
        this.add(loadGameButton);
    }
    @Override
    public void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics2D.drawImage(BACKGROUND_IMAGE, 0,0, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == startGameButton){
            welcomeLabel.setBounds(welcomeLabel.getX(), 30, welcomeLabel.getWidth(), welcomeLabel.getHeight());
            navigationStack.push(difficultyPanel);
        } else if (e.getSource() == loadGameButton) {
            
        }
    }
    public void resetWelcomeLabelLocation(){
        welcomeLabel.setBounds(247,117, welcomeLabel.getWidth(), welcomeLabel.getHeight());
    }
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
