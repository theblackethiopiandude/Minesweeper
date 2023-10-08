package UiComponents;

import GameComponents.GameDifficulty;

import javax.swing.*;
import java.awt.*;

public class TopTile extends JPanel {
    public final static Color LIGHTER_SHADE = new Color(0x73412F);
    public final static Color DARKER_SHADE = new Color(0x592B1B);

    private boolean FLAGGED = false, RELEASED = false;
    private Color SHADE;
    private final Dimension TILE_SIZE;
    private final ImageIcon FLAG_ICON;
    private final Point FLAG_LOCATION;
    private final int ROW, COLUMN;

    public TopTile(Color shade, int row, int column, int difficulty) {
        SHADE = shade;
        ROW = row;
        COLUMN = column;

        if (difficulty == GameDifficulty.EASY){
            TILE_SIZE = new Dimension(40, 40);
            FLAG_ICON = new ImageIcon("assets/images/FLAG_EASY.PNG");
            FLAG_LOCATION = new Point(10, 9);
        } else if (difficulty == GameDifficulty.MEDIUM) {
            TILE_SIZE = new Dimension(25, 25);
            FLAG_ICON = new ImageIcon("assets/images/FLAG_MEDIUM.PNG");
            FLAG_LOCATION = new Point(7, 6);
        } else if (difficulty == GameDifficulty.HARD) {
            TILE_SIZE = new Dimension(20, 20);
            FLAG_ICON = new ImageIcon("assets/images/FLAG_HARD.PNG");
            FLAG_LOCATION = new Point(6, 4);
        }else {
            TILE_SIZE = new Dimension();
            FLAG_ICON = new ImageIcon();
            FLAG_LOCATION = new Point();
        }
    }

    public int getRow() {
        return ROW;
    }

    public int getColumn() {
        return COLUMN;
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

    public Color getShade(){
        return SHADE;
    }
    public void setShade(Color shade){
        SHADE = shade;
        repaint();
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
