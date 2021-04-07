package model;

import exceptions.EmptyDeckException;

import java.util.ArrayList;

// Represents a dealers hand, (arraylist of cards)
public class Dealer {
    private final ArrayList<Card> dealer;

    // EFFECTS: creates a an arraylist representing the hand of the dealer
    public Dealer() {
        dealer = new ArrayList<>();
    }

    // MODIFIES: this, Deck
    // EFFECTS: removes the first card from the given deck and adds it into the dealer
    public void hit(Deck deck) throws EmptyDeckException {
        Card cardDrawn = null;
        cardDrawn = deck.drawFromDeck();
        this.placeInDealer(cardDrawn);
    }

    // EFFECTS: returns the sum value of all the cards int eh dealer
    public int getDealerValue() {
        int sumDealer = 0;
        for (Card card : dealer) {
            sumDealer = sumDealer + card.getValue();
        }
        return sumDealer;
    }

    // MODIFIES: this
    // EFFECTS: places the given card into the dealers hand
    public void placeInDealer(Card card) {
        dealer.add(card);
    }

    // MODIFIES: this
    // EFFECTS: removes every card from the dealer leaving the dealer empty
    public void clear() {
        dealer.clear();
    }

    // EFFECTS: gets the size of dealer
    public int size() {
        return dealer.size();
    }

    // EFFECTS: returns the card at the given index in the dealer
    public Card get(int i) {
        return dealer.get(i);
    }

}
