package model;

import exceptions.EmptyDeckException;

import java.util.ArrayList;

//Represents the cards in the players hand as an arraylist
public class Hand {
    private final ArrayList<Card> hand;

    // EFFECTS: creates an arraylist to represent the player's hand
    public Hand() {
        hand = new ArrayList<>();
    }

    // MODIFIES: this, Deck
    // EFFECTS: removes the first card in the deck and places it into the hand
    public void hit(Deck deck) throws EmptyDeckException {
        Card cardDrawn = null;
        cardDrawn = deck.drawFromDeck();
        this.placeInHand(cardDrawn);
    }

    // EFFECTS: gets the sum value of all the cards in the hand
    public int getHandValue() {
        int sumPlayer = 0;
        for (Card card : hand) {
            sumPlayer = sumPlayer + card.getValue();
        }
        return sumPlayer;
    }

    // REQUIRES: the given card to exist
    // MODIFIES: this
    // EFFECTS: places the given card into the hand
    public void placeInHand(Card card) {
        hand.add(card);
    }

    // EFFECTS: gets the size of the hand
    public int size() {
        return hand.size();
    }

    // MODIFIES: this
    // EFFECTS: clears the hand of all cards leaving it empty
    public void clear() {
        hand.clear();
    }

    // EFFECTS: returns the card at the given index
    public Card get(int i) {
        return hand.get(i);
    }
}
