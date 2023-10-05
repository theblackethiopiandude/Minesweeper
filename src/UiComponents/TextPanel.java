package UiComponents;

import javax.swing.*;
import java.awt.*;

public class TextPanel extends JPanel {
    private final static Color BACKGROUND_COLOR;
    private final Dimension PANEL_SIZE;
    private final int ARC;

    static {
        BACKGROUND_COLOR = new Color(0x73412F);
    }
    public TextPanel(JLabel label, Rectangle labelBounds, Dimension panel_size, int arc) {
        this.setLayout(null);
        PANEL_SIZE = panel_size;
        ARC = arc;
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        label.setBounds(labelBounds);
    }


    @Override
    public void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setPaint(BACKGROUND_COLOR);
        graphics2D.fillRoundRect(0, 0, PANEL_SIZE.width, PANEL_SIZE.height, ARC, ARC);
    }
}
