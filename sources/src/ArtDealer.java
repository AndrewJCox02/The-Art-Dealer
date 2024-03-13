/* File: ArtDealer.java
 * Author(s): Andrew Cox
 * Date: 3/13/2024
 * Purpose: This class is responsible for the selection of which cards get bought by the
 * simulated art dealer. The functions will return True or False based on whether or not
 * the Art Dealer buys the card.
 */


import java.util.ArrayList;

public class ArtDealer {

    // Used to determine if a card will be selected by the Art Dealer
    public boolean cardPurchased (Card card) {
        /** SELECTION PROCESS
         * If the card's suit is red then select the cards.
         * Red suits are hearts and diamonds.
         */
        if (card.getSuit() == "hearts" || card.getSuit() == "diamonds") {
            return true;
        }
        return false;
    }

    // Returns an array of boolean values corresponding to whether or not the cards in
    // an array are purchased or not
    public boolean [] sellCards(ArrayList<Card> cards) {
        boolean [] results = {false, false, false, false};
        int i = 0;  // Used to iterate through the results array
        for (Card item:cards) {
            results[i] = cardPurchased(item);
            i++;
        }
        return results;
    }

}
