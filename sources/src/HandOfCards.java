/* File: LaunchGUI.java
 * Author(s): Robert Reinholdt
 * Date: 2/14/2024
 * Purpose: This class represents a set of four cards
 */

import java.util.ArrayList;

public class HandOfCards {

    private final ArrayList<Cards> hand = new ArrayList<>(4);

    HandOfCards(Deck deck) {
        this.drawNewHand(deck);
    }

    // draws 4 cards from deck
    public void drawNewHand(Deck deck) {
        hand.clear(); // get rid of previous hand
        hand.add(deck.draw());
        hand.add(deck.draw());
        hand.add(deck.draw());
        hand.add(deck.draw());
    }

    // formats the cards as a comma separated list
    public String getHandAsCsv() {

        String out;
        if (hand.size() < 4) {
            return "Empty";
        }

        out = hand.get(0).getRank() + hand.get(0).getSuit();
        for (int i = 1; i < 4; i++) {
            out += "," + hand.get(i).getRank() + hand.get(i).getSuit();
        }

        return out;
    }
}
