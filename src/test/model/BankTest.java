package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankTest {

    @Test
    public void testWinBal() {
        Bank bank = new Bank("bank");
        bank.winOrLost(true);
        assertEquals(1100, bank.getBalance());
    }

    @Test
    public void testLoseBal() {
        Bank bank = new Bank("bank");
        bank.winOrLost(false);
        assertEquals(900, bank.getBalance());
    }

    @Test
    public void testLoseBalNoMoney() {
        Bank bank = new Bank("bank");
        while (bank.getBalance() > 0) {
            bank.winOrLost(false);
        }
        bank.winOrLost(false);
        assertEquals(0, bank.getBalance());
    }
}
