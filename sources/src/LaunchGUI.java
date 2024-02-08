/* File: LaunchGUI.java
 * Author(s): Andrew Cox
 * Date: 2/8/2024
 * Purpose: This class is responsible for creating the GUI for the project 1 game
 */
import javax.swing.*;
public class LaunchGUI {
    LaunchGUI() {
        // Creating instance of JFrame
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800);

        // Creating instances of JButton for Deal and Quit buttons
        JButton deal = new JButton(" Deal");
        JButton quit = new JButton("Quit");
        deal.setBounds(50, 650, 150, 75);
        quit.setBounds(350, 650, 150, 75);
        frame.add(deal);
        frame.add(quit);
    }
}
