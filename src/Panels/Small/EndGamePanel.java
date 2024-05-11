package Panels.Small;

import GameComponents.GameAudio;
import Panels.Screens.GamePanel;
import Panels.Screens.MainPanel;
import Panels.Screens.WelcomePanel;
import UiComponents.GameButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGamePanel extends JPanel implements ActionListener {
    public static int WIN = 1;
    public static int LOSE = 0;

    private final ImageIcon background;
    private final GameButton restart, mainMenu;

    public EndGamePanel(int status, String time, int moves){
        GameButton exit = new GameButton(new ImageIcon("assets/images/EXIT.PNG"), new ImageIcon("assets/images/EXIT_HOVERED.PNG"), new Point(0, 0), new Dimension(31, 31), 31);
        restart = new GameButton(new ImageIcon("assets/images/RESTART.PNG"), new ImageIcon("assets/images/RESTART_HOVERED.PNG"), new Point(0, 0), new Dimension(72, 72), 72);
        mainMenu = new GameButton(new ImageIcon("assets/images/HOME.PNG"), new ImageIcon("assets/images/HOME_HOVERED.PNG"), new Point(0, 0), new Dimension(72, 72), 72);

        this.setLayout(null);
        this.setBounds(111, 87, 478, 266);

        if (status == WIN){
            background = new ImageIcon("assets/images/WIN_BACKGROUND.PNG");
            exit.setBackground(new Color(0x985943));
            restart.setBackground(new Color(0x868053));
            mainMenu.setBackground(new Color(0x868053));
        } else {
            background = new ImageIcon("assets/images/LOSE_BACKGROUND.PNG");
            exit.setBackground(new Color(0x985943));
            restart.setBackground(new Color(0x9D5D43));
            mainMenu.setBackground(new Color(0x9D5D43));
        }

        exit.setBounds(440, 8, 31, 31);
        restart.setBounds(250, 170, 72, 72);
        mainMenu.setBounds(150, 170, 72, 72);

        JLabel timeLabel = new JLabel(time);
        JLabel movesLabel = new JLabel(String.valueOf(moves));
        timeLabel.setForeground(Color.WHITE);
        movesLabel.setForeground(Color.WHITE);

        timeLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        movesLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        timeLabel.setBounds(134, 128, 60, 30);
        movesLabel.setBounds(326, 128, 60, 30);

        exit.addActionListener(this);
        restart.addActionListener(this);
        mainMenu.addActionListener(this);

        this.add(exit);
        this.add(restart);
        this.add(mainMenu);

        this.add(timeLabel);
        this.add(movesLabel);
    }
    @Override
    public void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);


        graphics2D.drawImage(background.getImage(), 0, 0, null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == restart){
            GamePanel.getInstance().stopTimer();
            MainPanel.getInstance().remove(MainPanel.navigationStack.pop());
            MainPanel.getInstance().add(WelcomePanel.getInstance());
            MainPanel.navigationStack.push(WelcomePanel.getInstance());
        } else if (e.getSource() == mainMenu){
            GamePanel.getInstance().stopTimer();
            MainPanel.getInstance().remove(MainPanel.navigationStack.pop());
            MainPanel.getInstance().add(WelcomePanel.getInstance());
            WelcomePanel.navigationStack.pop();
            WelcomePanel.getInstance().resetWelcomeLabelLocation();
            MainPanel.navigationStack.push(WelcomePanel.getInstance());
        }
        this.setVisible(false);
        GamePanel.getInstance().remove(this);
        GamePanel.getInstance().revalidate();
        GamePanel.getInstance().repaint();
        GameAudio.stopAudio();
    }
}
