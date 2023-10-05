package Panels;

import UiComponents.TextPanel;

import javax.swing.*;
import java.awt.*;

public class FlagPanel extends JPanel{
    private final static Dimension PANEL_SIZE;
    private final static Dimension INNER_PANEL_SIZE;
    public final static ImageIcon FLAG_ICON;
    private final static Point FLAG_ICON_LOCATION;
    private final static Point INNER_PANEL_LOCATION;
    private final static Color BACKGROUND_COLOR;
    private final static int ARC;
    private final JLabel FLAG_LABEL;

    static {
        PANEL_SIZE = new Dimension(55, 55);
        BACKGROUND_COLOR = new Color(0x400E03);
        ARC = 10;

        FLAG_ICON = new ImageIcon("assets/images/FLAG_ICON.PNG");
        FLAG_ICON_LOCATION = new Point(11, 5);

        INNER_PANEL_SIZE = new Dimension(25, 20);
        INNER_PANEL_LOCATION = new Point(11, 30);
    }


    public FlagPanel(JLabel flag_label) {
        this.setBounds(636, 64, 55, 55);
        FLAG_LABEL = flag_label;
        FLAG_LABEL.setForeground(Color.WHITE);
        FLAG_LABEL.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        Rectangle FLAG_LABEL_BOUNDS = new Rectangle(7, 4, INNER_PANEL_SIZE.width, 14);

        TextPanel textPanel = new TextPanel(FLAG_LABEL, FLAG_LABEL_BOUNDS, INNER_PANEL_SIZE, ARC);

        textPanel.add(FLAG_LABEL);
        textPanel.setBounds(INNER_PANEL_LOCATION.x, INNER_PANEL_LOCATION.y, INNER_PANEL_SIZE.width, INNER_PANEL_SIZE.height);

        this.setLayout(null);
        this.add(textPanel);
    }

    public JLabel getFlagLabel() {
        return FLAG_LABEL;
    }

    @Override
    public void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setPaint(BACKGROUND_COLOR);
        graphics2D.fillRect(0, 0, PANEL_SIZE.width, PANEL_SIZE.height);

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(FLAG_ICON.getImage(), FLAG_ICON_LOCATION.x, FLAG_ICON_LOCATION.y, null);

//        graphics2D.setPaint(INNER_PANEL_COLOR);
//        graphics2D.fillRoundRect(INNER_PANEL_LOCATION.x, INNER_PANEL_LOCATION.y, INNER_PANEL_SIZE.width, INNER_PANEL_SIZE.height, ARC, ARC);
    }
}
