package UiComponents;

import GameComponents.GameDifficulty;

import javax.swing.*;
import java.awt.*;

public class BottomTile extends JPanel {
    public final static Color LIGHTER_SHADE = new Color(0xBF7B54);
    public final static Color DARKER_SHADE = new Color(0x8C5230);
    private final static Color BOMB_BACKGROUND = new Color(0xA61F12);

    private boolean EMPTY = true, BOMB = false;
    private Color SHADE;
    private final Dimension TILE_SIZE;
    private final JLabel NUMBER_LABEL;
    private final Point LABEL_LOCATION;
    private final ImageIcon BOMB_ICON;
    private final Point BOMB_LOCATION;
    private final int ROW, LAST_ROW;
    private final int COLUMN, LAST_COLUMN;
    private int ADJACENT_BOMB;

    public BottomTile(Color shade, int row, int column, int difficulty) {
        SHADE = shade;
        ROW = row;
        COLUMN = column;
        ADJACENT_BOMB = 0;
        NUMBER_LABEL = new JLabel(String.valueOf(ADJACENT_BOMB));
        NUMBER_LABEL.setForeground(Color.WHITE);

        if (difficulty == GameDifficulty.EASY){
            TILE_SIZE = new Dimension(40, 40);
            NUMBER_LABEL.setFont(new Font("Bahnschrift", Font.PLAIN, 33));
            LABEL_LOCATION = new Point(14, 6);
            BOMB_ICON = new ImageIcon("assets/images/BOMB_EASY.PNG");
            BOMB_LOCATION = new Point(3, 2);
            LAST_ROW = 7;
            LAST_COLUMN = 9;

        } else if (difficulty == GameDifficulty.MEDIUM) {
            TILE_SIZE = new Dimension(25, 25);
            NUMBER_LABEL.setFont(new Font("Bahnschrift", Font.PLAIN, 21));
            LABEL_LOCATION = new Point(9, 4);
            BOMB_ICON = new ImageIcon("assets/images/BOMB_MEDIUM.PNG");
            BOMB_LOCATION = new Point(2, 1);
            LAST_ROW = 13;
            LAST_COLUMN = 17;

        } else if (difficulty == GameDifficulty.HARD) {
            TILE_SIZE = new Dimension(20, 20);
            NUMBER_LABEL.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
            LABEL_LOCATION = new Point(7, 3);
            BOMB_ICON = new ImageIcon("assets/images/BOMB_HARD.PNG");
            BOMB_LOCATION = new Point(1, 1);
            LAST_ROW = 19;
            LAST_COLUMN = 23;

        }else {
            TILE_SIZE = new Dimension();
            LABEL_LOCATION = new Point();
            BOMB_ICON = new ImageIcon();
            BOMB_LOCATION = new Point();
            LAST_ROW = 0;
            LAST_COLUMN = 0;
        }

    }

    public int getAdjacentBomb() {
        return ADJACENT_BOMB;
    }

    public void refreshLabel() {
        if (BOMB)
            return;
        if (EMPTY){
            NUMBER_LABEL.setBounds(LABEL_LOCATION.x, LABEL_LOCATION.y, TILE_SIZE.width, TILE_SIZE.height);
            this.add(NUMBER_LABEL);
        }
        this.EMPTY = false;
        this.BOMB = false;

        NUMBER_LABEL.setText(String.valueOf(++ADJACENT_BOMB));
    }

    public boolean isEmpty() {
        return EMPTY;
    }

    public boolean isBOMB() {
        return BOMB;
    }

    public void setBomb() {
        this.BOMB = true;
        this.EMPTY = false;
        NUMBER_LABEL.setVisible(false);
        SHADE = BOMB_BACKGROUND;
        repaint();
    }
    public void incrementAdjacent(BottomTile[][] bottomTiles){
        final int FIRST_ROW = 0;
        final int FIRST_COLUMN = 0;

        int PREVIOUS_ROW = ROW - 1;
        final int CURRENT_ROW = ROW;
        int NEXT_ROW = ROW + 1;

        if (PREVIOUS_ROW < 0 ){
            PREVIOUS_ROW = CURRENT_ROW;
        }else if (NEXT_ROW > LAST_ROW){
            NEXT_ROW = CURRENT_ROW;
        }


        int PREVIOUS_COLUMN = COLUMN - 1;
        final int CURRENT_COLUMN = COLUMN;
        int NEXT_COLUMN = COLUMN + 1;

        if (PREVIOUS_COLUMN < 0 ){
            PREVIOUS_COLUMN = CURRENT_COLUMN;
        }else if (NEXT_COLUMN > LAST_COLUMN){
            NEXT_COLUMN = CURRENT_COLUMN;
        }

        if (CURRENT_ROW == FIRST_ROW && CURRENT_COLUMN == FIRST_COLUMN){ // TOP-LEFT CORNER
            bottomTiles[CURRENT_ROW][NEXT_COLUMN].refreshLabel();
            bottomTiles[NEXT_ROW][CURRENT_COLUMN].refreshLabel();
            bottomTiles[NEXT_ROW][NEXT_COLUMN].refreshLabel();

        } else if (CURRENT_ROW == FIRST_ROW && CURRENT_COLUMN == LAST_COLUMN) { // TOP-RIGHT CORNER
            bottomTiles[CURRENT_ROW][PREVIOUS_COLUMN].refreshLabel();
            bottomTiles[NEXT_ROW][PREVIOUS_COLUMN].refreshLabel();
            bottomTiles[NEXT_ROW][CURRENT_COLUMN].refreshLabel();

        } else if (CURRENT_ROW == LAST_ROW && CURRENT_COLUMN == FIRST_COLUMN) { // BOTTOM-LEFT CORNER
            bottomTiles[PREVIOUS_ROW][CURRENT_COLUMN].refreshLabel();
            bottomTiles[PREVIOUS_ROW][NEXT_COLUMN].refreshLabel();
            bottomTiles[CURRENT_ROW][NEXT_COLUMN].refreshLabel();

        } else if (CURRENT_ROW == LAST_ROW && CURRENT_COLUMN == LAST_COLUMN) { // BOTTOM-RIGHT CORNER
            bottomTiles[PREVIOUS_ROW][PREVIOUS_COLUMN].refreshLabel();
            bottomTiles[PREVIOUS_ROW][CURRENT_COLUMN].refreshLabel();
            bottomTiles[CURRENT_ROW][PREVIOUS_COLUMN].refreshLabel();

        } else if (CURRENT_COLUMN == FIRST_COLUMN) { // LEFT-SIDE
            bottomTiles[PREVIOUS_ROW][CURRENT_COLUMN].refreshLabel();
            bottomTiles[NEXT_ROW][CURRENT_COLUMN].refreshLabel();

            bottomTiles[PREVIOUS_ROW][NEXT_COLUMN].refreshLabel();
            bottomTiles[CURRENT_ROW][NEXT_COLUMN].refreshLabel();
            bottomTiles[NEXT_ROW][NEXT_COLUMN].refreshLabel();
        } else if (CURRENT_COLUMN == LAST_COLUMN) { // RIGHT-SIDE
            bottomTiles[PREVIOUS_ROW][CURRENT_COLUMN].refreshLabel();
            bottomTiles[NEXT_ROW][CURRENT_COLUMN].refreshLabel();

            bottomTiles[PREVIOUS_ROW][PREVIOUS_COLUMN].refreshLabel();
            bottomTiles[CURRENT_ROW][PREVIOUS_COLUMN].refreshLabel();
            bottomTiles[NEXT_ROW][PREVIOUS_COLUMN].refreshLabel();
        }else if (CURRENT_ROW == FIRST_ROW){ // TOP-PART
            bottomTiles[CURRENT_ROW][PREVIOUS_COLUMN].refreshLabel();
            bottomTiles[CURRENT_ROW][NEXT_COLUMN].refreshLabel();

            bottomTiles[NEXT_ROW][PREVIOUS_COLUMN].refreshLabel();
            bottomTiles[NEXT_ROW][CURRENT_COLUMN].refreshLabel();
            bottomTiles[NEXT_ROW][NEXT_COLUMN].refreshLabel();
        } else if (CURRENT_ROW == LAST_ROW) { // BOTTOM-PART
            bottomTiles[CURRENT_ROW][PREVIOUS_COLUMN].refreshLabel();
            bottomTiles[CURRENT_ROW][NEXT_COLUMN].refreshLabel();

            bottomTiles[PREVIOUS_ROW][PREVIOUS_COLUMN].refreshLabel();
            bottomTiles[PREVIOUS_ROW][CURRENT_COLUMN].refreshLabel();
            bottomTiles[PREVIOUS_ROW][NEXT_COLUMN].refreshLabel();
        }else {
            bottomTiles[PREVIOUS_ROW][PREVIOUS_COLUMN].refreshLabel();
            bottomTiles[PREVIOUS_ROW][CURRENT_COLUMN].refreshLabel();
            bottomTiles[PREVIOUS_ROW][NEXT_COLUMN].refreshLabel();

            bottomTiles[CURRENT_ROW][PREVIOUS_COLUMN].refreshLabel();
            bottomTiles[CURRENT_ROW][NEXT_COLUMN].refreshLabel();

            bottomTiles[NEXT_ROW][PREVIOUS_COLUMN].refreshLabel();
            bottomTiles[NEXT_ROW][CURRENT_COLUMN].refreshLabel();
            bottomTiles[NEXT_ROW][NEXT_COLUMN].refreshLabel();
        }

    }
    @Override
    public void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics2D.setPaint(SHADE);
        graphics2D.fillRect(0,0, TILE_SIZE.width, TILE_SIZE.height);

        if (BOMB){
            graphics2D.drawImage(BOMB_ICON.getImage(), BOMB_LOCATION.x, BOMB_LOCATION.y, null);
        }
//        super.paintComponent(graphics);

    }
}
