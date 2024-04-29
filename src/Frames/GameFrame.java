package Frames;

import Panels.Screens.MainPanel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameFrame extends JFrame implements MouseListener {
    public static Dimension SCREEN_SIZE = new Dimension(700, 442);
    public GameFrame(){
        this.setIconImage(new ImageIcon("assets/images/MINESWEEPER.PNG").getImage());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);


        this.add(MainPanel.getInstance(), BorderLayout.CENTER);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
