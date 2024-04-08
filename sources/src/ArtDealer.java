/* File: ArtDealer.java
 * Author(s): Andrew Cox, Robert Reinholdt
 * Date: 3/13/2024
 * Purpose: This class is responsible for the selection of which cards get bought by the
 * simulated art dealer. The functions will return True or False based on whether or not
 * the Art Dealer buys the card.
 */


import java.util.ArrayList;

public class ArtDealer {

    static Pattern currentPattern = Pattern.ALL_RED_CARDS;


    // Enum to represent different patterns
    private enum Pattern {
        ALL_RED_CARDS,
        ALL_CLUBS,
        ALL_FACE_CARDS,
        ALL_SINGLE_DIGITS,
        ALL_SINGLE_DIGIT_PRIMES,
        HIGHEST_RANK
    }



    // Used to determine if a card will be selected by the Art Dealer
    public static boolean cardPurchased (Card card) {
        switch (currentPattern) {
            case ALL_RED_CARDS:
                return card.getSuit().equals("hearts") || card.getSuit().equals("diamonds");
            case ALL_CLUBS:
                return card.getSuit().equals("clubs");
            case ALL_FACE_CARDS:
                return card.getRank().equals("11") || card.getRank().equals("12") || card.getRank().equals("13");
            case ALL_SINGLE_DIGITS:
                return Integer.parseInt(card.getRank()) >= 2 && Integer.parseInt(card.getRank()) <= 9;
            case ALL_SINGLE_DIGIT_PRIMES:
                return card.getRank().equals("2") || card.getRank().equals("3") || card.getRank().equals("5") || card.getRank().equals("7");
            case HIGHEST_RANK:
                // Logic for the highest rank pattern will be implemented in the ApplicationGUI class
                return false;
            default:
                return false;
        }
    }

    // Returns an array of boolean values corresponding to whether or not the cards in
    // an array are purchased or not
    public static ArrayList<Boolean> sellCards(ArrayList<Card> cards) {
        boolean foundPattern = true;
        ArrayList<Boolean> results = new ArrayList<Boolean>();
        for (int j = 0; j < 4; j++) {
            results.add(Boolean.FALSE);
        }
        int i = 0;  // Used to iterate through the results array
        for (Card item:cards) {
            results.add(i, cardPurchased(item));
            i++;
        }

        return results;
    }
}


