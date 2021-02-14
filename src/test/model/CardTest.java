package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    public void testCardValue() {
        Deck deck = new Deck();
        Card king = deck.get(12);
        Card jack = deck.get(10);
        Card ten = deck.get(9);
        Card nine = deck.get(8);
        Card two = deck.get(1);
        assertEquals(king.getValue(), 10);
        assertEquals(jack.getValue(), 10);
        assertEquals(ten.getValue(), 10);
        assertEquals(nine.getValue(), 9);
        assertEquals(two.getValue(), 2);
    }

    @Test
    public void testSuit() {
        Deck deck = new Deck();
        Card spades = deck.get(0);
        Card hearts = deck.get(13);
        Card diamond = deck.get(26);
        Card clubs = deck.get(39);
        assertEquals(spades.getSuit(), "S");
        assertEquals(hearts.getSuit(), "H");
        assertEquals(diamond.getSuit(), "D");
        assertEquals(clubs.getSuit(), "C");
    }

    @Test
    public void testNoSuit() {
        Card noSuit = new Card(5, 10);
        assertNotSame("S", noSuit.getSuit());
        assertNotSame("D", noSuit.getSuit());
        assertNotSame("H", noSuit.getSuit());
        assertNotSame("C", noSuit.getSuit());
    }
}