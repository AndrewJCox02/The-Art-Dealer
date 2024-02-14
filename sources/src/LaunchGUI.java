/* File: LaunchGUI.java
 * Author(s): Andrew Cox
 * Date: 2/8/2024
 * Purpose: This class is responsible for creating the GUI for the project 1 game
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LaunchGUI {
    LaunchGUI() {
        // Creating instance of JFrame
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800);

        // deck stuff
        Deck deck = new Deck();
        ArrayList<HandOfCards> handsOfCards = new ArrayList<>();

        // Creating instances of JButton for Deal and Quit buttons
        JButton deal = new JButton(" Deal");
        JButton quit = new JButton("Quit");
        deal.setBounds(100, 650, 150, 75);
        quit.setBounds(350, 650, 150, 75);

        Graphics g = frame.getGraphics();

        //assigning button actions
        deal.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                HandOfCards hand = new HandOfCards(deck); // draw a new hand
                handsOfCards.add(hand); // record the hand

                ArrayList<Cards> cards = hand.getHand();

                BufferedImage i1 = null;
                BufferedImage i2 = null;
                BufferedImage i3 = null;
                BufferedImage i4 = null;

                // read the playing card images into memory
                try {
                    i1 = ImageIO.read(new File("PlayingCards\\" + cards.get(0).toString() + ".png"));
                    i2 = ImageIO.read(new File("PlayingCards\\" + cards.get(1).toString() + ".png"));
                    i3 = ImageIO.read(new File("PlayingCards\\" + cards.get(2).toString() + ".png"));
                    i4 = ImageIO.read(new File("PlayingCards\\" + cards.get(3).toString() + ".png"));
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }

                g.drawImage(i1, 100, 50, 200, 300, null);
                g.drawImage(i2, 300, 50, 200, 300, null);
                g.drawImage(i3, 100, 350, 200, 300, null);
                g.drawImage(i4, 300, 350, 200, 300, null);
            }
        });

        quit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // write the output file and close application
                FileOut.writeOutputFile(handsOfCards);
                frame.dispose();
            }
        });

        frame.add(deal);
        frame.add(quit);
    }


}
