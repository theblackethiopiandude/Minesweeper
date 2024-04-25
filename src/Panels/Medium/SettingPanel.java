package Panels.Medium;

import Panels.Screens.GamePanel;
import UiComponents.CloseButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SettingPanel extends JPanel {
    private final JPanel container;
    public SettingPanel(JPanel container){
        this.container = container;
        Dimension SettingPanelSize = new Dimension(219, 442);
        Color BackgroundColor = new Color(0x592B1B);

        ImageIcon closeIcon = new ImageIcon("assets/images/CLOSE.PNG");
        Point closeIconLocation = new Point(203, 61);
        Dimension closeIconSize = new Dimension(10, 10);

        Font TextFont = new Font("Roboto", Font.PLAIN, 12);
        this.setPreferredSize(SettingPanelSize);
        this.setBackground(BackgroundColor);
        this.setLayout(null);

        CloseButton close = new CloseButton();
        JLabel save = new JLabel("SAVE");
        JLabel restart = new JLabel("RESTART");
        JLabel difficulty = new JLabel("DIFFICULTY");
        JLabel exit = new JLabel("EXIT");

        close.setBackground(BackgroundColor);

        save.setFont(TextFont);
        save.setForeground(Color.WHITE);

        restart.setFont(TextFont);
        restart.setForeground(Color.WHITE);

        difficulty.setFont(TextFont);
        difficulty.setForeground(Color.WHITE);

        exit.setFont(TextFont);
        exit.setForeground(Color.WHITE);

        close.setBounds(closeIconLocation.x, closeIconLocation.y, closeIconSize.width, closeIconSize.height);
        save.setBounds(96, 93, 30, 16);
        restart.setBounds(85, 122, 60, 16);
        difficulty.setBounds(77, 151, 75, 16);
        exit.setBounds(98, 180, 30, 16);

        close.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                close();
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
        });

        save.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                save();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mouseExited(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseEntered(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.WHITE);
            }
        });

        restart.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                restart();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mouseExited(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseEntered(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.WHITE);
            }
        });

        difficulty.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                difficulty();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mouseExited(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseEntered(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.WHITE);
            }
        });

        exit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                exit();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mouseExited(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseEntered(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JLabel label = (JLabel) e.getSource();
                label.setForeground(Color.WHITE);
            }
        });

        JLabel name = new JLabel("Habeshan MineSweeper");
        JLabel version = new JLabel("Version 1.0.0");
        name.setFont(new Font(TextFont.getName(), Font.PLAIN, 9));
        version.setFont(new Font(TextFont.getName(), Font.PLAIN, 9));

        name.setForeground(new Color(0xF2B749));
        version.setForeground(new Color(0xF2B749));

        name.setBounds(66, 347, 100, 11);
        version.setBounds(86, 357, 60, 11);

        this.add(close);
        this.add(save);
        this.add(restart);
        this.add(difficulty);
        this.add(exit);

        this.add(name);
        this.add(version);

    }

    private void save(){

    }
    private void restart(){

    }
    private void difficulty(){

    }
    private void exit(){

    }
    private void close() {
        System.out.println("in close");
        var x = this.getX();
        while (x <= GamePanel.SCREEN_SIZE.width){
            try{
                this.setBounds(++x, this.getY(), this.getWidth(), this.getHeight());
                container.revalidate();
                container.repaint();
                Thread.sleep(4);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
