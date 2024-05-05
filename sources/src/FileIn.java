/* File: FileOut.java
 * Author(s): Robert Reinholdt
 * Date: 4/10/2024
 * Purpose: This class contains functions for reading input files
 */

import java.io.*;

public class FileIn {

    // reads the log file LastWon.txt
    public static Integer readLastWon() {
        String fileName = "LastWon.txt"; // output file's name

        File file = new File(fileName); // file object representing the location of the output file
        BufferedReader fIn = null;

        try {
            // check if file exists, returning 0 if not.
            if (!file.exists()) {
                System.out.print("FileOut: File " + fileName + " Does not exist yet returning 0");
                return 0;
            }
            // create a new BufferedReader
            fIn = new BufferedReader(new FileReader(file.getAbsoluteFile()));

            return Integer.valueOf(fIn.readLine());
        }
        catch (IOException ioe) {
            ioe.printStackTrace(); // prints the stack trace if an IOException occurs
        }
        finally {
            // close out the BufferedReader if it has been initialized at any point
            if (fIn != null) {
                try {
                    fIn.close();
                }
                catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }

        return 0;
    }


}
