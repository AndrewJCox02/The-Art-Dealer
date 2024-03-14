/* File: Cards.java
 * Author(s): Robert Reinholdt
 * Date: 2/29/2024
 * Purpose: This class extends JComponent and controls how cards are drawn onto the screen
 */

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CardPanel extends JComponent {


    // array stores the cardImages that are written to the screen
    private ArrayList<BufferedImage> cardImages = new ArrayList<>();

    // generate 4 evenly spaced points that will act as the center of the y-axis for each card
    // use the generated points to scale the cards to an appropriate size and draw them in the scene,
    // finally using the scaled size and position, calculate the size and position of the confirmation symbols
    // note: multiplication is used in place of division to prevent the unlikely,
    // but potentially possible case of a rounded width of zero causing an error
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // minimum width of the cards
        final int cardWidthMin = 4;

        // card height is defined as card width multiplied by 1.5
        final double cardWidthToHeightRatio = 1.5f;

        // calculate the distance between the center of cards by taking the width of the element and reducing it to 1/5
        // defining the distance between 4 point
        double pointDistanceDouble = this.getSize().getWidth() * 0.2;

        // define card width as slightly less than the distance between their centers to add a gap between the cards
        double cardWidthDouble = pointDistanceDouble * 0.97;
        // clamp the card width to at least the minimum size
        if (cardWidthDouble < cardWidthMin ) {
            cardWidthDouble = cardWidthMin;
        }

        // multiply card width by the width to height ration to create the scaled height
        double cardHeightDouble = cardWidthDouble * cardWidthToHeightRatio;

        // calculates the positional offsets of the cards
        int CardHorizontalOffset = (int)Math.round(pointDistanceDouble * 0.5);
        int CardVerticalOffset = (int)Math.round(this.getSize().getHeight() * 0.5 - cardHeightDouble * 0.5);

        // convert card width, height, and distance to integers for use with the draw function
        int pointDistance = (int)Math.round(pointDistanceDouble);
        int cardWidth = (int)Math.round(cardWidthDouble);
        int cardHeight = (int)Math.round(cardHeightDouble);

        // clear prior cards
        g.setColor(new Color(20,100,20));
        g.fillRect(0,0,this.getSize().width,this.getSize().height);

        // draw new cards
        for (int i = 0; i < cardImages.size(); i++) {
            g.drawImage(cardImages.get(i), CardHorizontalOffset + pointDistance * i, CardVerticalOffset, cardWidth, cardHeight, null);
        }
    }

    // setter for cards buffered images
    public void setCardImages(ArrayList<BufferedImage> in) {
        cardImages = in;
    }

}
