package model;

import exceptions.SuitNotExistException;
import exceptions.ValueNotExistException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class HandTest {

    @Test
    public void testHandHit() {
        Deck deck = new Deck();
        Hand handEmpty = new Hand();
        assertEquals(handEmpty.getHandValue(), 0);
        handEmpty.hit(deck);
        assertEquals(handEmpty.getHandValue(), 1);
    }

    @Test
    public void testHand() {
        Hand hand = new Hand();
        try {
            hand.placeInHand(new Card(2, 12));
            hand.placeInHand(new Card(2, 9));
            assertEquals(hand.getHandValue(), 19);
            assertEquals(hand.size(), 2);
            assertEquals(hand.get(0).getValue(), 10);
            hand.clear();
            assertEquals(hand.getHandValue(), 0);
        } catch (SuitNotExistException | ValueNotExistException e) {
            fail();
        }
    }
}
