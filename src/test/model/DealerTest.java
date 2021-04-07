package model;

import exceptions.EmptyDeckException;
import exceptions.SuitNotExistException;
import exceptions.ValueNotExistException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DealerTest {

    @Test
    public void dealerHit() {
        Deck deck = null;
        try {
            deck = new Deck();
        } catch (SuitNotExistException | ValueNotExistException e) {
            fail();
        }
        Dealer dealer = new Dealer();
        try {
            dealer.hit(deck);
            dealer.hit(deck);
        } catch (EmptyDeckException e) {
            fail();
        }
        assertEquals(dealer.getDealerValue(), 3);
        assertEquals(dealer.size(), 2);
        assertEquals(dealer.get(0).getValue(), 1);
        dealer.clear();
        assertEquals(dealer.getDealerValue(), 0);
    }
}
