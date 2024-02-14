import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        LaunchGUI UI = new LaunchGUI();


        // debug code for testing the FileOut class
        Deck deck = new Deck();
        ArrayList<HandOfCards> hands = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            hands.add(new HandOfCards(deck));
        }

        FileOut.writeOutputFile(hands);

    }
}