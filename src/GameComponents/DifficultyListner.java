package GameComponents;

import Panels.GamePanel;
import UiComponents.GameButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DifficultyListner implements MouseListener {
    public static JPanel mainPanel;

    @Override
    public void mouseClicked(MouseEvent e) {
        mainPanel.remove(mainPanel.getComponent(0));
        mainPanel.revalidate();

        mainPanel.add(new GamePanel(((GameButton) e.getSource()).difficulty), BorderLayout.CENTER);
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
