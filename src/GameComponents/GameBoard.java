package GameComponents;

import UiComponents.BottomTile;
import UiComponents.TopTile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
                bottomTiles[row][column] = tile;
                this.add(tile);
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
                tile.addMouseListener(this);
//                this.add(tile);
            }
        }

        placeBomb();
    }

    private void placeBomb(){
        for (int i = 0; i<NUMBER_OF_BOMB; i++){
            int row = new Random().nextInt(ROW);
            int column = new Random().nextInt(COLUMN);
            if (!BOMB_LOCATION.contains(new Point(row, column)))
                BOMB_LOCATION.add(new Point(row, column));
            else
                --i;
        }
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
                            topTiles[row][column].setVisible(false);
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
                    topTiles[row][column].setShade(topTiles[row][column].getShade().brighter());
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
                    topTiles[row][column].setShade(topTiles[row][column].getShade().darker());
                    break;
                }
            }
        }
    }
}
