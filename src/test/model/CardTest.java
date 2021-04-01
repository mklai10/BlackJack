package model;

import exceptions.SuitNotExistException;
import exceptions.ValueNotExistException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
    public void testCardSuitTooLarge() {
        try {
            Card card = new Card(5, 4);
            fail();
        } catch (SuitNotExistException e) {
            // success
        } catch (ValueNotExistException e) {
           fail();
        }
    }

    @Test
    public void testCardSuitTooSmall() {
        try {
            Card card = new Card(0, 4);
            fail();
        } catch (SuitNotExistException e) {
            // success
        } catch (ValueNotExistException e) {
            fail();
        }
    }

    @Test
    public void testCardValueTooLarge() {
        try {
            Card card = new Card(2, 14);
            fail();
        } catch (SuitNotExistException e) {
            fail();
        } catch (ValueNotExistException e) {
            // success
        }
    }

    @Test
    public void testCardValueTooSmall() {
        try {
            Card card = new Card(2, 0);
            fail();
        } catch (SuitNotExistException e) {
            fail();
        } catch (ValueNotExistException e) {
            // success
        }
    }

    @Test
    public void testCardValueAndSuitNotExist() {
        try {
            Card card = new Card(5, 14);
            fail();
        } catch (SuitNotExistException e) {
            // success
        } catch (ValueNotExistException e) {
            fail();
        }
    }
}