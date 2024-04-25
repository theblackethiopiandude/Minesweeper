package Panels.Small;

import UiComponents.TextPanel;

import javax.swing.*;
import java.awt.*;

public class BombsPanel extends TextPanel {
    private final static Point LOCATION_ON_GAME_PANEL;
    private final static Dimension NUMBER_OF_BOMBS_PANEL_SIZE, TOTAL_BOMBS_PANEL_SIZE;
    public final static JLabel BOMBS_LABEL;
    private final static Rectangle BOMBS_LABEL_BOUNDS, TOTAL_BOMBS_LABEL_BOUNDS;
    private final static Point NUMBER_OF_BOMBS_PANEL_LOCATION, TOTAL_BOMBS_PANEL_LOCATION;
    private final static int NUMBER_OF_BOMBS_PANEL_ARC;
    private final static Rectangle NUMBER_OF_BOMBS_LABEL_BOUNDS;
    private final JLabel TOTAL_BOMBS_LABEL = new JLabel("25");

    static {
        LOCATION_ON_GAME_PANEL = new Point(505, 203);

        BOMBS_LABEL = new JLabel("Bombs");
        BOMBS_LABEL_BOUNDS = new Rectangle(4, 4, TextPanel.PANEL_SIZE.width, 14);

        NUMBER_OF_BOMBS_PANEL_SIZE = new Dimension(37, 20);
        TOTAL_BOMBS_PANEL_SIZE = NUMBER_OF_BOMBS_PANEL_SIZE;

        NUMBER_OF_BOMBS_PANEL_LOCATION = new Point(4, 23);
        TOTAL_BOMBS_PANEL_LOCATION = new Point(68, 23);

        NUMBER_OF_BOMBS_PANEL_ARC = 10;

        NUMBER_OF_BOMBS_LABEL_BOUNDS = new Rectangle(NUMBER_OF_BOMBS_PANEL_LOCATION.x + 10, NUMBER_OF_BOMBS_PANEL_LOCATION.y + 4, NUMBER_OF_BOMBS_PANEL_SIZE.width, 14);
        TOTAL_BOMBS_LABEL_BOUNDS = new Rectangle(TOTAL_BOMBS_PANEL_LOCATION.x + 10, TOTAL_BOMBS_PANEL_LOCATION.y + 4, TextPanel.PANEL_SIZE.width, 14);

    }
    public BombsPanel(JLabel number_of_bombs_label){
        super(BOMBS_LABEL, BOMBS_LABEL_BOUNDS, NUMBER_OF_BOMBS_PANEL_SIZE, NUMBER_OF_BOMBS_PANEL_LOCATION, NUMBER_OF_BOMBS_PANEL_ARC, number_of_bombs_label, NUMBER_OF_BOMBS_LABEL_BOUNDS);
        this.setBounds(LOCATION_ON_GAME_PANEL.x, LOCATION_ON_GAME_PANEL.y, TextPanel.PANEL_SIZE.width, TextPanel.PANEL_SIZE.height);

        TOTAL_BOMBS_LABEL.setForeground(Color.WHITE);
        TOTAL_BOMBS_LABEL.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        TOTAL_BOMBS_LABEL.setBounds(TOTAL_BOMBS_LABEL_BOUNDS);

        this.add(TOTAL_BOMBS_LABEL);
    }

    public JLabel getTotalBombsLabel() {
        return TOTAL_BOMBS_LABEL;
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        Color TOTAL_BOMBS_PANEL_COLOR = new Color(0x73412F);
        Color SLANT_LINE_COLOR = new Color(0x707070);

        graphics2D.setPaint(TOTAL_BOMBS_PANEL_COLOR);
        graphics2D.fillRoundRect(TOTAL_BOMBS_PANEL_LOCATION.x, TOTAL_BOMBS_PANEL_LOCATION.y, TOTAL_BOMBS_PANEL_SIZE.width, TOTAL_BOMBS_PANEL_SIZE.height, NUMBER_OF_BOMBS_PANEL_ARC, NUMBER_OF_BOMBS_PANEL_ARC);

        graphics2D.setStroke(new BasicStroke(1));
        graphics2D.setPaint(SLANT_LINE_COLOR);
        graphics2D.drawLine(59, 20, 49, 45);
    }
}
