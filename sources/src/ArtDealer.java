/* File: ArtDealer.java
 * Author(s): Andrew Cox, Robert Reinholdt
 * Date: 3/13/2024
 * Purpose: This class is responsible for the selection of which cards get bought by the
 * simulated art dealer. The functions will return True or False based on whether
 * the Art Dealer buys the card.
 */


import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ArtDealer {

    static public Integer currentPattern = 0;
    static public Integer finalPattern = 12;

    static String[] Patterns = {
            "ALL_RED_CARDS",
            "ALL_CLUBS",
            "ALL_FACE_CARDS",
            "ALL_SINGLE_DIGITS",
            "ALL_SINGLE_DIGIT_PRIMES",
            "HIGHEST_RANK",
            "RISING_RUN_IN_THE_SAME_SUIT",
            "SKIPPING_BY_TWO_ANY_SUIT",
            "ADDS_TO_ELEVEN",
            "ACES_AND_EIGHTS",
            "SORT_OF_A_ROYAL_FLUSH",
            "TWO_BlACK_JACK_COMBOS"};

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

        if (currentPattern < 5) {
            for (Card card : cards) {
                boolean result = false;

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
        else if (currentPattern == 5) { // The highest rank
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
        else if (currentPattern == 6) { // Rising run in the same suit
            boolean result;
            result = cards.get(0).getSuit().equals(cards.get(1).getSuit()) && cards.get(1).getSuit().equals(cards.get(2).getSuit()) && cards.get(2).getSuit().equals(cards.get(3).getSuit());
            if (result) {   // If all the cards have the same suit
                result = Card.translateRank(cards.get(0).getRank()) < Card.translateRank(cards.get(1).getRank()) && Card.translateRank(cards.get(1).getRank()) < Card.translateRank(cards.get(2).getRank()) && Card.translateRank(cards.get(2).getRank()) < Card.translateRank(cards.get(3).getRank());
            }
            if (result) {   // If the cards are in order
                for (int i = 0; i < 4; i++) {
                    out.add(true);  // All cards are accepted
                }
            }
            else {
                for (int i = 0; i < 4; i++) {
                    out.add(false);  // All cards are rejected
                }
            }
        }
        else if (currentPattern == 7) { // skipping by 2, Any suit
            ArrayList<Integer> ranks = new ArrayList<>();
            for (Card card : cards) {
                ranks.add(Card.translateRank(card.getRank()));
            }
            ranks.sort(null);
            if (ranks.get(0) + 2 == ranks.get(1) && ranks.get(1) + 2 == ranks.get(2) && ranks.get(2) + 2 == ranks.get(3)) {
                for (int i = 0; i < 4; i++) {
                    out.add(true);
                }
            }
            else {
                for (int i = 0; i < 4; i++) {
                    out.add(false);
                }
            }
        }
        else if (currentPattern == 8); // Adds to eleven is handled using its own function in applicationGUI due to its special requirements
        else if (currentPattern == 9) { // Aces and eights
            ArrayList<Integer> ranks = new ArrayList<>();
            for (Card card : cards) {
                ranks.add(Card.translateRank(card.getRank()));
            }
            ranks.sort(null);

            if (ranks.get(0).equals(ranks.get(1)) && ranks.get(2).equals(ranks.get(3)) && ranks.get(0) == 8 && ranks.get(2) == 14) {
                for (int i = 0; i < 4; i++) {
                    out.add(true);
                }
            }
            else {
                for (int i = 0; i < 4; i++) {
                    out.add(false);
                }
            }
        }
        else if (currentPattern == 10) { // Sort of royal flush

            String suit = cards.get(0).getSuit();
            boolean suitCheck = true;
            boolean ace = false;
            boolean king = false;
            boolean queen = false;
            boolean jack = false;

            for (Card card : cards) {
                // make sure all cards are of the same suit
                if (!suit.equals(card.getSuit())) {
                    suitCheck = false;
                }

                // if the card is one of the desired ranks, check its flag
                if(Card.translateRank(card.getRank()) == 11) {
                    jack = true;
                }
                if(Card.translateRank(card.getRank()) == 12) {
                    queen = true;
                }
                if(Card.translateRank(card.getRank()) == 13) {
                    king = true;
                }
                if(Card.translateRank(card.getRank()) == 14) {
                    ace = true;
                }
            }

            if (suitCheck && ace && king && queen && jack) {
                for (int i = 0; i < 4; i++) {
                    out.add(true);
                }
            }
            else {
                for (int i = 0; i < 4; i++) {
                    out.add(false);
                }
            }
        }

        return out;
    }

    // handles pattern Adds to eleven, jPanel is needed to handle purchased messages
    public static ArrayList<HandOfCards> AddsToEleven (ArrayList<Card> cards, JPanel panel) {
        // why does this case need so many special exceptions compared to the regular flow

        ArrayList<Boolean> total = new ArrayList<>();
        ArrayList<HandOfCards> out = new ArrayList<>();
        ArrayList<Integer> ranks = new ArrayList<Integer>();

        // initialize the output to false, so that we can test for successful cases
        for (int i = 0; i < 4; i++) {
            total.add(false);
        }

        for (Card card : cards) {
            int rank = Card.translateRank(card.getRank());

            // convert ace to one
            if (rank == 14) {
                rank = 1;
            }
            // if the rank is a face card, set it to a negative integer such that any set with it will fail
            if (rank > 10) {
                rank = -50;
            }
            ranks.add(rank);
        }

        // binary cases
        if (ranks.get(0) + ranks.get(1) == 11) {
            total.set(0, true);
            total.set(1, true);

            JOptionPane.showMessageDialog(panel, "Pair of cards: " + cards.get(0).toPlainString() + " and " + cards.get(1).toPlainString() +  " were purchased.",
                    "Subset Purchased", JOptionPane.INFORMATION_MESSAGE);

            // add card selection to setsOfCards arraylist for logging
            out.add(new HandOfCards(new ArrayList<>(cards), new ArrayList<>(Arrays.asList(true,true,false,false))));
        }
        if (ranks.get(0) + ranks.get(2) == 11) {
            total.set(0, true);
            total.set(2, true);
            JOptionPane.showMessageDialog(panel, "Pair of cards: " + cards.get(0).toPlainString() + " and " + cards.get(2).toPlainString() +  " were purchased.",
                    "Subset Purchased", JOptionPane.INFORMATION_MESSAGE);

            // add card selection to setsOfCards arraylist for logging
            out.add(new HandOfCards(new ArrayList<>(cards), new ArrayList<>(Arrays.asList(true,false,true,false))));
        }
        if (ranks.get(0) + ranks.get(3) == 11) {
            total.set(0, true);
            total.set(3, true);
            JOptionPane.showMessageDialog(panel, "Pair of cards: " + cards.get(0).toPlainString() + " and " + cards.get(3).toPlainString() +  " were purchased.",
                    "Subset Purchased", JOptionPane.INFORMATION_MESSAGE);
            // add card selection to setsOfCards arraylist for logging
            out.add(new HandOfCards(new ArrayList<>(cards), new ArrayList<>(Arrays.asList(true,false,false,true))));
        }
        if (ranks.get(1) + ranks.get(2) == 11) {
            total.set(1, true);
            total.set(2, true);
            JOptionPane.showMessageDialog(panel, "Pair of cards: " + cards.get(1).toPlainString() + " and " + cards.get(2).toPlainString() +  " were purchased.",
                    "Subset Purchased", JOptionPane.INFORMATION_MESSAGE);
            // add card selection to setsOfCards arraylist for logging
            out.add(new HandOfCards(new ArrayList<>(cards), new ArrayList<>(Arrays.asList(false,true,true,false))));
        }
        if (ranks.get(1) + ranks.get(3) == 11) {
            total.set(1, true);
            total.set(3, true);
            JOptionPane.showMessageDialog(panel, "Pair of cards: " + cards.get(1).toPlainString() + " and " + cards.get(3).toPlainString() +  " were purchased.",
                    "Subset Purchased", JOptionPane.INFORMATION_MESSAGE);
            // add card selection to setsOfCards arraylist for logging
            out.add(new HandOfCards(new ArrayList<>(cards), new ArrayList<>(Arrays.asList(false,true,false,true))));
        }
        if (ranks.get(2) + ranks.get(3) == 11) {
            total.set(2, true);
            total.set(3, true);
            JOptionPane.showMessageDialog(panel, "Pair of cards: " + cards.get(2).toPlainString() + " and " + cards.get(3).toPlainString() +  " were purchased.",
                    "Subset Purchased", JOptionPane.INFORMATION_MESSAGE);
            // add card selection to setsOfCards arraylist for logging
            out.add(new HandOfCards(new ArrayList<>(cards), new ArrayList<>(Arrays.asList(false,false,true,true))));
        }

        // trinary cases
        if (ranks.get(0) + ranks.get(1) + ranks.get(2) == 11) {
            total.set(0, true);
            total.set(1, true);
            total.set(2, true);
            JOptionPane.showMessageDialog(panel, "Set of cards: " + cards.get(0).toPlainString() + ", " + cards.get(1).toPlainString() + ", and " + cards.get(2).toPlainString() +  " were purchased.",
                    "Subset Purchased", JOptionPane.INFORMATION_MESSAGE);
            // add card selection to setsOfCards arraylist for logging
            out.add(new HandOfCards(new ArrayList<>(cards), new ArrayList<>(Arrays.asList(true,true,true,false))));
        }
        if (ranks.get(1) + ranks.get(2) + ranks.get(3) == 11) {
            total.set(1, true);
            total.set(2, true);
            total.set(3, true);
            JOptionPane.showMessageDialog(panel, "Set of cards: " + cards.get(1).toPlainString() + ", " + cards.get(2).toPlainString() + ", and " + cards.get(3).toPlainString() +  " were purchased.",
                    "Subset Purchased", JOptionPane.INFORMATION_MESSAGE);
            // add card selection to setsOfCards arraylist for logging
            out.add(new HandOfCards(new ArrayList<>(cards), new ArrayList<>(Arrays.asList(false,true,true,true))));
        }
        if (ranks.get(0) + ranks.get(1) + ranks.get(3) == 11) {
            total.set(0, true);
            total.set(1, true);
            total.set(3, true);
            JOptionPane.showMessageDialog(panel, "Set of cards: " + cards.get(0).toPlainString() + ", " + cards.get(1).toPlainString() + ", and " + cards.get(3).toPlainString() +  " were purchased.",
                    "Subset Purchased", JOptionPane.INFORMATION_MESSAGE);
            // add card selection to setsOfCards arraylist for logging
            out.add(new HandOfCards(new ArrayList<>(cards), new ArrayList<>(Arrays.asList(true,true,false,true))));
        }
        if (ranks.get(0) + ranks.get(2) + ranks.get(3) == 11) {
            total.set(0, true);
            total.set(2, true);
            total.set(3, true);
            JOptionPane.showMessageDialog(panel, "Set of cards: " + cards.get(0).toPlainString() + ", " + cards.get(2).toPlainString() + ", and " + cards.get(3).toPlainString() +  " were purchased.",
                    "Subset Purchased", JOptionPane.INFORMATION_MESSAGE);
            // add card selection to setsOfCards arraylist for logging
            out.add(new HandOfCards(new ArrayList<>(cards), new ArrayList<>(Arrays.asList(true,false,true,true))));
        }

        // quaternary case
        if (ranks.get(0) + ranks.get(1) + ranks.get(2) + ranks.get(3) == 11) {
            total.set(0, true);
            total.set(1, true);
            total.set(2, true);
            total.set(3, true);
            JOptionPane.showMessageDialog(panel, "Set of cards: " + cards.get(0).toPlainString() + ", " + cards.get(1).toPlainString() + ", " + cards.get(2).toPlainString() + ", and " + cards.get(3).toPlainString() +  " were purchased.",
                    "Subset Purchased", JOptionPane.INFORMATION_MESSAGE);
            // add card selection to setsOfCards arraylist for logging
            out.add(new HandOfCards(new ArrayList<>(cards), new ArrayList<>(Arrays.asList(true,true,true,true))));

            return out;
        }
        else { // prevent repeated card lists in the logs
            out.add(new HandOfCards(new ArrayList<>(cards), new ArrayList<>(total)));
            return out;
        }
    }


    // Returns an array of boolean values corresponding to whether or not the cards in
    // an array are purchased or not
    public static ArrayList<Boolean> sellCardsOLD(ArrayList<Card> cards) {
        ArrayList<Boolean> results = new ArrayList<>();
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


