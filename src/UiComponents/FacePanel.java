package UiComponents;

import javax.swing.*;
import java.awt.*;

public class FacePanel extends JPanel{
    public final static ImageIcon HAPPY_FACE;
    public final static ImageIcon NORMAL_FACE;
    public final static ImageIcon SAD_FACE;
    private final static Dimension PANEL_SIZE = new Dimension(55, 55);
    private final static Color BACKGROUND_COLOR = new Color(0x592B1B);
    private final static int ARC;
    private final static Point IMAGE_START_LOCATION;
    private ImageIcon currentImage = NORMAL_FACE;

    static {
      HAPPY_FACE = new ImageIcon("assets/images/HAPPY_FACE.PNG");
      NORMAL_FACE = new ImageIcon("assets/images/NORMAL_FACE.PNG");
      SAD_FACE = new ImageIcon("assets/images/SAD_FACE.PNG");
      IMAGE_START_LOCATION = new Point(8, 5);
      ARC = 40;
    }
    public FacePanel(){
        this.setBounds(509, 64, 55, 55);
    }
    public void setCurrentImage(ImageIcon currentImage) {
        this.currentImage = currentImage;
        repaint();
    }
    @Override
    public void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setPaint(BACKGROUND_COLOR);
        graphics2D.fillRoundRect(0, 0, PANEL_SIZE.width, PANEL_SIZE.height, ARC, ARC);

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(currentImage.getImage(),IMAGE_START_LOCATION.x, IMAGE_START_LOCATION.y, null);
    }


}
