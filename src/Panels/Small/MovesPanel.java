package Panels.Small;

import UiComponents.TextPanel;

import javax.swing.*;
import java.awt.*;

public class MovesPanel extends TextPanel{
    private final static Point LOCATION_ON_GAME_PANEL;
    private final static Dimension NUMBER_OF_MOVES_PANEL_SIZE;
    public final static JLabel MOVES_LABEL;
    private final static Rectangle MOVES_LABEL_BOUNDS;
    private final static Point NUMBER_OF_MOVES_PANEL_LOCATION;
    private final static int NUMBER_OF_MOVES_PANEL_ARC;
    private final static Rectangle NUMBER_OF_MOVES_LABEL_BOUNDS;

    static {
        LOCATION_ON_GAME_PANEL = new Point(505, 145);

        MOVES_LABEL = new JLabel("Moves");
        MOVES_LABEL_BOUNDS = new Rectangle(5, 8, TextPanel.PANEL_SIZE.width, 14);

        NUMBER_OF_MOVES_PANEL_SIZE = new Dimension(100, 20);
        NUMBER_OF_MOVES_PANEL_LOCATION = new Point(4, 27);
        NUMBER_OF_MOVES_PANEL_ARC = 10;
        NUMBER_OF_MOVES_LABEL_BOUNDS = new Rectangle(NUMBER_OF_MOVES_PANEL_LOCATION.x + 44, NUMBER_OF_MOVES_PANEL_LOCATION.y + 4, NUMBER_OF_MOVES_PANEL_SIZE.width, 14);
    }
    public MovesPanel(JLabel number_of_moves_label){
        super(MOVES_LABEL, MOVES_LABEL_BOUNDS, NUMBER_OF_MOVES_PANEL_SIZE, NUMBER_OF_MOVES_PANEL_LOCATION, NUMBER_OF_MOVES_PANEL_ARC, number_of_moves_label, NUMBER_OF_MOVES_LABEL_BOUNDS);
        this.setBounds(LOCATION_ON_GAME_PANEL.x, LOCATION_ON_GAME_PANEL.y, TextPanel.PANEL_SIZE.width, TextPanel.PANEL_SIZE.height);
    }
}
