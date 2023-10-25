package UiComponents;

import GameComponents.GameDifficulty;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TopTile extends JPanel {
    public final static Color LIGHTER_SHADE = new Color(0x73412F);
    public final static Color DARKER_SHADE = new Color(0x592B1B);
    private final static Color LIGHTER_HOVER_SHADE = new Color(0x956757);
    private final static Color DARKER_HOVER_SHADE = new Color(0x98604D);
    private final static HashMap<Color, Color> SHADE_MAP;
    private boolean FLAGGED = false, RELEASED = false;
    private Color SHADE;
    private final Dimension TILE_SIZE;
    private final ImageIcon FLAG_ICON;
    private final Point FLAG_LOCATION;
    private final int ROW, LAST_ROW;
    private final int COLUMN, LAST_COLUMN;
    static {
        SHADE_MAP = new HashMap<>();

        SHADE_MAP.put(DARKER_SHADE, DARKER_HOVER_SHADE);
        SHADE_MAP.put(DARKER_HOVER_SHADE, DARKER_SHADE);

        SHADE_MAP.put(LIGHTER_SHADE, LIGHTER_HOVER_SHADE);
        SHADE_MAP.put(LIGHTER_HOVER_SHADE, LIGHTER_SHADE);
    }

    public TopTile(Color shade, int row, int column, int difficulty) {
        SHADE = shade;
        ROW = row;
        COLUMN = column;

        if (difficulty == GameDifficulty.EASY){
            TILE_SIZE = new Dimension(40, 40);
            FLAG_ICON = new ImageIcon("assets/images/FLAG_EASY.PNG");
            FLAG_LOCATION = new Point(10, 9);
            LAST_ROW = 7;
            LAST_COLUMN = 9;

        } else if (difficulty == GameDifficulty.MEDIUM) {
            TILE_SIZE = new Dimension(25, 25);
            FLAG_ICON = new ImageIcon("assets/images/FLAG_MEDIUM.PNG");
            FLAG_LOCATION = new Point(7, 6);
            LAST_ROW = 13;
            LAST_COLUMN = 17;

        } else if (difficulty == GameDifficulty.HARD) {
            TILE_SIZE = new Dimension(20, 20);
            FLAG_ICON = new ImageIcon("assets/images/FLAG_HARD.PNG");
            FLAG_LOCATION = new Point(6, 4);
            LAST_ROW = 19;
            LAST_COLUMN = 23;

        }else {
            TILE_SIZE = new Dimension();
            FLAG_ICON = new ImageIcon();
            FLAG_LOCATION = new Point();
            LAST_ROW = 0;
            LAST_COLUMN = 0;
        }
        this.setPreferredSize(TILE_SIZE);
    }
    private void revealAdjacent(ArrayList<Point> adjacentLocations, TopTile[][] topTiles, BottomTile [][] bottomTiles){
        for (final var location: adjacentLocations){
            if (topTiles[location.x][location.y].isReleased()) // topTiles[location.x][location.y], bottomTiles[location.x][location.y]),
                continue;

            if (!bottomTiles[location.x][location.y].isBOMB()){
                topTiles[location.x][location.y].add(bottomTiles[location.x][location.y], BorderLayout.CENTER);
                topTiles[location.x][location.y].setReleased(true);
                if (bottomTiles[location.x][location.y].isEmpty()){
                    revealAdjacent(topTiles[location.x][location.y].getAdjacent(), topTiles, bottomTiles);
                }
            }
        }
    }

    public void released(TopTile[][] topTiles, BottomTile [][] bottomTiles){
        this.setReleased(true);
        this.add(bottomTiles[ROW][COLUMN], BorderLayout.CENTER);
        this.revalidate();

        if (bottomTiles[ROW][COLUMN].isEmpty()){
            revealAdjacent(topTiles[ROW][COLUMN].getAdjacent(), topTiles, bottomTiles);
        }
    }

    public boolean isFlagged() {
        return FLAGGED;
    }

    public void setFlagged(boolean FLAGGED) {
        this.FLAGGED = FLAGGED;
        repaint();
    }

    public boolean isReleased() {
        return RELEASED;
    }

    public void setReleased(boolean RELEASED) {
        if (RELEASED){
            if (!FLAGGED){
                this.RELEASED = true;
            }
        }else {
            this.RELEASED = false;
        }
    }

    public int getRow() {
        return ROW;
    }

    public int getColumn() {
        return COLUMN;
    }

    public void refreshShade(){
        SHADE = SHADE_MAP.get(SHADE);
        repaint();
    }
    public ArrayList<Point> getAdjacent(){
        ArrayList<Point> adjacentLocation = new ArrayList<>();
        final int FIRST_ROW = 0;
        final int FIRST_COLUMN = 0;

        final int CURRENT_ROW = ROW;
        int PREVIOUS_ROW = CURRENT_ROW - 1;
        int NEXT_ROW = CURRENT_ROW + 1;

        if (PREVIOUS_ROW < 0 ){
            PREVIOUS_ROW = CURRENT_ROW;
        }else if (NEXT_ROW > LAST_ROW){
            NEXT_ROW = CURRENT_ROW;
        }


        final int CURRENT_COLUMN = COLUMN;
        int PREVIOUS_COLUMN = CURRENT_COLUMN - 1;
        int NEXT_COLUMN = CURRENT_COLUMN + 1;

        if (PREVIOUS_COLUMN < 0 ){
            PREVIOUS_COLUMN = CURRENT_COLUMN;
        }else if (NEXT_COLUMN > LAST_COLUMN){
            NEXT_COLUMN = CURRENT_COLUMN;
        }

        if (CURRENT_ROW == FIRST_ROW && CURRENT_COLUMN == FIRST_COLUMN){ // TOP-LEFT CORNER
            adjacentLocation.add(new Point(CURRENT_ROW, NEXT_COLUMN));
            adjacentLocation.add(new Point(NEXT_ROW, CURRENT_COLUMN));
            adjacentLocation.add(new Point(NEXT_ROW, NEXT_COLUMN));

        } else if (CURRENT_ROW == FIRST_ROW && CURRENT_COLUMN == LAST_COLUMN) { // TOP-RIGHT CORNER
            adjacentLocation.add(new Point(CURRENT_ROW, PREVIOUS_COLUMN));
            adjacentLocation.add(new Point(NEXT_ROW, PREVIOUS_COLUMN));
            adjacentLocation.add(new Point(NEXT_ROW, CURRENT_COLUMN));

        } else if (CURRENT_ROW == LAST_ROW && CURRENT_COLUMN == FIRST_COLUMN) { // BOTTOM-LEFT CORNER
            adjacentLocation.add(new Point(PREVIOUS_ROW, CURRENT_COLUMN));
            adjacentLocation.add(new Point(PREVIOUS_ROW, NEXT_COLUMN));
            adjacentLocation.add(new Point(CURRENT_ROW, NEXT_COLUMN));

        } else if (CURRENT_ROW == LAST_ROW && CURRENT_COLUMN == LAST_COLUMN) { // BOTTOM-RIGHT CORNER
            adjacentLocation.add(new Point(PREVIOUS_ROW, PREVIOUS_COLUMN));
            adjacentLocation.add(new Point(PREVIOUS_ROW, CURRENT_COLUMN));
            adjacentLocation.add(new Point(CURRENT_ROW, PREVIOUS_COLUMN));

        } else if (CURRENT_COLUMN == FIRST_COLUMN) { // LEFT-SIDE
            adjacentLocation.add(new Point(PREVIOUS_ROW, CURRENT_COLUMN));
            adjacentLocation.add(new Point(NEXT_ROW, CURRENT_COLUMN));

            adjacentLocation.add(new Point(PREVIOUS_ROW, NEXT_COLUMN));
            adjacentLocation.add(new Point(CURRENT_ROW, NEXT_COLUMN));
            adjacentLocation.add(new Point(NEXT_ROW, NEXT_COLUMN));

        } else if (CURRENT_COLUMN == LAST_COLUMN) { // RIGHT-SIDE
            adjacentLocation.add(new Point(PREVIOUS_ROW, CURRENT_COLUMN));
            adjacentLocation.add(new Point(NEXT_ROW, CURRENT_COLUMN));

            adjacentLocation.add(new Point(PREVIOUS_ROW, PREVIOUS_COLUMN));
            adjacentLocation.add(new Point(CURRENT_ROW, PREVIOUS_COLUMN));
            adjacentLocation.add(new Point(NEXT_ROW, PREVIOUS_COLUMN));
        }else if (CURRENT_ROW == FIRST_ROW){ // TOP-PART
            adjacentLocation.add(new Point(CURRENT_ROW, PREVIOUS_COLUMN));
            adjacentLocation.add(new Point(CURRENT_ROW, NEXT_COLUMN));

            adjacentLocation.add(new Point(NEXT_ROW, PREVIOUS_COLUMN));
            adjacentLocation.add(new Point(NEXT_ROW, CURRENT_COLUMN));
            adjacentLocation.add(new Point(NEXT_ROW, NEXT_COLUMN));
        } else if (CURRENT_ROW == LAST_ROW) { // BOTTOM-PART
            adjacentLocation.add(new Point(CURRENT_ROW, PREVIOUS_COLUMN));
            adjacentLocation.add(new Point(CURRENT_ROW, NEXT_COLUMN));

            adjacentLocation.add(new Point(PREVIOUS_ROW, PREVIOUS_COLUMN));
            adjacentLocation.add(new Point(PREVIOUS_ROW, CURRENT_COLUMN));
            adjacentLocation.add(new Point(PREVIOUS_ROW, NEXT_COLUMN));
        }else {
            adjacentLocation.add(new Point(PREVIOUS_ROW, PREVIOUS_COLUMN));
            adjacentLocation.add(new Point(PREVIOUS_ROW, CURRENT_COLUMN));
            adjacentLocation.add(new Point(PREVIOUS_ROW, NEXT_COLUMN));

            adjacentLocation.add(new Point(CURRENT_ROW, PREVIOUS_COLUMN));
            adjacentLocation.add(new Point(CURRENT_ROW, NEXT_COLUMN));

            adjacentLocation.add(new Point(NEXT_ROW, PREVIOUS_COLUMN));
            adjacentLocation.add(new Point(NEXT_ROW, CURRENT_COLUMN));
            adjacentLocation.add(new Point(NEXT_ROW, NEXT_COLUMN));

        }
        return adjacentLocation;
    }
    @Override
    public void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics2D.setPaint(SHADE);
        graphics2D.fillRect(0,0, TILE_SIZE.width, TILE_SIZE.height);

        if (FLAGGED){
            graphics2D.drawImage(FLAG_ICON.getImage(), FLAG_LOCATION.x, FLAG_LOCATION.y, null);
        }

    }
}
