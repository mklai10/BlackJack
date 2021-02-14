package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DealerTest {

    @Test
    public void dealerHit() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer();
        dealer.hit(deck);
        dealer.hit(deck);
        assertEquals(dealer.getDealerValue(), 3);
        assertEquals(dealer.size(), 2);
        assertEquals(dealer.get(0).getValue(), 1);
        dealer.clear();
        assertEquals(dealer.getDealerValue(), 0);
    }
}
