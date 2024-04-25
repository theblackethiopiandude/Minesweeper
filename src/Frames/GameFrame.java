package Frames;

import Panels.Screens.MainPanel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameFrame extends JFrame implements MouseListener {
    public GameFrame(){
        this.setIconImage(new ImageIcon("assets/images/MINESWEEPER.PNG").getImage());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);


//        this.add(new GamePanel(1), BorderLayout.CENTER);
        this.add(new MainPanel(), BorderLayout.CENTER);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);

//        DifficultyListner.mainFrame = this;
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
