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

public class ApplicationGUI extends JFrame {

    // variables
    private ArrayList<HandOfCards> setsOfCards =  new ArrayList<>();
    private Integer windowWidth = 1000;
    private Integer windowHeight = 800;

    private JPanel mainPanel;
    private JPanel sidePanel;
    private JPanel cardPanel;
    private JList<String> cardLog;

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

        /*
        Graphics mg = mainPanel.getGraphics();

        Integer cardWidth = 160;
        Integer cardHeight = 240;

        String[] ranks = {"","2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
        JComboBox<String> comboRanks = new JComboBox<>(ranks);
        comboRanks.setBounds(140,400,20,20);


        // JComboBox representing suits of cards
        String[] suits = {"Clubs", "Diamonds", "Spades", "Hearts"};
        JComboBox<String> comboSuits = new JComboBox<>(suits);
        comboSuits.setBounds(60,400,80,20);

        BufferedImage cardImg = getCardImage(new Card("2","hearts"));
        mg.drawImage(cardImg, 60, 75, cardWidth, cardHeight, null);

        mainPanel.add(comboRanks);
        mainPanel.add(comboSuits);
        */
    }

    // displays a prompt explaining the use of the application
    private void displayUserInstructions() {
        // message box announcing program's purpose and user instructions
        UIManager.put("OptionPane.minimumSize", new Dimension(100, 100));
        JOptionPane.showMessageDialog(
                this,
                "This program will deal four cards at a time, as many times as you want. " +
                        "\nThere will be no Jokers and no duplicates within the four cards dealt to you, " +
                        "\nbut you might see repeat cards in different hands. " +
                        "\nAll hands and the date they were pulled are recorded and stored in the Cards " +
                        "\nDealt text file that was downloaded with this program. " +
                        "\n\nTo draw cards click deal, and to stop click quit. Click OK to continue." +
                        "\n\n",
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
        cardPanel = new JPanel(new GridBagLayout());
        cardPanel.setBackground(new Color(20,100,20));
        c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        c.weightx=0.1;
        c.weighty=0.6;
        c.fill=GridBagConstraints.BOTH;
        mainPanel.add(cardPanel,c);

        // quit button in the bottom portion
        JButton quit = new JButton("Quit");
        quit.setSize(new Dimension(150,75));
        // assigns quit button to write output file and close application
        quit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(mainPanel, "Thank you for playing! \nPlay again soon!",
                //        "Goodbye", JOptionPane.INFORMATION_MESSAGE);
                // write the output file and close application
                FileOut.writeOutputFile(setsOfCards);
                dispose();
            }
        });

        c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=1;
        c.weightx=0.5;
        c.weighty=0.4;

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
        c.weightx = 0.3;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        add(sidePanel, c);

        // Contains a running list of all previous sets of cards
        cardLog = new JList<>(new String[]{"Entry1","Entry2","Entry3","Entry4","Entry5"});
        cardLog.setBackground(new Color(80,170,80));
        cardLog.setDragEnabled(false);

        //create a scrollable area for the card log
        JScrollPane logBox = new JScrollPane(cardLog);
        logBox.createVerticalScrollBar();

        // the logBox should fill all of the Side panel,
        // a border of around 5 pixels is added for aesthetics
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.1f;
        c.weighty = 0.1f;
        c.insets=new Insets(5,5,5,5);
        c.fill = GridBagConstraints.BOTH;
        sidePanel.add(logBox, c);
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