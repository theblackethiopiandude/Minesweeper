package Panels;

import UiComponents.TextPanel;

import javax.swing.*;
import java.awt.*;

public class TimePanel extends JPanel {
    private final static Dimension PANEL_SIZE;
    private final static Dimension INNER_PANEL_SIZE;
    public final static ImageIcon TIME_ICON;
    private final static Point TIME_ICON_LOCATION;
    private final static Point INNER_PANEL_LOCATION;
    private final static Color BACKGROUND_COLOR;
    private final static int ARC;
    private final JLabel FLAG_LABEL;

    static {
        PANEL_SIZE = new Dimension(55, 55);
        BACKGROUND_COLOR = new Color(0x400E03);
        ARC = 10;

        TIME_ICON = new ImageIcon("assets/images/TIME_ICON.PNG");
        TIME_ICON_LOCATION = new Point(3, 5);

        INNER_PANEL_SIZE = new Dimension(50, 20);
        INNER_PANEL_LOCATION = new Point(3, 30);
    }


    public TimePanel(JLabel time_label) {
        this.setBounds(576, 64, 55, 55);
        FLAG_LABEL = time_label;
        FLAG_LABEL.setForeground(Color.WHITE);
        FLAG_LABEL.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        Rectangle TIME_LABEL_BOUNDS = new Rectangle(14, 4, INNER_PANEL_SIZE.width, 14);

        TextPanel textPanel = new TextPanel(FLAG_LABEL, TIME_LABEL_BOUNDS, INNER_PANEL_SIZE, ARC);

        textPanel.add(FLAG_LABEL);
        textPanel.setBounds(INNER_PANEL_LOCATION.x, INNER_PANEL_LOCATION.y, INNER_PANEL_SIZE.width, INNER_PANEL_SIZE.height);

        this.setLayout(null);
        this.add(textPanel);
    }

    public JLabel getTimeLabel() {
        return FLAG_LABEL;
    }

    @Override
    public void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setPaint(BACKGROUND_COLOR);
        graphics2D.fillRect(0, 0, PANEL_SIZE.width, PANEL_SIZE.height);

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(TIME_ICON.getImage(),TIME_ICON_LOCATION.x, TIME_ICON_LOCATION.y, null);

//        graphics2D.setPaint(INNER_PANEL_COLOR);
//        graphics2D.fillRoundRect(INNER_PANEL_LOCATION.x, INNER_PANEL_LOCATION.y, INNER_PANEL_SIZE.width, INNER_PANEL_SIZE.height, ARC, ARC);
    }
}
