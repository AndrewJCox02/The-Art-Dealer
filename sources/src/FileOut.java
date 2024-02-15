/* File: LaunchGUI.java
 * Author(s): Robert Reinholdt, Schuyler Condon
 * Date: 2/14/2024
 * Purpose: This class creates the "CardsDealt.txt" output file using an array of cards as input
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FileOut {

    // writes the hands of cards to output file
    public static void writeOutputFile(ArrayList<HandOfCards> in) {

        String fileName = "CardsDealt.txt"; // output file's name

        // if there are no cards to save method returns immediately
        if (in.size() == 0) {
            return;
        }

        // gets the current date
        Date today = Calendar.getInstance().getTime();
        // format the date into mm/dd/yyyy
        String dateLine = (today.getMonth() + 1) +
                "/" + today.getDate() +
                "/" + (today.getYear() + 1900);


        File file = new File(fileName);
        BufferedWriter fOut = null;

        try {
            // create the file if it does not already exist
            if (file.createNewFile()) {
                System.out.println("Creating new file [" + fileName + "]");
            }
            // create a new BufferedWriter, with the FileWriter's append flag being true
            fOut = new BufferedWriter(new FileWriter(file.getAbsoluteFile(),true));
            fOut.write(dateLine); // write the current date to the file
            fOut.newLine();

            // each hand of cards gets written into file in CSV format
            for (HandOfCards hand : in) {
                fOut.write(hand.getHandAsCsv());
                fOut.newLine();
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace(); // prints the stack trace if an IOException occurs
        }
        finally {
            // close out the BufferedWriter if it has been initialized at any point
            if (fOut != null) {
                try {
                    fOut.close();
                }
                catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }
}
