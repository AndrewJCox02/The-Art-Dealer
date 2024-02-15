/*
* File: Main.java
* Author(s): Andrew Cox, Robert Reinholdt, Schuyler Condon
* Date: 2/14/2024
* Class ID: CMP_SCI-4500-001
* Group Mates: Andrew Cox, Robert Reinholdt, Schuyler Condon, Dramond Ashford, Marcus Stovall
* Language & IDE:  This code was written, compiled and built using Java on the Intellij IDEA IDE.
* Project's Purpose: This project entitled "The Art Dealer", was designed to allow users to draw hands of four
cards from a standard 52 card poker deck.  Within the cards drawn there never be duplicate cards in the same hand,
neither will there be any jokers drawn.  The user can continue drawing cards as long as they want and simply quit the
program when they are ready to be done.  In the background (not seen on the user interface) the program will also keep
track of what cards are drawn and the date they were drawn on by writing them into a "DealtCards" text file using EBNF
grammar.
* Data Structures: This program uses Stack, Arrays, and ArrayList data structure.
* External Files: A file containing a list of 52 playing cards and their images was used in this project as images to be
shown to the user of the program.
* Installation:
* Class Purpose: This class is the main class and creates a new instance of LaunchGUI()


 */

// program's main class
public class Main {
    public static void main(String[] args) {
        // creates new instance of LaunchGUI class
        // starts GUI
        LaunchGUI UI = new LaunchGUI();
    }
}