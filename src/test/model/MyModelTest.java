package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyModelTest {

    @Test
    public void testDeck() {
        Deck cat = new Deck();

        assertEquals(cat.drawFromDeck().getValue(), 1);
    }

    @Test
    public void testValue() {
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

    @Test
    public void testHand() {
        Hand hand = new Hand();
        hand.placeInHand(new Card(2, 12));
        hand.placeInHand(new Card(2, 9));
        assertEquals(hand.getHandValue(), 19);
    }

    @Test
    public void testDeckSize() {
        Deck deck = new Deck();
        assertEquals(52, deck.size());
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

    @Test
    public void testHit() {
        Deck deck = new Deck();
        Hand handEmpty = new Hand();
        handEmpty.hit(deck);
        assertEquals(handEmpty.getHandValue(), 1);
    }

    @Test
    public void testWinBal() {
        Bank bank = new Bank();
        bank.winOrLost(true);
        assertEquals(1100, bank.getBalance());
    }

    @Test
    public void testLoseBal() {
        Bank bank = new Bank();
        bank.winOrLost(false);
        assertEquals(900, bank.getBalance());
    }

    @Test
    public void testLoseBalNoMoney() {
        Bank bank = new Bank();
        while (bank.getBalance() > 0) {
            bank.winOrLost(false);
        }
        bank.winOrLost(false);
        assertEquals(0, bank.getBalance());
    }

    @Test
    public void dealerHit() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer();
        dealer.hit(deck);
        assertEquals(dealer.getDealerValue(), 1);
    }

}