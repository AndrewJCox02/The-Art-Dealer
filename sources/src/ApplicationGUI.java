/* File: ApplicationGUI.java
 * Author(s): Andrew Cox, Robert Reinholdt, Schuyler Condon
 * Date: 2/15/2024
 * Purpose: This class is responsible for creating the GUI for the project 1 game.  The GUI as defined by this class
 includes a welcome/informational JOptionPane dialog box, a JFrame with "deal" and "quit" buttons that displays
 * different card images, and a goodbye JOptionPane dialog box.
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class ApplicationGUI {
    ApplicationGUI() {

        Integer screenWidth = 1000;
        Integer screenHeight = 800;

        // message box announcing program's purpose and user instructions
        /*UIManager.put("OptionPane.minimumSize", new Dimension(100, 100));
        JOptionPane.showMessageDialog(
                null,
                "This program will deal four cards at a time, as many times as you want. " +
                        "\nThere will be no Jokers and no duplicates within the four cards dealt to you, " +
                        "\nbut you might see repeat cards in different hands. " +
                        "\nAll hands and the date they were pulled are recorded and stored in the Cards " +
                        "\nDealt text file that was downloaded with this program. " +
                        "\n\nTo draw cards click deal, and to stop click quit. Click OK to continue." +
                        "\n\n",
                "Welcome to The Art Dealer",
                JOptionPane.INFORMATION_MESSAGE); */



        // deck stuff
        Deck deck = new Deck();
        ArrayList<HandOfCards> handsOfCards = new ArrayList<>();

        // Creating instance of JFrame
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenWidth, screenHeight);
        frame.setTitle("The Art Dealer");

        // divide the frame into panels
        // main panel where cards are displayed
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0,0,screenWidth-250,screenHeight);
        mainPanel.setBackground(new Color(40,160,40));

        // sidebar where a log of card sets is displayed
        JPanel sidePanel = new JPanel();
        sidePanel.setBounds(screenWidth-250,0,250,screenHeight);
        sidePanel.setBackground(new Color(40,120,40));

        // Creating instances of JButton for Deal and Quit buttons
        JButton select = new JButton("Select");
        JButton quit = new JButton("Quit");
        select.setBounds(100, 650, 150, 75);
        quit.setBounds(350, 650, 150, 75);

        // override the default closing operation to save cards in the buffer
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //displays goodbye message upon clicking quit button
                /*JOptionPane.showMessageDialog(frame, "Thank you for playing! \nPlay again soon!",
                        "Goodbye", JOptionPane.INFORMATION_MESSAGE);*/

                // write the output file and close application
                FileOut.writeOutputFile(handsOfCards);
                frame.dispose();

                System.exit(0);
            }
        });

        Graphics g = frame.getGraphics();

        // ran into problem where code could not display images
        // rewrote original code to allow the program use internal resources for the card images
        select.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                HandOfCards hand = new HandOfCards(deck); // draw a new hand
                handsOfCards.add(hand); // record the hand

                ArrayList<Card> cards = hand.getHand(); // Stores cards in hand in an ArrayList named cards

                Integer cardWidth = 160;
                Integer cardHeight = 240;

                // read the playing card images into memory
                BufferedImage card1 = getCardImage(cards.get(1));
                BufferedImage card2 = getCardImage(cards.get(0));
                BufferedImage card3 = getCardImage(cards.get(3));
                BufferedImage card4 = getCardImage(cards.get(2));

                g.drawImage(card1, 60, 250, cardWidth, cardHeight, null);
                g.drawImage(card2, 60 + cardWidth, 250, cardWidth, cardHeight, null);
                g.drawImage(card3, 60 + cardWidth * 2, 250, cardWidth, cardHeight, null);
                g.drawImage(card4, 60 + cardWidth * 3, 250, cardWidth, cardHeight, null);

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
        frame.add(select);
        frame.add(quit);

        frame.add(mainPanel);
        frame.add(sidePanel);

        // makes the JFrame visible after all components have been added
        frame.setVisible(true);

        /* the validate and repaint commands are being used to update the display of Swing component.  Here they are
        being used specifically to fix a problem of the deal and quit buttons not being displayed after
        the welcome message
        */

        frame.validate();
        frame.repaint();

    }

    // gets a BufferedImage of the supplied card
    private BufferedImage getCardImage(Card card) {
        try {
            return ImageIO.read(getClass().getResource("/PlayingCards/" + card.toString() + ".png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }





}

