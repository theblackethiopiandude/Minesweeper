package Panels.Screens;

import GameComponents.DifficultyListner;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public MainPanel(){
        this.setLayout(null);
        this.setLayout(new BorderLayout());

        this.add(new StartPanel(), BorderLayout.CENTER);

        DifficultyListner.mainPanel = this;
    }
}
