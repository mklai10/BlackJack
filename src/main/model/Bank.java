package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a bank storing, adding, or removing currency
public class Bank implements Writable {
    private static int balance;
    private static String name;

    // EFFECTS: creates a bank a sets the a balance with $1000
    public Bank(String name) {
        this.name = name;
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
    public static int getBalance() {
        return balance;
    }

    // EFFECTS: gets the name of the bank
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: changes balance to one in file
    public void changeBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("balance", balance);
        return json;
    }
}
