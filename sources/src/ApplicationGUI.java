/* File: ApplicationGUI.java
 * Author(s): Andrew Cox, Robert Reinholdt, Schuyler Condon
 * Date: 2/15/2024
 * Purpose: This class is responsible for creating the gui, and running the main application logic,
 * the gui is structured using a GridBagLayout, the ui is partitioned into two sections,
 * the main section which has a selector for cards and an area for displaying the cards already selected
 * it also has a sidebar that shows the history of cards selected,
 * finally when the application closes it appends a list of the chosen cards to a txt file
 * by calling the FileOut.writeOutputFile function
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class ApplicationGUI extends JFrame {

    // size constants
    private final Integer windowWidth = 1000;
    private final Integer windowHeight = 800;

    // Variables
    private final ArrayList<HandOfCards> setsOfCards =  new ArrayList<>();
    private final ArrayList<Card> currentSetOfCards = new ArrayList<>();

    // references to JComponents
    private JPanel mainPanel;
    private JPanel sidePanel;
    private CardPanel cardPanel;
    private JPanel cardLog;
    JComboBox<String> CardPickerComboBoxSuits;
    JComboBox<String> CardPickerComboBoxRanks;

    // the constructor for ApplicationGUI
    ApplicationGUI() {
        // initialize the basic parameters of the JFrame
        initJFrame();
        // Create the Main panel which contains the card selector
        createMainJPanel();
        // Create the Side panel which contains the card set log
        createSideJPanel();
        // configures the exit methods to properly save to the CardsDealt.txt file
        setupWindowsExit();
        // check the layout
        validate();
        // display the completed JFrame
        repaint();
        // display Instructions on how to use the application to the user
        displayUserInstructions();
    }

    // displays a prompt explaining the use of the application
    private void displayUserInstructions() {
        // message box announcing program's purpose and user instructions
        UIManager.put("OptionPane.minimumSize", new Dimension(100, 100));
        JOptionPane.showMessageDialog(
                this,
                "In This Application, you will select cards one at a time until you,\n" +
                        "make a set of 4, after which you can begin selecting another set of 4.\n" +
                        "Every selected set is noted on the right hand side of the screen\n" +
                        "inside of the History of Cards box.\n" +
                        "card selection is done using the two comboBoxes at the center of the window,\n" +
                        "the first box selects the rank of the card, the second box selects suit of the card.\n" +
                        "The application can be exited at any time by click the quit button,\n" +
                        "or the windows exit button at the top right of the screen.\n" +
                        "Any incomplete sets of cards, are lost upon exiting the application.",
                "Welcome to The Art Dealer",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // changes the default closing operation in windows
    private void setupWindowsExit() {
        // overrides the default closing operation to save cards in the buffer
        // if the user uses the X or "close" button
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // write the output file and close application
                FileOut.writeOutputFile(setsOfCards);
                dispose();

                System.exit(0);
            }
        });
    }

    // creates and configures the JFrame
    private void initJFrame() {
        // Creating instance of JFrame
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(windowWidth, windowHeight);
        setLayout(new GridBagLayout());
        setTitle("The Art Dealer");
        setLocationRelativeTo(null);
    }

    // creates and defines layout for the Main JPanel
    // the main panel contains the card picker, and an area to render selected cards
    private void createMainJPanel() {
        // main panel where cards are displayed
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(40,160,40));

        // the Main panel should fill the left 70% of the window
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        c.weightx=0.7;
        c.weighty=1.0;
        c.fill=GridBagConstraints.BOTH;

        add(mainPanel, c);

        // define a subsection for the cards to be displayed
        cardPanel = new CardPanel();
        cardPanel.setBackground(new Color(20,100,20));
        cardPanel.setIgnoreRepaint(true);
        c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        c.weightx=0.1;
        c.weighty=0.6;
        c.fill=GridBagConstraints.BOTH;
        mainPanel.add(cardPanel,c);


        // define a subsection for the card picker
        // the card picker will have two combo boxes that allow the users
        // to select rank and suit separately for readability
        JPanel cardPickerPanel = new JPanel();
        cardPickerPanel.setBackground(new Color(20,130,20));
        c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=1;
        c.gridwidth=2;
        c.weightx=0.1;
        c.weighty=0.0;
        c.fill=GridBagConstraints.BOTH;
        mainPanel.add(cardPickerPanel,c);

        // Label for the Card Picker
        JLabel cardPickerLabel = new JLabel("Card Picker:");
        cardPickerPanel.add(cardPickerLabel);

        // ComboBox for the card picker, deals with card ranks
        String[] ranks = {"","2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
        CardPickerComboBoxRanks = new JComboBox<>(ranks);
        CardPickerComboBoxRanks.setBounds(140,400,20,20);
        CardPickerComboBoxRanks.setSize(new Dimension(60, 20));
        CardPickerComboBoxRanks.addActionListener(e -> {
            cardPicker();
        });
        cardPickerPanel.add(CardPickerComboBoxRanks);

        // "Of" text between the two combo boxes
        JLabel cardPickerOfTxt = new JLabel("Of");
        cardPickerPanel.add(cardPickerOfTxt);

        // ComboBox for the card picker, deals with card suits
        String[] suits = {"","Clubs", "Diamonds", "Spades", "Hearts"};
        CardPickerComboBoxSuits = new JComboBox<>(suits);
        CardPickerComboBoxSuits.setSize(new Dimension(60,20));
        CardPickerComboBoxSuits.addActionListener(e -> {
            cardPicker();
        });
        cardPickerPanel.add(CardPickerComboBoxSuits);

        // spacer to keep the quit button at the bottom
        JPanel spacerPanel = new JPanel();
        spacerPanel.setOpaque(false);
        c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=2;
        c.weighty=0.3;
        c.fill=GridBagConstraints.BOTH;
        mainPanel.add(spacerPanel,c);

        // quit button located at the bottom of the screen
        JButton quit = new JButton("Quit");
        quit.setSize(new Dimension(150,75));
        // assigns quit button to write output file and close application
        quit.addActionListener(e -> {
            //JOptionPane.showMessageDialog(mainPanel, "Thank you for playing! \nPlay again soon!",
            //        "Goodbye", JOptionPane.INFORMATION_MESSAGE);
            // write the output file and close application
            FileOut.writeOutputFile(setsOfCards);
            dispose();
        });
        c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=3;
        c.weightx=0.5;
        c.insets = new Insets(0,0,5,0);
        mainPanel.add(quit,c);
    }

    // creates and defines layout for the Side JPanel
    // the side panel will contain a scrollable log of previous sets of 4 cards
    private void createSideJPanel() {
        // sidebar where a log of card sets is displayed
        sidePanel = new JPanel(new GridBagLayout());
        sidePanel.setBackground(new Color(40,130,40));

        // the side panel should occupy the right 30% of the window
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.25;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        add(sidePanel, c);

        // Contains a running list of all previous sets of cards
        cardLog = new JPanel();
        cardLog.setLayout(new BoxLayout(cardLog,BoxLayout.Y_AXIS));
        cardLog.add(Box.createHorizontalGlue());

        cardLog.setBackground(new Color(80,170,80));
        cardLog.add(new JLabel("History Of Cards:"));


        //create a scrollable area for the card log
        JScrollPane logBox = new JScrollPane(cardLog);
        logBox.createVerticalScrollBar();

        // the logBox should fill all of the Side panel,
        // a border of around 5 pixels is added for aesthetics
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0f;
        c.weighty = 1.0f;
        c.insets=new Insets(5,5,5,5);
        c.fill = GridBagConstraints.BOTH;
        sidePanel.add(logBox, c);
    }

    // function called when either the rank or suit combobox value changes
    // then performs the checks necessary to verify the selected card
    private void cardPicker() {
        String selectedRank = (String)CardPickerComboBoxRanks.getSelectedItem();
        String selectedSuit = (String)CardPickerComboBoxSuits.getSelectedItem();

        // if either rank or suit is still blank, then just return
        if (selectedRank.isEmpty() || selectedSuit.isEmpty()) {
            return;
        }

        // construct a new Card from the rank and value
        Card card = new Card(String.valueOf(Card.translateRank(selectedRank)),selectedSuit);

        // if the selected card is already in the current set, prompt the user to select another
        for (Card c : currentSetOfCards) {
            if (card.equals(c)) {
                JOptionPane.showMessageDialog(mainPanel, "Please Select a Different Card",
                        "Card Already Selected", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        // if the card is not already in the set then add it to current cards
        currentSetOfCards.add(card);

        // after saving the card to the set, reset the comboBoxes
        CardPickerComboBoxRanks.setSelectedIndex(0);
        CardPickerComboBoxSuits.setSelectedIndex(0);

        // draw already selected cards on the screen,
        Graphics g = cardPanel.getGraphics();

        // get the cards than need to be drawn
        ArrayList<BufferedImage> images = new ArrayList<>();
        ArrayList<Boolean> selectedCards = new ArrayList<>();
        for (Card c : currentSetOfCards) {
            // add the image to the array
            images.add(getCardImage(c));
            // add if the card is selected to the array
            selectedCards.add(ArtDealer.cardPurchased(c));
        }

        // send them to the cardPanel to be drawn
        cardPanel.setSelectedCards(selectedCards);
        cardPanel.setCardImages(images);

        // paint the component
        cardPanel.paintComponent(g);

        // if we have less than 4 cards in the set, return
        if (currentSetOfCards.size() < 4) {
            return;
        }

        //=======================================================
        //TODO: Select a subset of the current cards and
        // add an animation for the selection
        //=======================================================

        // if we have 4 cards in the current set, then add them to the history
        setsOfCards.add(new HandOfCards(currentSetOfCards));

        // update the log
        cardLog.add(new JLabel ("Card Set " + setsOfCards.size() + ":"));
        for (int iter = 0; iter < 4; iter++) {
            if (ArtDealer.cardPurchased(currentSetOfCards.get(iter))) {
                cardLog.add(new JLabel("*" + currentSetOfCards.get(iter).toPlainString() + "*" + ","));
            }
            else {
                cardLog.add(new JLabel(currentSetOfCards.get(iter).toPlainString() + ","));
            }
        }

        //=======================================================
        //TODO: Add the cards selected by the software
        // to the cardLog along with the choices made by the
        // user
        //=======================================================

        cardLog.updateUI();

        cardPanel.repaint();

        // clear the set for the next 4 cards
        currentSetOfCards.clear();
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

