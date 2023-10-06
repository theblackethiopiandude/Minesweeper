package Panels;

import UiComponents.ImagePanel;

import javax.swing.*;
import java.awt.*;

public class FlagPanel extends ImagePanel {
    private final static Point LOCATION_ON_GAME_PANEL;
    private final static Dimension NUMBER_OF_FLAG_PANEL_SIZE;
    public final static ImageIcon FLAG_ICON;
    private final static Point FLAG_ICON_LOCATION;
    private final static Point NUMBER_OF_FLAG_PANEL_LOCATION;
    private final static int NUMBER_OF_FLAG_PANEL_ARC;
    private final static Rectangle NUMBER_OF_FLAG_LABEL_BOUNDS;

    static {
        LOCATION_ON_GAME_PANEL = new Point(636, 64);

        FLAG_ICON = new ImageIcon("assets/images/FLAG_ICON.PNG");
        FLAG_ICON_LOCATION = new Point(11, 5);

        NUMBER_OF_FLAG_PANEL_SIZE = new Dimension(25, 20);
        NUMBER_OF_FLAG_PANEL_LOCATION = new Point(11, 30);
        NUMBER_OF_FLAG_PANEL_ARC = 10;
        NUMBER_OF_FLAG_LABEL_BOUNDS = new Rectangle(NUMBER_OF_FLAG_PANEL_LOCATION.x + 7, NUMBER_OF_FLAG_PANEL_LOCATION.y + 4, NUMBER_OF_FLAG_PANEL_SIZE.width, 14);
    }
    public FlagPanel(JLabel flag_label) {
        super(FLAG_ICON, FLAG_ICON_LOCATION, NUMBER_OF_FLAG_PANEL_SIZE, NUMBER_OF_FLAG_PANEL_LOCATION, NUMBER_OF_FLAG_PANEL_ARC, flag_label, NUMBER_OF_FLAG_LABEL_BOUNDS);
        this.setBounds(LOCATION_ON_GAME_PANEL.x, LOCATION_ON_GAME_PANEL.y, ImagePanel.PANEL_SIZE.width, ImagePanel.PANEL_SIZE.height);
    }
}
