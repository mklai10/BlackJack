package model;

import exceptions.SuitNotExistException;
import exceptions.ValueNotExistException;

// Represents the cards in the game with its suit and value
public class Card {
    private final int cardType;
    private final int cardValue;

    // EFFECTS: Creates a card with it suit and number,
    //          11 represents jack, 12 queen, and 13 king
    Card(int suit, int cardNumber) throws SuitNotExistException, ValueNotExistException {
        if (suit > 4 || suit < 1) {
            throw new SuitNotExistException();
        }
        if (cardNumber > 13 || cardNumber < 1) {
            throw new ValueNotExistException();
        }
        this.cardValue = cardNumber;
        cardType = suit;
    }

    // EFFECTS: turns the number suit from a card into the String representation of a suit
    public String getSuit() {
        if (cardType == 1) {
            return "S";
        } else if (cardType == 2) {
            return "H";
        } else if (cardType == 3) {
            return "D";
        } else {
            return "C";
        }
    }

    // REQUIRES: card to have a value between and including 1 and 13
    // EFFECTS: gets the value of the card, if anything above 10 the value becomes 10
    public int getValue() {
        return Math.min(cardValue, 10);
    }
}
