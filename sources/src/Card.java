import java.util.Arrays;
import java.util.Locale;

/* File: Cards.java
 * Author(s): Andrew Cox, Robert Reinholdt
 * Date: 2/8/2024
 * Purpose: This class is responsible for representing the cards in the deck, each card has a rank and a suit, and this
 class provides to get these values and to get a string representation of the card.
 */
public class Card {

    // The cards class will hold the values of an individual card's rank and suit
    private final String rank;
    private final String suit;

    /**
     * @param rank The rank of the created card, values between 2 and 14, or joker.
     *             This is stored as a String because of the jokers, as well as
     *             for ease of matching the card with it's png image.
     * @param suit The suit of the created card, possible values include
     *             clubs, diamonds, hearts and spades.
     *
     * Normally we would need to prevent the user from inputting an improper value to this
     *            class, however all instances of this class will be created in the deck
     *             class, so we do not need to worry about this.
     */
    Card(String rank, String suit) {
        this.rank = rank.toUpperCase();
        this.suit = suit.toLowerCase();
    }

    public static void main(String[] args) {
        // Test cases
        String[] set1 = {"2H", "3H", "4H", "5H"};
        String[] set2 = {"JD", "QD", "KD", "AD"};
        String[] set3 = {"7S", "8S", "9S", "10S"};
        String[] set4 = {"5H", "4H", "3H", "2H"};
        String[] set5 = {"JD", "KD", "QD", "AD"};
        String[] set6 = {"7S", "8D", "9S", "10S"};

        // Test each set
        testSet(set1);
        testSet(set2);
        testSet(set3);
        testSet(set4);
        testSet(set5);
        testSet(set6);
    }

    public static void testSet(String[] set) {
        boolean result = isPattern7(set);
        System.out.println("Cards " + Arrays.toString(set) + " : " + (result ? "Accepted" : "Rejected"));
    }

    public static boolean isPattern7(String[] set) {
        if (set.length != 4) {
            return false;
        }

        // Check if all cards have the same suit
        String suit = set[0].substring(1);
        for (String card : set) {
            if (!card.substring(1).equals(suit)) {
                return false;
            }
        }

        // Check if ranks are in rising order
        int[] ranks = new int[4];
        for (int i = 0; i < 4; i++) {
            String rankStr = set[i].substring(0, set[i].length() - 1);
            ranks[i] = getRank(rankStr);
        }
        for (int i = 0; i < 3; i++) {
            if (ranks[i] >= ranks[i + 1]) {
                return false;
            }
        }

        return true;
    }

    public static int getRank(String rankStr) {
        switch (rankStr) {
            case "A":
                return 14;
            case "K":
                return 13;
            case "Q":
                return 12;
            case "J":
                return 11;
            default:
                return Integer.parseInt(rankStr);
        }
    }

    // returns a String representation of the card
    // Note: rank is a number between 2 and 14
    public String toString() {
        return rank + "_of_" + suit;
    }

    // returns a String of card in plain English, such as 2 of Spades
    public String toPlainString() {
        return getEnglishRank() + " Of " + String.valueOf(suit.charAt(0)).toUpperCase() + suit.substring(1,suit.length());
    }

    // converts to the 2 through 14 numeric representation of rank
    public static Integer translateRank(String rank) {
        switch (rank.toLowerCase()) {
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":
                return 10;
            case "j":
            case "jack":
                return 11;
            case "q":
            case "queen":
                return 12;
            case "k":
            case "king":
                return 13;
            case "a":
            case "ace":
                return 14;
            default:
                return 0;
        }
    }

    public String getEnglishRank() {

        // translate rank from a value between 2 and 14 into a card suit

        if (Integer.valueOf(rank) <= 10) {
             return rank;
        }
        else {
            switch (Integer.valueOf(rank)) {
                case 11:
                    return "Jack";
                case 12:
                    return "Queen";
                case 13:
                    return "King";
                case 14:
                    return "Ace";
                default:
                    return "Error";
            }
        }
    }

    // returns a string of formatted Card data
    public String getFormattedCard() {
        Integer cardsRank = Integer.valueOf(this.getRank());

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
        switch (this.getSuit()) {
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

    // getter for rank
    public String getRank() {
        return rank;
    }

    // getter for suit
    public String getSuit() {
        return suit;
    }

    // checks if two card objects are of the same card
    public Boolean equals(Card card) {
        if (this == card) return true;
        if (card == null || getClass() != card.getClass()) return false;
        Card otherCard = (Card) card;
        return this.rank.equals(otherCard.rank) && this.suit.equals(otherCard.suit);
    }
}

