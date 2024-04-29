package GameComponents;

import Panels.Screens.GamePanel;
import Panels.Screens.MainPanel;
import UiComponents.GameButton;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DifficultyListner implements MouseListener {
    public static JPanel mainPanel;

    @Override
    public void mouseClicked(MouseEvent e) {
        mainPanel.remove(MainPanel.navigationStack.pop());
        mainPanel.revalidate();
        mainPanel.repaint();
        var gamePanel = GamePanel.getInstance();
        gamePanel.setDifficulty(((GameButton) e.getSource()).getDifficulty());

        gamePanel.setBounds(MainPanel.FULL_SCREEN);
        mainPanel.add(gamePanel, 1);
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
