/*
* File: Main.java
* Author(s): Andrew Cox, Robert Reinholdt, Schuyler Condon
* Date: 2/15/2024
* Class ID: CMP_SCI-4500-001
* Group Mates: Andrew Cox, Robert Reinholdt, Schuyler Condon, Dramond Ashford, Marcus Stovall
* Language & IDE:  This code was written, compiled and built using Java on the Intellij IDEA IDE.
* External Resource Citations: In the writing of this code several external sources were used to brush up on Java
syntax and proper use of Java methods, these were CodeProject, The Stack Overflow, docs.oracle.com, and W3Schools.  Github was used
to see how other developers solved similar problems and aided in brainstorming. The Jetbrains website was also used to
help in properly using the Intellij IDE.
* Project's Purpose: This project entitled "The Art Dealer", was designed to allow users to draw hands of four
cards from a standard 52 card poker deck.  Within the cards drawn there never be duplicate cards in the same hand,
neither will there be any jokers drawn.  The user can continue drawing cards as long as they want and simply quit the
program when they are ready to be done.  In the background (not seen on the user interface) the program will also keep
track of what cards are drawn and the date they were drawn on by writing them into a "DealtCards" text file using EBNF
grammar.
* Data Structures: This program uses Stack, Arrays, and ArrayList data structure.
* External Files: A file containing a list of 52 playing cards and their images was used in this project as images to be
shown to the user of the program.
* Instructions to compile, build and execute: Open file in Intellij IDE.  Build project by clicking on 'Build' >
'Build Project' tabs.  Run project by clicking on the file in the file explorer and selecting 'Run'.
* Instructions on creating a Windows executable: After compiling, building and executing the program and making sure it
works properly we can now produce a Windows executable file.  Start by clicking on the following tabs 'File' >
'Project Structure' > 'Artifacts'.  At the Artifacts menu click the '+' icon to add a new artifact.  Select 'Jar' >
'From modules with dependencies...' in the 'Main Class' section of the window that pops up, select the main class
of the project, under 'JAR files from libraries' section of the pop-up make sure the 'extract to the target JAR' box is
ticked.  Click on 'OK'  to move to the next window that shows the details of the executable file you are creating.
Click on 'OK' and you will be taken back to the main IDE screen.  From the main screen go to the 'Build' tab and click
on the 'Build Artifacts' button.  From the pop-up menu choose the artifact you just created and click on 'Build'.  The
Windows executable java file is built and can now be found in the 'Out' file under 'Artifacts' in the program files.  It
is important to note that the file labeled 'PlayingCards' needs to be put into the same folder as the executable file
you have just built in order for the program to run properly.
* Class Purpose: This class is the main class and creates a new instance of LaunchGUI()
*/

// program's main class
public class Main {
    public static void main(String[] args) {
        // creates new instance of LaunchGUI class
        // starts GUI
        ApplicationGUI UI = new ApplicationGUI();
    }
}