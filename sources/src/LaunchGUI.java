/* File: LaunchGUI.java
 * Author(s): Andrew Cox, Robert Reinholdt, Schuyler Condon
 * Date: 2/15/2024
 * Purpose: This class is responsible for creating the GUI for the project 1 game.  The GUI as defined by this class
 includes a welcome/informational JOptionPane dialog box, a JFrame with "deal" and "quit" buttons that displays
 * different card images, and a goodbye JOptionPane dialog box.
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

        // message box announcing program's purpose and user instructions
        UIManager.put("OptionPane.minimumSize", new Dimension(100, 100));
        JOptionPane.showMessageDialog(null, "This program will deal " +
                        "four cards at a time, as many times as you want. \nThere will be no Jokers and no duplicates " +
                        "within the four cards dealt to you, \nbut you might see repeat cards in different hands. " +
                        "\nAll hands and the date they were pulled are recorded and stored in the Cards \nDealt text " +
                        "file that was downloaded with this program. \n \nTo draw cards click deal, and to stop click quit. " +
                        "Click OK to continue.\n\n",
                "Welcome to The Art Dealer",
                JOptionPane.INFORMATION_MESSAGE);




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

        // ran into problem where code could not display images
        // rewrote original code to allow the program use internal resources for the card images
        deal.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                HandOfCards hand = new HandOfCards(deck); // draw a new hand
                handsOfCards.add(hand); // record the hand

                ArrayList<Cards> cards = hand.getHand(); // stores cards in hand in ArrayList named cards

                BufferedImage i1 = null;
                BufferedImage i2 = null;
                BufferedImage i3 = null;
                BufferedImage i4 = null;

                // read the playing card images into memory
                try {
                    i1 = ImageIO.read(getClass().getResource("/PlayingCards/" + cards.get(0).toString() + ".png"));
                    i2 = ImageIO.read(getClass().getResource("/PlayingCards/" + cards.get(1).toString() + ".png"));
                    i3 = ImageIO.read(getClass().getResource("/PlayingCards/" + cards.get(2).toString() + ".png"));
                    i4 = ImageIO.read(getClass().getResource("/PlayingCards/" + cards.get(3).toString() + ".png"));
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }

                g.drawImage(i1, 100, 50, 200, 300, null);
                g.drawImage(i2, 300, 50, 200, 300, null);
                g.drawImage(i3, 100, 350, 200, 300, null);
                g.drawImage(i4, 300, 350, 200, 300, null);
            }
        });

        // assigns quit button to write output file and close application
        quit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                //displays goodbye message upon clicking quit button
                JOptionPane.showMessageDialog(frame, "Thank you for playing! \nPlay again soon!",
                        "Goodbye", JOptionPane.INFORMATION_MESSAGE);

                // write the output file and close application
                FileOut.writeOutputFile(handsOfCards);
                frame.dispose();
            }
        });

        // adds the deal and quit clickable buttons to the JFrame frame
        frame.add(deal);
        frame.add(quit);

        // makes the JFrame visible after all components have been added
        frame.setVisible(true);

        /* the validate and repaint commands are being used to update the display of Swing component.  Here they are
        being used specifically to fix a problem of the deal and quit buttons not being displayed after
        the welcome message
        */
        frame.validate();
        frame.repaint();
    }


}

