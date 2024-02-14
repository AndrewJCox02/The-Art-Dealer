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

    // returns a string of formatted Card data
    private String getFormattedCard(Cards card) {

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

    // formats the cards as a comma separated list
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

    public ArrayList<Cards> getHand() {
        return hand;
    }
}
