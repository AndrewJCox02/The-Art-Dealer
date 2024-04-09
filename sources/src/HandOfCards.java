/* File: HandOfCards.java
 * Author(s): Robert Reinholdt, Schuyler Condon
 * Date: 2/14/2024
 * Purpose: This class represents a HandOfCards, the class has methods to interact with deck objects
 * and can format the cards as a string representation
 */

import java.util.ArrayList;

// class represents a hand of cards
public class HandOfCards {

    // ArrayList to hold the cards that are in the hand
    private final ArrayList<Card> hand = new ArrayList<>(4);
    private final ArrayList<Boolean> selected = new ArrayList<>(4);

    // constructor takes a deck and draws a new hand
    HandOfCards(Deck deck) {
        this.drawNewHand(deck);
    }

    // construct a hand from a list of cards
    HandOfCards(ArrayList<Card> cards) {
        for (Card card : cards) {
            hand.add(card);
        }
    }

    HandOfCards(ArrayList<Card> cards, ArrayList<Boolean> selectedCards) {
        for (Card card : cards) {
            hand.add(card);
        }

        for (boolean bool : selectedCards) {
            selected.add(bool);
        }
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

        if (selected.get(0)) {
            out = "*" + hand.get(0).getFormattedCard() + "*";
        }
        else {
            out = hand.get(0).getFormattedCard();
        }

        for (int i = 1; i < 4; i++) {
            if (selected.get(i)) {
                out += "," + "*" + hand.get(i).getFormattedCard() + "*";
            }
            else {
                out += "," + hand.get(i).getFormattedCard();
            }
        }

        return out;
    }

    // returns the hand as an arraylist of cards
    public ArrayList<Card> getHand() {
        return hand;
    }
}
