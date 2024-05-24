package Panels;

import Frames.GameFrame;
import Panels.Screens.GamePanel;
import Panels.Screens.MainPanel;
import Panels.Screens.WelcomePanel;
import UiComponents.GameButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SettingPanel extends JPanel implements MouseListener, ActionListener {
    private static SettingPanel instance = null;
    public static SettingPanel getInstance(){
        if (instance == null)
            instance = new SettingPanel();
        return instance;
    }

    private final GameButton closeButton;
    private final JLabel save, restart, difficulty, exit;
    private final Timer timer;

    private boolean easeIn = false;
    private SettingPanel(){
        Color backgroundColor = new Color(0x592B1B);


        this.setBounds(GameFrame.SCREEN_SIZE.width,0, 219, 442);
        this.setBackground(backgroundColor);
        this.setLayout(null);
        this.setVisible(false);

        int EASE_TIME = 2;
        timer = new Timer(EASE_TIME, this);

        closeButton = new GameButton(new ImageIcon("assets/images/CLOSE_HOVERED.PNG"), new ImageIcon("assets/images/CLOSE.PNG"), new Point(6, 6), new Dimension(16, 16), 0);
        closeButton.setBackground(backgroundColor);

        save = new JLabel("SAVE");
        restart = new JLabel("RESTART");
        difficulty = new JLabel("DIFFICULTY");
        exit = new JLabel("EXIT");


        closeButton.setBounds(195, 57, 20, 20);
        save.setBounds(96, 93, 30, 16);
        restart.setBounds(85, 122, 60, 16);
        difficulty.setBounds(77, 151, 75, 16);
        exit.setBounds(98, 180, 30, 16);

        Font TextFont = new Font("Roboto", Font.PLAIN, 12);

        JLabel name = new JLabel("Habeshan MineSweeper");
        JLabel version = new JLabel("Version 1.0.0");

        name.setFont(new Font(TextFont.getName(), Font.PLAIN, 9));
        name.setForeground(new Color(0xF2B749));
        version.setFont(new Font(TextFont.getName(), Font.PLAIN, 9));
        version.setForeground(new Color(0xF2B749));

        name.setBounds(66, 347, 150, 11);
        version.setBounds(86, 357, 100, 11);

        this.add(closeButton);
        this.add(save);
        this.add(restart);
        this.add(difficulty);
        this.add(exit);

        for (final var component : this.getComponents()){
            component.addMouseListener(this);
            if (component instanceof JLabel){
                component.setFont(TextFont);
                component.setForeground(Color.WHITE);
            }
        }
        this.add(name);
        this.add(version);

    }

    public void easeIn(){
        easeIn = true;
        this.setVisible(true);
        timer.start();

    }
    public void easeOut(){
        easeIn = false;
        timer.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        var x = this.getX();
        if (easeIn)
            this.setBounds(--x,0, 219, 442);
        else
            this.setBounds(++x,0, 219, 442);

        if(!easeIn){
            if (x == 509)
                GamePanel.getInstance().enableBombs();
            else if (x == 579)
                GamePanel.getInstance().enableTime();
            else if (x == 668)
                GamePanel.getInstance().enableSettings();
        }

        if (x == 481 || x == 700){
            timer.stop();
            if (!easeIn)
                this.setVisible(false);
            else
                GamePanel.getInstance().disableDynamicComponent();
        }

        MainPanel.getInstance().repaint();

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == closeButton)
            easeOut();
        else if (e.getSource() == save){

        } else if (e.getSource() == restart) {
            easeOut();
            this.setVisible(false);
            GamePanel.getInstance().stopTimer();
            MainPanel.getInstance().remove(MainPanel.navigationStack.pop());
            MainPanel.getInstance().add(WelcomePanel.getInstance());
            WelcomePanel.navigationStack.pop();
            WelcomePanel.getInstance().resetWelcomeLabelLocation();
            MainPanel.navigationStack.push(WelcomePanel.getInstance());
            this.revalidate();
            this.repaint();
        } else if (e.getSource() == difficulty) {
            this.setVisible(false);
            easeOut();
            GamePanel.getInstance().stopTimer();
            MainPanel.getInstance().remove(MainPanel.navigationStack.pop());
            MainPanel.getInstance().add(WelcomePanel.getInstance());
            MainPanel.navigationStack.push(WelcomePanel.getInstance());
        } else if (e.getSource() == exit) {

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
        if (e.getSource() instanceof JLabel)
            ((JLabel) e.getSource()).setForeground(Color.BLACK);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof JLabel)
            ((JLabel) e.getSource()).setForeground(Color.WHITE);
    }
}
