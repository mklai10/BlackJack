package model;

import exceptions.EmptyDeckException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {

    @Test
    public void testDeck() {
        Deck deck = new Deck();
        assertEquals(deck.size(), 52);
        try {
            assertEquals(deck.drawFromDeck().getValue(), 1);
        } catch (EmptyDeckException e) {
            fail();
        }
    }

    @Test
    public void testShuffleDeck() {
        Deck deck = new Deck();
        Deck deckNotShuffled = new Deck();
        deck.shuffle();
        assertNotSame(deck, deckNotShuffled);
    }

    @Test
    public void testDeckEmpty() {
        Deck deck = new Deck();
        deck.clear();
        deck = deck.fillDeckIfEmpty(deck);
        assertEquals(52, deck.size());
    }

    @Test
    public void testDrawFromDeckNotEmpty() {
        Deck deck = new Deck();
        try {
            deck.drawFromDeck();
            assertEquals(deck.size(), 51);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testDrawFromDeckEmpty() {
        Deck deck = new Deck();
        for (int i = 1; i <= 52; i++) {
            try {
                deck.drawFromDeck();
            } catch (EmptyDeckException e) {
                fail();
            }
        }
        assertEquals(0, deck.size());
        try {
            deck.drawFromDeck();
        } catch (EmptyDeckException e) {
            // success
            assertEquals(0, deck.size());
        }
    }
}
