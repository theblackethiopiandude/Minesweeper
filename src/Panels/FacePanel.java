package Panels;

import Panels.Screens.MainPanel;

import javax.swing.*;
import java.awt.*;

public class FacePanel extends JPanel{
    public final static ImageIcon HAPPY_FACE;
    public final static ImageIcon NORMAL_FACE;
    public final static ImageIcon SAD_FACE;
    private final static Point LOCATION_ON_GAME_PANEL;
    private final static Dimension FACE_PANEL_SIZE;
    private final static Color BACKGROUND_COLOR;
    private final static int FACE_PANEL_ARC;
    private final static Point FACE_IMAGE_START_LOCATION;
    private ImageIcon currentImage = NORMAL_FACE;

    static {
        HAPPY_FACE = new ImageIcon("assets/images/HAPPY_FACE.PNG");
        NORMAL_FACE = new ImageIcon("assets/images/NORMAL_FACE.PNG");
        SAD_FACE = new ImageIcon("assets/images/SAD_FACE.PNG");
        FACE_IMAGE_START_LOCATION = new Point(8, 5);

        LOCATION_ON_GAME_PANEL = new Point(509, 64);
        BACKGROUND_COLOR = new Color(0x592B1B);
        FACE_PANEL_SIZE = new Dimension(55, 55);
        FACE_PANEL_ARC = 40;
    }
    public FacePanel(){
        this.setBounds(LOCATION_ON_GAME_PANEL.x, LOCATION_ON_GAME_PANEL.y,  FACE_PANEL_SIZE.width, FACE_PANEL_SIZE.height);
    }
    public void setCurrentImage(ImageIcon currentImage) {
        this.currentImage = currentImage;
        MainPanel.getInstance().repaint();
        repaint();
    }
    @Override
    public void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics2D.setPaint(BACKGROUND_COLOR);
        graphics2D.fillRoundRect(0, 0, FACE_PANEL_SIZE.width, FACE_PANEL_SIZE.height, FACE_PANEL_ARC, FACE_PANEL_ARC);

        graphics2D.drawImage(currentImage.getImage(), FACE_IMAGE_START_LOCATION.x, FACE_IMAGE_START_LOCATION.y, null);
    }


}
