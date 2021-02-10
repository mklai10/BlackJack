package model;

// Represents a bank storing, adding, or removing currency
public class Bank {
    private static int balance;

    // EFFECTS: creates a bank a sets the a balance with $1000
    public Bank() {
        balance = 1000;
    }

    // MODIFIES: this
    // EFFECTS: if the boolean is true then add 100 to the balance
    //          if the boolean is false then subtract 100 from the balance
    public void winOrLost(Boolean win) {
        if (win) {
            int winAmount = 100;
            balance = balance + winAmount;
        } else {
            int loseAmount = 100;
            if (balance - loseAmount >= 0) {
                balance = balance - loseAmount;
            }
        }
    }

    // EFFECTS: gets the current balance in the bank
    public int getBalance() {
        return balance;
    }

}
