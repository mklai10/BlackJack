package model;

import exceptions.EmptyDeckException;
import exceptions.SuitNotExistException;
import exceptions.ValueNotExistException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {

    @Test
    public void testDeck() {
        Deck deck = null;
        try {
            deck = new Deck();
        } catch (SuitNotExistException | ValueNotExistException e) {
            fail();
        }
        assertEquals(deck.size(), 52);
        try {
            assertEquals(deck.drawFromDeck().getValue(), 1);
        } catch (EmptyDeckException e) {
            fail();
        }
    }

    @Test
    public void testShuffleDeck() {
        Deck deck = null;
        try {
            deck = new Deck();
        } catch (SuitNotExistException | ValueNotExistException e) {
            fail();
        }
        Deck deckNotShuffled = null;
        try {
            deckNotShuffled = new Deck();
        } catch (SuitNotExistException | ValueNotExistException e) {
            fail();
        }
        deck.shuffle();
        assertNotSame(deck, deckNotShuffled);
    }

    @Test
    public void testDeckEmpty() {
        Deck deck = null;
        try {
            deck = new Deck();
        } catch (SuitNotExistException | ValueNotExistException e) {
            fail();
        }
        deck.clear();
        try {
            deck = deck.fillDeckIfEmpty(deck);
        } catch (SuitNotExistException | ValueNotExistException e) {
            fail();
        }
        assertEquals(52, deck.size());
    }

    @Test
    public void testDrawFromDeckNotEmpty() {
        Deck deck = null;
        try {
            deck = new Deck();
        } catch (SuitNotExistException | ValueNotExistException e) {
            fail();
        }
        try {
            deck.drawFromDeck();
            assertEquals(deck.size(), 51);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testDrawFromDeckEmpty() {
        Deck deck = null;
        try {
            deck = new Deck();
        } catch (SuitNotExistException | ValueNotExistException e) {
            fail();
        }
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
