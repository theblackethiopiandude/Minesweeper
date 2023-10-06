package UiComponents;

import javax.swing.*;
import java.awt.*;

public abstract class ImagePanel extends JPanel {
    public final static Dimension PANEL_SIZE;
    private final static Color BACKGROUND_COLOR;
    private final static Color INNER_PANEL_COLOR;
    private final Dimension INNER_PANEL_SIZE;
    public final  ImageIcon IMAGE;
    private final  Point IMAGE_LOCATION;
    private final  Point INNER_PANEL_LOCATION;
    private final  int ARC;
    private final JLabel COUNTER_LABEL;

    static {
        PANEL_SIZE = new Dimension(55, 55);
        BACKGROUND_COLOR = new Color(0x400E03);
        INNER_PANEL_COLOR = new Color(0x73412F);
    }

    public ImagePanel(ImageIcon image, Point image_location, Dimension inner_panel_size, Point inner_panel_location, int arc, JLabel label, Rectangle label_bounds) {
        INNER_PANEL_SIZE = inner_panel_size;
        IMAGE = image;
        IMAGE_LOCATION = image_location;
        INNER_PANEL_LOCATION = inner_panel_location;
        ARC = arc;
        COUNTER_LABEL = label;
        COUNTER_LABEL.setForeground(Color.WHITE);
        COUNTER_LABEL.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        COUNTER_LABEL.setBounds(label_bounds);
        this.setLayout(null);
        this.add(COUNTER_LABEL);
    }


    public JLabel getLabel() {
        return COUNTER_LABEL;
    }

    @Override
    public void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setPaint(BACKGROUND_COLOR);
        graphics2D.fillRect(0, 0, PANEL_SIZE.width, PANEL_SIZE.height);

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(IMAGE.getImage(), IMAGE_LOCATION.x, IMAGE_LOCATION.y, null);

        graphics2D.setPaint(INNER_PANEL_COLOR);
        graphics2D.fillRoundRect(INNER_PANEL_LOCATION.x, INNER_PANEL_LOCATION.y, INNER_PANEL_SIZE.width, INNER_PANEL_SIZE.height, ARC, ARC);
    }
}