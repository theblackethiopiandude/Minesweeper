package UiComponents;

import javax.swing.*;
import java.awt.*;

public abstract class TextPanel extends JPanel {
    public final static Dimension PANEL_SIZE;
    private final static Color BACKGROUND_COLOR;
    private final static Color INNER_PANEL_COLOR;
    private final Dimension INNER_PANEL_SIZE;
    private final  Point INNER_PANEL_LOCATION;
    private final  int INNER_PANEL_ARC;
    private final JLabel COUNTER_LABEL;

    static {
        PANEL_SIZE = new Dimension(113, 55);
        BACKGROUND_COLOR = new Color(0x400E03);
//        BACKGROUND_COLOR = Color.LIGHT_GRAY;
        INNER_PANEL_COLOR = new Color(0x73412F);
    }

    public TextPanel(JLabel text_label, Rectangle text_label_bounds, Dimension inner_panel_size, Point inner_panel_location, int inner_panel_arc, JLabel counter_label, Rectangle counter_label_bounds) {
        INNER_PANEL_SIZE = inner_panel_size;
        INNER_PANEL_LOCATION = inner_panel_location;
        INNER_PANEL_ARC = inner_panel_arc;
        COUNTER_LABEL = counter_label;

        text_label.setForeground(Color.WHITE);
        text_label.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        text_label.setBounds(text_label_bounds);

        COUNTER_LABEL.setForeground(Color.WHITE);
        COUNTER_LABEL.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        COUNTER_LABEL.setBounds(counter_label_bounds);

        this.setLayout(null);

        this.add(text_label);
        this.add(COUNTER_LABEL);
    }


    public JLabel getLabel() {
        return COUNTER_LABEL;
    }

    @Override
    public void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics2D.setPaint(BACKGROUND_COLOR);
        graphics2D.fillRect(0, 0, PANEL_SIZE.width, PANEL_SIZE.height);

        graphics2D.setPaint(INNER_PANEL_COLOR);
        graphics2D.fillRoundRect(INNER_PANEL_LOCATION.x, INNER_PANEL_LOCATION.y, INNER_PANEL_SIZE.width, INNER_PANEL_SIZE.height, INNER_PANEL_ARC, INNER_PANEL_ARC);
    }
}