package GameComponents;

import java.awt.*;

public class GameDifficulty {
    public static int EASY = 1;
    public static int MEDIUM = 2;
    public static int HARD = 3;
    protected final Dimension BOARD_SIZE, TILE_SIZE;
    protected final Point BOARD_LOCATION;
    protected final int ROW, COLUMN, NUMBER_OF_BOMB;
    public GameDifficulty(int DIFFICULTY){
        if (DIFFICULTY == EASY){
            BOARD_SIZE = new Dimension(400, 320);
            BOARD_LOCATION = new Point(57, 69);
            TILE_SIZE = new Dimension(40, 40);
            ROW = 8;
            COLUMN = 10;
            NUMBER_OF_BOMB = 10;
        } else if (DIFFICULTY == MEDIUM) {
            BOARD_SIZE = new Dimension(450, 350);
            BOARD_LOCATION = new Point(32, 54);
            TILE_SIZE = new Dimension(25, 25);
            ROW = 14;
            COLUMN = 18;
            NUMBER_OF_BOMB = 40;
        } else if (DIFFICULTY == HARD) {
            BOARD_SIZE = new Dimension(480, 400);
            BOARD_LOCATION = new Point(22, 33);
            TILE_SIZE = new Dimension(20, 20);
            ROW = 20;
            COLUMN = 24;
            NUMBER_OF_BOMB = 99;
        }else {
            BOARD_SIZE = null;
            TILE_SIZE = null;
            BOARD_LOCATION = null;
            ROW = 0;
            COLUMN = 0;
            NUMBER_OF_BOMB = 0;
        }
    }
}
