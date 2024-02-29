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

    // returns a String representation of the card
    // Note: rank is a number between 2 and 14
    public String toString() {
        return rank + "_of_" + suit;
    }

    // converts to the 2 through 14 numeric representation of rank
    private static Integer translateRank(String rank) {
        switch (rank) {
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
            case "J":
            case "Jack":
                return 11;
            case "Q":
            case "Queen":
                return 12;
            case "K":
            case "King":
                return 13;
            case "A":
            case "Ace":
                return 14;
            default:
                return 0;
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

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }



    // checks if two card objects are of the same card
    public Boolean equals(Card card) {
        if (this.toString().equals(card.toString())) {
            return true;
        }
        return false;
    }
}
