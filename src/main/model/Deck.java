package model;

import java.util.ArrayList;
import java.util.Collections;

// Represents a deck of cards as an arraylist
public class Deck {
    private final ArrayList<Card> deck;

    // EFFECTS: creates an entire deck of cards with suit and number as an arraylist
    public Deck() {
        deck = new ArrayList<>();
        for (int s = 1; s <= 4; s++) {
            for (int n = 1; n <= 13; n++) {
                deck.add(new Card(s, n));
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: if the deck is empty make a new deck and replace the old one
    public Deck fillDeckIfEmpty(Deck currentDeck) {
        if (currentDeck.size() == 0) {
            Deck newDeck = new Deck();
            newDeck.shuffle();
            currentDeck = newDeck;
        }
        return currentDeck;
    }

    // REQUIRES: deck not be empty
    // MODIFIES: this
    // EFFECTS: shuffles the arraylist
    public void shuffle() {
        Collections.shuffle(deck);
    }

    // REQUIRES: deck not be empty
    // MODIFIES: this
    // EFFECTS: draws the first card of the shuffled deck
    public Card drawFromDeck() {
        return deck.remove(0);
    }

    // MODIFIES: this
    // EFFECTS: clears all the cards from the deck leaving it empty
    public void clear() {
        deck.clear();
    }

    // EFFECTS: returns the size of the deck
    public int size() {
        return deck.size();
    }

    // EFFECTS: returns the card at the given index
    public Card get(int i) {
        return deck.get(i);
    }
}
