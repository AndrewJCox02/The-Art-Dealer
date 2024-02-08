/* File: LaunchGUI.java
 * Author: Andrew Cox
 * Date: 2/8/2024
 * Purpose: This class is responsible for creating the GUI for the project 1 game
 */
import javax.swing.*;
public class LaunchGUI {
    LaunchGUI() {
        // Creating instance of JFrame
        JFrame frame = new JFrame();

        // 400 width and 500 height
        frame.setSize(600, 800);

        // Using no layout managers
        frame.setLayout(null);

        // Making the frame visible
        frame.setVisible(true);

        // Making the frame close upon exit
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating instances of JButton for Deal and Quit buttons
        JButton deal = new JButton(" Deal");
        JButton quit = new JButton("Quit");

        // x-axis, y-axis, width, height
        deal.setBounds(50, 650, 150, 75);
        quit.setBounds(350, 650, 150, 75);

        // adding button in JFrame
        frame.add(deal);
        frame.add(quit);
    }
}
