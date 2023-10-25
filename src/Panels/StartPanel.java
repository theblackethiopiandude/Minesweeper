package Panels;

import UiComponents.GameButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartPanel extends JPanel implements MouseListener {
    private final static Image BACKGROUND_IMAGE;
    static {
        BACKGROUND_IMAGE = new ImageIcon("assets/images/START_GAME_BACKGROUND.PNG").getImage();
    }

    private final GameButton startGame, loadGame;
    private final JLabel welcome;
    private final DifficultyPanel difficultyPanel;
    public StartPanel(){
        Dimension SCREEN_SIZE = new Dimension(700, 442);
        this.setPreferredSize(SCREEN_SIZE);
        this.setLayout(null);

        Color welcomeTextColor = new Color(0x000000);
        Font welcomeTextFont = new Font("Roboto", Font.PLAIN, 40);
        Dimension welcomeTextSize = new Dimension(230, 37);

        welcome = new JLabel("WELCOME!");
        welcome.setFont(welcomeTextFont);
        welcome.setForeground(welcomeTextColor);
        welcome.setPreferredSize(welcomeTextSize);

        welcome.setBounds(247,117, welcomeTextSize.width, welcomeTextSize.height);

        Font buttonTextFont = new Font("Roboto", Font.BOLD, 12);
        Dimension buttonSize = new Dimension(184, 37);
        int arc = 15;
        Color buttonBackground = new Color(0x400E03);

        startGame = new GameButton("Start New Game", buttonTextFont, new Point(49, 11), buttonSize, arc, buttonBackground);
        loadGame = new GameButton("Load Save Game", buttonTextFont, new Point(48, 11), buttonSize, arc, buttonBackground);

        startGame.addMouseListener(this);
        loadGame.addMouseListener(this);

        startGame.setBounds(258, 191, buttonSize.width, buttonSize.height);
        loadGame.setBounds(258, 238, buttonSize.width, buttonSize.height);

        difficultyPanel = new DifficultyPanel();
        difficultyPanel.setVisible(false);

        this.add(welcome);
        this.add(difficultyPanel);
        this.add(startGame);
        this.add(loadGame);
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
        if (e.getSource() == startGame){
            welcome.setBounds(welcome.getX(), 30, welcome.getWidth(), welcome.getHeight());
            difficultyPanel.setVisible(true);
        }
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
