package Panels;

import GameComponents.Clock;
import UiComponents.ImagePanel;

import javax.swing.*;
import java.awt.*;


public class TimePanel extends ImagePanel{
    private final static Point LOCATION_ON_GAME_PANEL;
    public final static ImageIcon TIME_ICON;
    private final static Point TIME_ICON_LOCATION;
    private final static Dimension ELAPSED_TIME_PANEL_SIZE;
    private final static int ELAPSED_TIME_PANEL_ARC;
    private final static Point ELAPSED_TIME_PANEL_LOCATION;
    private final static Rectangle ELAPSED_TIME_LABEL_BOUNDS;
    private final Clock clock;

    static {
        LOCATION_ON_GAME_PANEL = new Point(576, 64);

        TIME_ICON = new ImageIcon("assets/images/TIME_ICON.PNG");
        TIME_ICON_LOCATION = new Point(3, 5);

        ELAPSED_TIME_PANEL_ARC = 10;
        ELAPSED_TIME_PANEL_SIZE = new Dimension(50, 20);
        ELAPSED_TIME_PANEL_LOCATION = new Point(3, 30);
        ELAPSED_TIME_LABEL_BOUNDS = new Rectangle(ELAPSED_TIME_PANEL_LOCATION.x + 13, ELAPSED_TIME_PANEL_LOCATION.y + 4, ELAPSED_TIME_PANEL_SIZE.width, 14);
    }

    public TimePanel(JLabel time_label) {
        super(TIME_ICON, TIME_ICON_LOCATION, ELAPSED_TIME_PANEL_SIZE, ELAPSED_TIME_PANEL_LOCATION, ELAPSED_TIME_PANEL_ARC, time_label, ELAPSED_TIME_LABEL_BOUNDS);
        this.setBounds(LOCATION_ON_GAME_PANEL.x, LOCATION_ON_GAME_PANEL.y, ImagePanel.PANEL_SIZE.width, ImagePanel.PANEL_SIZE.height);
        clock = new Clock(time_label);
    }
    public void stopTimer(){
        clock.getTimer().stop();
    }
}
