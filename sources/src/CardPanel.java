/* File: Cards.java
 * Author(s): Robert Reinholdt
 * Date: 2/29/2024
 * Purpose: This class extends JComponent to control drawing cards onto the screen
 */


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class CardPanel extends JComponent {

    private final Integer cardWidth = 160;
    private final Integer cardHeight = 240;

    private ArrayList<BufferedImage> cardImages = new ArrayList<>();

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // clear prior cards
        g.setColor(new Color(20,100,20));
        g.fillRect(0,0,this.getSize().width,this.getSize().height);

        // draw new cards
        for (int i = 0; i < cardImages.size(); i++) {
            g.drawImage(cardImages.get(i), 60 + cardWidth * i, 75, cardWidth, cardHeight, null);
        }
    }

    // setter for cards buffered images
    public void setCardImages(ArrayList<BufferedImage> in) {
        cardImages = in;
    }

}
