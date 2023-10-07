package GameComponents;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {
    protected final Dimension BOARD_SIZE, TILE_SIZE;
    protected final Point BOARD_LOCATION;
    protected final int ROW, COLUMN, NUMBER_OF_BOMB;

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
    }
}
