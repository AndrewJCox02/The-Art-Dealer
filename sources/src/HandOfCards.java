/* File: HandOfCards.java
 * Author(s): Robert Reinholdt, Schuyler Condon
 * Date: 2/14/2024
 * Purpose: This class provides methods to draw a new hand of cards from the deck.  It formats a card as a string
 and formats the hand of cards as comma separated string, and gets the hand of cards.
 */

import java.util.ArrayList;

// class represents a hand of cards
public class HandOfCards {

    // ArrayList to hold the cards that are in the hand
    private final ArrayList<Card> hand = new ArrayList<>(4);

    // constructor takes a deck and draws a new hand
    HandOfCards(Deck deck) {
        this.drawNewHand(deck);
    }

    // method draws 4 cards from deck
    public void drawNewHand(Deck deck) {
        hand.clear(); // get rid of previous hand
        //draws four new cards from the deck
        hand.add(deck.draw());
        hand.add(deck.draw());
        hand.add(deck.draw());
        hand.add(deck.draw());
    }



    // method formats the cards as a comma separated list
    public String getHandAsCsv() {
        String out;
        if (hand.size() < 4) {
            return "Empty";
        }

        out = hand.get(0).getFormattedCard();
        for (int i = 1; i < 4; i++) {
            out += "," + hand.get(i).getFormattedCard();
        }

        return out;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
}
