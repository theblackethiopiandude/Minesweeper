package Frames;

import Panels.Screens.MainPanel;


import javax.swing.*;
import java.awt.*;


public class GameFrame extends JFrame{
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
}
