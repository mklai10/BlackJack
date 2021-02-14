package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class DeckTest {

    @Test
    public void testDeck() {
        Deck deck = new Deck();
        assertEquals(deck.size(), 52);
        assertEquals(deck.drawFromDeck().getValue(), 1);
    }

    @Test
    public void testShuffleDeck() {
        Deck deck = new Deck();
        deck.shuffle();
        Card card1 = deck.get(0);
        assertNotSame(1, card1.getValue() );
    }

    @Test
    public void testDeckEmpty() {
        Deck deck = new Deck();
        deck.clear();
        deck = deck.fillDeckIfEmpty(deck);
        assertEquals(52, deck.size());
    }

    @Test
    public void testDrawFromDeck() {
        Deck deck = new Deck();
        deck.drawFromDeck();
        assertEquals(deck.size(), 51);
    }
}
