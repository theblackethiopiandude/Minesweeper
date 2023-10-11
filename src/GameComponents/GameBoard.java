package GameComponents;

import UiComponents.BottomTile;
import UiComponents.TopTile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameBoard extends JPanel implements MouseListener {
    protected final Dimension BOARD_SIZE, TILE_SIZE;
    protected final Point BOARD_LOCATION;
    protected final int ROW, COLUMN, NUMBER_OF_BOMB;
    private final Set<Point> BOMB_LOCATION, REAVELD, FLAGGED;
    private final TopTile [][]topTiles;
    private final BottomTile [][]bottomTiles;
//    private final JPanel [][] Container;
    private boolean FIRST_CLICK = true;
    public GameBoard(int difficulty){
        GameDifficulty gameDifficulty = new GameDifficulty(difficulty);
        BOARD_SIZE = gameDifficulty.BOARD_SIZE;
        TILE_SIZE = gameDifficulty.TILE_SIZE;
        BOARD_LOCATION = gameDifficulty.BOARD_LOCATION;
        ROW = gameDifficulty.ROW;
        COLUMN = gameDifficulty.COLUMN;
        NUMBER_OF_BOMB = gameDifficulty.NUMBER_OF_BOMB;

        this.setLayout(new GridLayout(ROW, COLUMN));
        this.setBounds(BOARD_LOCATION.x, BOARD_LOCATION.y, BOARD_SIZE.width, BOARD_SIZE.height);

        BOMB_LOCATION = new HashSet<>();
        REAVELD = new HashSet<>();
        FLAGGED = new HashSet<>();

        topTiles = new TopTile[ROW][COLUMN];
        bottomTiles = new BottomTile[ROW][COLUMN];

//        Container = new JPanel[ROW][COLUMN];
//
//        for (int row = 0; row < ROW; row++) {
//            for (int column = 0; column < COLUMN; column++) {
//                Container[row][column] = new JPanel(new BorderLayout());
//                this.add(Container[row][column]);
//            }
//        }

        for (int row = 0; row < ROW; row++){
            for (int column = 0; column < COLUMN; column++){
                BottomTile tile;
                if (row % 2 == 0){
                    if (column % 2 == 0){
                        tile = new BottomTile(BottomTile.LIGHTER_SHADE, row, column, difficulty);
                    }else{
                        tile = new BottomTile(BottomTile.DARKER_SHADE, row, column, difficulty);
                    }
                }else {
                    if (column % 2 == 0){
                        tile = new BottomTile(BottomTile.DARKER_SHADE, row, column, difficulty);
                    }else{
                        tile = new BottomTile(BottomTile.LIGHTER_SHADE, row, column, difficulty);
                    }
                }
//                tile.setLayout(new BorderLayout());
                bottomTiles[row][column] = tile;
//                this.add(tile);
            }
        }
        for (int row = 0; row < ROW; row++){
            for (int column = 0; column < COLUMN; column++){
                TopTile tile;
                if (row % 2 == 0){
                    if (column % 2 == 0){
                        tile = new TopTile(TopTile.LIGHTER_SHADE, row, column, difficulty);
                    }else{
                        tile = new TopTile(TopTile.DARKER_SHADE, row, column, difficulty);
                    }
                }else {
                    if (column % 2 == 0){
                        tile = new TopTile(TopTile.DARKER_SHADE, row, column, difficulty);
                    }else{
                        tile = new TopTile(TopTile.LIGHTER_SHADE, row, column, difficulty);
                    }
                }
                topTiles[row][column] = tile;
                tile.setLayout(new BorderLayout());
                tile.addMouseListener(this);
//                bottomTiles[row][column].add(tile, BorderLayout.CENTER);
                this.add(tile);
//                Container[row][column].add(tile);
            }
        }

//    generateBomb();
    }
    private Point[] getAdjacent(Point location){ // gets adjacent location to make the first click safe
        ArrayList<Point> adjacentLocation = new ArrayList<>();
        final int FIRST_ROW = 0;
        final int LAST_ROW = ROW;
        final int FIRST_COLUMN = 0;
        final int LAST_COLUMN = COLUMN;

        final int CURRENT_ROW = location.x;
        int PREVIOUS_ROW = CURRENT_ROW - 1;
        int NEXT_ROW = CURRENT_ROW + 1;

        if (PREVIOUS_ROW < 0 ){
            PREVIOUS_ROW = CURRENT_ROW;
        }else if (NEXT_ROW > LAST_ROW){
            NEXT_ROW = CURRENT_ROW;
        }


        final int CURRENT_COLUMN = location.y;
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
        adjacentLocation.add(new Point(CURRENT_ROW, CURRENT_COLUMN));
        return adjacentLocation.toArray(new Point[adjacentLocation.size()]);
    }
    private void generateBomb(Point[] nonBombLocation){
        for (int i = 0; i<NUMBER_OF_BOMB; i++){
            int row = new Random().nextInt(ROW);
            int column = new Random().nextInt(COLUMN);
            if (!BOMB_LOCATION.contains(new Point(row, column)))
                BOMB_LOCATION.add(new Point(row, column));
            else
                --i;
        }
        for (int i = 0; i < nonBombLocation.length; i++){
            if (BOMB_LOCATION.contains(nonBombLocation[i])){
                BOMB_LOCATION.remove(nonBombLocation[i]);
                BOMB_LOCATION.add(new Point(new Random().nextInt(ROW), new Random().nextInt(COLUMN)));
                --i;
            }
        }

        placeBomb();
    }
    private void placeBomb(){
        for (final var location: BOMB_LOCATION){
            bottomTiles[location.x][location.y].setBomb();
            bottomTiles[location.x][location.y].incrementAdjacent(bottomTiles);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (int row = 0; row < ROW; row++){
            for (int column = 0; column < COLUMN; column++){
                if (e.getSource() == topTiles[row][column]){
                    if (e.getButton() == MouseEvent.BUTTON1){
                        if (!topTiles[row][column].isFlagged()){
                            if (FIRST_CLICK){
                                FIRST_CLICK = false;
                                System.out.println("First Click Done");
                                generateBomb(getAdjacent(new Point(row, column)));
                            }
//                            topTiles[row][column].setVisible(false);
//                            Container[row][column].add(bottomTiles[row][column]);
                            topTiles[row][column].released(topTiles, bottomTiles);
                            REAVELD.add(new Point(row, column));
                        }
                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                        if (!topTiles[row][column].isFlagged()){
                            if (FLAGGED.size() >= NUMBER_OF_BOMB){
                                break;
                            }
                            topTiles[row][column].setFlagged(true);
                            FLAGGED.add(new Point(row, column));
                        }else {
                            topTiles[row][column].setFlagged(false);
                            FLAGGED.remove(new Point(row, column));
                        }
                    }
                    break;
                }
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        for (int row = 0; row < ROW; row++){
            for (int column = 0; column < COLUMN; column++){
                if (e.getSource() == topTiles[row][column]){
                    topTiles[row][column].refreshShade();
                    break;
                }
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        for (int row = 0; row < ROW; row++){
            for (int column = 0; column < COLUMN; column++){
                if (e.getSource() == topTiles[row][column]){
                    topTiles[row][column].refreshShade();
                    break;
                }
            }
        }
    }
}
