package UiComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CloseButton extends JPanel implements MouseListener {
    Image closeIcon;
    public CloseButton(){
        this.setPreferredSize(new Dimension(10, 10));
        this.addMouseListener(this);
        closeIcon = new ImageIcon("assets/images/CLOSE.PNG").getImage();
    }
        @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Point closeIconLocation = new Point(1, 1);

        graphics2D.drawImage(closeIcon, closeIconLocation.x, closeIconLocation.y, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
        closeIcon = new ImageIcon("assets/images/CLOSE_HOVERD.PNG").getImage();
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        closeIcon = new ImageIcon("assets/images/CLOSE.PNG").getImage();
        repaint();
    }
}
