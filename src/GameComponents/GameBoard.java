package GameComponents;

import Panels.Screens.GamePanel;
import Panels.Small.*;
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
    private final Set<Point> BOMB_LOCATION, REVEALED, FLAGGED;
    private final TopTile [][]topTiles;
    private final BottomTile [][]bottomTiles;
    private boolean FIRST_CLICK = true;
    private final FacePanel facePanel;
    private final TimePanel timePanel;
    private final FlagPanel flagPanel;
    private final MovesPanel movesPanel;
    private final BombsPanel bombsPanel;
    private int moves = 0;
    public GameBoard(int difficulty, FacePanel facePanel, TimePanel timePanel, FlagPanel flagPanel, MovesPanel movesPanel, BombsPanel bombsPanel){
        GameDifficulty gameDifficulty = new GameDifficulty(difficulty);
        BOARD_SIZE = gameDifficulty.BOARD_SIZE;
        TILE_SIZE = gameDifficulty.TILE_SIZE;
        BOARD_LOCATION = gameDifficulty.BOARD_LOCATION;
        ROW = gameDifficulty.ROW;
        COLUMN = gameDifficulty.COLUMN;
        NUMBER_OF_BOMB = gameDifficulty.NUMBER_OF_BOMB;

        this.facePanel = facePanel;
        this.timePanel = timePanel;
        this.flagPanel = flagPanel;
        this.movesPanel = movesPanel;
        this.bombsPanel = bombsPanel;

        flagPanel.getLabel().setText(String.valueOf(NUMBER_OF_BOMB));
        bombsPanel.getLabel().setText("0");
        bombsPanel.getTotalBombsLabel().setText(flagPanel.getLabel().getText());
        movesPanel.getLabel().setText(String.valueOf(moves)); // TODO: give value to moves
        facePanel.setCurrentImage(FacePanel.HAPPY_FACE);
        timePanel.startTimer();

        this.setLayout(new GridLayout(ROW, COLUMN));
        this.setBounds(BOARD_LOCATION.x, BOARD_LOCATION.y, BOARD_SIZE.width, BOARD_SIZE.height);

        BOMB_LOCATION = new HashSet<>();
        REVEALED = new HashSet<>();
        FLAGGED = new HashSet<>();

        TopTile.REVEALED = REVEALED;
        topTiles = new TopTile[ROW][COLUMN];
        bottomTiles = new BottomTile[ROW][COLUMN];

        initializeBoard(difficulty);
    }
    public void initializeBoard(int difficulty){
        for (int row = 0; row < ROW; row++){
            for (int column = 0; column < COLUMN; column++){
                TopTile topTile;
                BottomTile bottomTile;
                if (row % 2 == 0){
                    if (column % 2 == 0){
                        topTile = new TopTile(TopTile.LIGHTER_SHADE, row, column, difficulty);
                        bottomTile = new BottomTile(BottomTile.LIGHTER_SHADE, row, column, difficulty);
                    }else{
                        topTile = new TopTile(TopTile.DARKER_SHADE, row, column, difficulty);
                        bottomTile = new BottomTile(BottomTile.DARKER_SHADE, row, column, difficulty);
                    }
                }else {
                    if (column % 2 == 0){
                        topTile = new TopTile(TopTile.DARKER_SHADE, row, column, difficulty);
                        bottomTile = new BottomTile(BottomTile.DARKER_SHADE, row, column, difficulty);
                    }else{
                        topTile = new TopTile(TopTile.LIGHTER_SHADE, row, column, difficulty);
                        bottomTile = new BottomTile(BottomTile.LIGHTER_SHADE, row, column, difficulty);
                    }
                }

                topTile.addMouseListener(this);
                topTile.setLayout(new BorderLayout());
                topTiles[row][column] = topTile;
                bottomTiles[row][column] = bottomTile;
                this.add(topTile);
            }
        }
    }
    private void generateBomb(ArrayList<Point> nonBombLocations){
        for (int i = 0; i<NUMBER_OF_BOMB; i++){
            Point bombLocation = new Point(new Random().nextInt(ROW), new Random().nextInt(COLUMN));
            if (!BOMB_LOCATION.contains(bombLocation))
                BOMB_LOCATION.add(bombLocation);
            else
                --i;
        }

        for (int i = 0; i < nonBombLocations.size(); i++){
            if (BOMB_LOCATION.contains(nonBombLocations.get(i))){
                BOMB_LOCATION.remove(nonBombLocations.get(i));
                Point bombLocation = new Point(new Random().nextInt(ROW), new Random().nextInt(COLUMN));
                if (BOMB_LOCATION.contains(bombLocation)) {
                    --i;
                }
                else {
                    BOMB_LOCATION.add(bombLocation);
                }
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

    public void disableBoard() {
        for (int row = 0; row < ROW; row++){
            for (int column = 0; column < COLUMN; column++){
                topTiles[row][column].removeMouseListener(this);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        TopTile pressedTile = (TopTile) e.getSource();
        int row = pressedTile.getRow();
        int column = pressedTile.getColumn();
        Point currentLocation = new Point(row, column);
        boolean gameOver = false;

        if (e.getButton() == MouseEvent.BUTTON1){
            if (!pressedTile.isFlagged()){
                if (!FIRST_CLICK && !bottomTiles[row][column].isEmpty() && !bottomTiles[row][column].isBOMB() && !topTiles[row][column].isReleased())
                    GameAudio.revealSound(bottomTiles[row][column].getAdjacentBomb());
                if (FIRST_CLICK){
                    FIRST_CLICK = false;
                    for (var flaggedLocation:FLAGGED)
                        topTiles[flaggedLocation.x][flaggedLocation.y].setFlaggedQuiet(false);
                    FLAGGED.clear();
                    bombsPanel.getLabel().setText("0");
                    var nonBombLocations = pressedTile.getAdjacent();
                    nonBombLocations.add(currentLocation);
                    generateBomb(nonBombLocations);
                }
                if (bottomTiles[row][column].isBOMB()) { // todo: where game over is going to be called
                    timePanel.stopTimer();
                    gameOver = true;
                }
                if (!pressedTile.isReleased() && bottomTiles[row][column].isEmpty())
                    GameAudio.firstClick();
                pressedTile.released(topTiles, bottomTiles);
                REVEALED.add(currentLocation);

                if (REVEALED.size() == (ROW * COLUMN) - NUMBER_OF_BOMB) {
                    timePanel.stopTimer();
                    facePanel.setCurrentImage(FacePanel.HAPPY_FACE);
                    GameAudio.winSound();
                    disableBoard();
                    GamePanel.getInstance().add(new EndGamePanel(EndGamePanel.WIN, timePanel.getLabel().getText(), moves), 0);
                }
                if (gameOver){
                    facePanel.setCurrentImage(FacePanel.SAD_FACE);
                    GameAudio.loseSound();
                    disableBoard();
                    GamePanel.getInstance().add(new EndGamePanel(EndGamePanel.LOSE, timePanel.getLabel().getText(), moves), 0);
                }
                movesPanel.getLabel().setText(String.valueOf(++moves));
            }
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            if (!pressedTile.isFlagged()){
                if (FLAGGED.size() < NUMBER_OF_BOMB && !pressedTile.isReleased()){
                    pressedTile.setFlagged(true);
                    FLAGGED.add(currentLocation);

                    var bombs = Integer.parseInt(bombsPanel.getLabel().getText());
                    bombsPanel.getLabel().setText(String.valueOf(++bombs));
                }
            }else {
                pressedTile.setFlagged(false);
                FLAGGED.remove(currentLocation);

                var bombs = Integer.parseInt(bombsPanel.getLabel().getText());
                bombsPanel.getLabel().setText(String.valueOf(--bombs));
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        ((TopTile) e.getSource()).refreshShade();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ((TopTile) e.getSource()).refreshShade();
    }
}
