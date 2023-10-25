package UiComponents;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameButton extends JButton implements MouseListener {
    private final static Color BACKGROUND_COLOR;
    static {
        BACKGROUND_COLOR = new Color(0x400E03);
    }
    private final String buttonText;
    private final int borderRadius;
    public final int difficulty;


    public GameButton(String buttonText, Font buttonTextFont, Point buttonTextLocation, Dimension size, int borderRadius, int difficulty) {
        this.buttonText = buttonText;
        this.borderRadius = borderRadius;
        this.difficulty = difficulty;
        this.setFont(buttonTextFont);
        this.setPreferredSize(size);

        this.setBackground(BACKGROUND_COLOR);
        this.setBorder(new EmptyBorder(buttonTextLocation.y, buttonTextLocation.x, 0, 0));

        this.addMouseListener(this);
        this.setOpaque(false);
    }
    public GameButton(String buttonText, Font buttonTextFont, Point buttonTextLocation, Dimension size, int borderRadius, Color backgroundColor) {
         this(buttonText, buttonTextFont, buttonTextLocation, size, borderRadius, -1);
         this.setBackground(backgroundColor);
     }
    @Override
    public void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics2D.setPaint(this.getBackground());
        graphics2D.fillRoundRect(0,0, this.getWidth(), this.getHeight(), borderRadius, borderRadius);

        graphics2D.setPaint(Color.WHITE);
//        graphics2D.setFont(buttonTextFont);
//        graphics2D.drawString(buttonText, buttonTextLocation.x, buttonTextLocation.y);
        graphics2D.drawString(buttonText, getInsets().left, graphics.getFontMetrics().getMaxAscent() + getInsets().top);

    }
    @Override
    protected void paintBorder(Graphics g) {
        // Do nothing to prevent painting the default border
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setBackground(this.getBackground().brighter());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setBackground(this.getBackground().darker());
    }
}
