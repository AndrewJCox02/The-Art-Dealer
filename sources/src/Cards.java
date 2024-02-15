/* File: Cards.java
 * Author(s): Andrew Cox
 * Date: 2/8/2024
 * Purpose: This class is responsible for representing the cards in the deck, each card has a rank and a suit, and this
 class provides to get these values and to get a string representation of the card.
 */
public class Cards {

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
    Cards(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    // This class is used for testing
    public String toString() {
        return rank + "_of_" + suit;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }
}
