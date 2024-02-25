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

    // returns a string of formatted Card data
    private String getFormattedCard(Card card) {

        Integer cardsRank = Integer.valueOf(card.getRank());

        // translate rank from a value between 2 and 14 into a card suit
        String rank;
        if (cardsRank <= 10) {
            rank = String.valueOf(cardsRank);
        }
        else {
            switch (cardsRank) {
                case 11:
                    rank = "J";
                    break;
                case 12:
                    rank = "Q";
                    break;
                case 13:
                    rank = "K";
                    break;
                case 14:
                    rank = "A";
                    break;
                default:
                    rank = "Error";
            }
        }

        // shorten the cards rank into a one character value
        String suit;
        switch (card.getSuit()) {
            case "clubs":
                suit = "C";
                break;
            case "diamonds":
                suit = "D";
                break;
            case "spades":
                suit = "S";
                break;
            case "hearts":
                suit = "H";
                break;
            default:
                suit = "Error";
        }

        return rank + suit;
    }

    // method formats the cards as a comma separated list
    public String getHandAsCsv() {
        String out;
        if (hand.size() < 4) {
            return "Empty";
        }

        out = getFormattedCard(hand.get(0));
        for (int i = 1; i < 4; i++) {
            out += "," + getFormattedCard(hand.get(i));
        }

        return out;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
}
