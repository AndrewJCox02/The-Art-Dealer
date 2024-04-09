/* File: ArtDealer.java
 * Author(s): Andrew Cox, Robert Reinholdt
 * Date: 3/13/2024
 * Purpose: This class is responsible for the selection of which cards get bought by the
 * simulated art dealer. The functions will return True or False based on whether or not
 * the Art Dealer buys the card.
 */


import java.util.ArrayList;

public class ArtDealer {

    static public Integer currentPattern = 0;

    static String[] Patterns = {
            "ALL_RED_CARDS",
            "ALL_CLUBS",
            "ALL_FACE_CARDS",
            "ALL_SINGLE_DIGITS",
            "ALL_SINGLE_DIGIT_PRIMES",
            "HIGHEST_RANK"};

    // Used to determine if a card will be selected by the Art Dealer
    public static boolean cardPurchasedOLD (Card card) {
        switch (currentPattern) {
            case 0:
                return card.getSuit().equals("hearts") || card.getSuit().equals("diamonds");
            case 1:
                return card.getSuit().equals("clubs");
            case 2:
                return card.getRank().equals("11") || card.getRank().equals("12") || card.getRank().equals("13");
            case 3:
                return Integer.parseInt(card.getRank()) >= 2 && Integer.parseInt(card.getRank()) <= 9;
            case 4:
                return card.getRank().equals("2") || card.getRank().equals("3") || card.getRank().equals("5") || card.getRank().equals("7");
            case 5:
                // Logic for the highest rank pattern will be implemented in the ApplicationGUI class
                return false;
            default:
                return false;
        }
    }

    // determines which cards in a set are selected
    public static ArrayList<Boolean> cardsPurchased (ArrayList<Card> cards) {
        ArrayList<Boolean> out = new ArrayList<>();

        // if we are on the highest rank pattern
        if (currentPattern == 5) {
            Integer highestRank = 0;

            // find what card is the highest rank
            for (Card card : cards) {
                Integer cardRank = Card.translateRank(card.getRank());
                if (highestRank < cardRank) {
                    highestRank = cardRank;
                }
            }

            // the construct an array of booleans according to the rank
            for (Card card : cards) {
                Integer cardRank = Card.translateRank(card.getRank());
                out.add(cardRank == highestRank);
            }
        }
        else {
            for (Card card : cards) {
                Boolean result = false;

                switch (currentPattern) {
                    case 0:
                        result = (card.getSuit().equals("hearts") || card.getSuit().equals("diamonds"));
                        break;
                    case 1:
                        result = card.getSuit().equals("clubs");
                        break;
                    case 2:
                        result = (card.getRank().equals("11") || card.getRank().equals("12") || card.getRank().equals("13"));
                        break;
                    case 3:
                        result = (Integer.parseInt(card.getRank()) >= 2 && Integer.parseInt(card.getRank()) <= 9);
                        break;
                    case 4:
                        result = (card.getRank().equals("2") || card.getRank().equals("3") || card.getRank().equals("5") || card.getRank().equals("7"));
                        break;
                    default:
                }
                out.add(result);
            }
        }

        return out;
    }

    // Returns an array of boolean values corresponding to whether or not the cards in
    // an array are purchased or not
    public static ArrayList<Boolean> sellCardsOLD(ArrayList<Card> cards) {
        boolean foundPattern = true;
        ArrayList<Boolean> results = new ArrayList<Boolean>();
        for (int j = 0; j < 4; j++) {
            results.add(Boolean.FALSE);
        }
        int i = 0;  // Used to iterate through the results array
        for (Card item:cards) {
            results.add(i, cardPurchasedOLD(item));
            i++;
        }

        return results;
    }
}


