/* File: Deck.java
 * Author: Andrew Cox
 * Date: 2/8/2024
 * Purpose: This class is responsible for representing the deck in which cards are held
 *          and with which the "game" is played
 */
import java.util.*;
public class Deck {
    // These two arrays will be used in the creation of individual cards
    static final String [] ranks = {"2", "3", "4", "5", "6", "7", "8",
            "9", "10", "11", "12", "13", "14"};
    static final String [] suits = {"clubs", "diamonds", "spades", "hearts"};

    /* The ArrayList deck will hold all the cards. The list is used
     * instead of the built-in java array because it allows for easier adding
     * cards to and shuffling the deck
     */
    private List<Cards> deck = new ArrayList<>();

    // Creates a new deck of 52 cards and shuffles it
    Deck() {
        // Creates the deck

        // Loops through both arrays to create a deck will all the cards
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(new Cards(rank, suit));
            }
        } // After this loop, deck will contain all 52 cards

        // Shuffles the deck
        Collections.shuffle(deck);
    }

    // Returns the first card in the deck
    public Cards draw() {
        return deck.remove(0);
    }
}

