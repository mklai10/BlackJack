package ui;

import model.*;

import java.util.Scanner;

// BlackJack application
public class BlackJack {
    private Boolean running;
    private Bank bank;
    private Hand hand;
    private Dealer dealer;
    private Deck deck;
    private Scanner scanner;
    private String currentCardsInPlayer;
    private String cardsInHand;
    private String currentCardsInDealer;
    private String cardsInDealer;
    private Boolean dealerDone;

    // EFFECTS: runs the BlackJack application
    public BlackJack() {
        runBlackJack();
    }

    // MODIFIES: this
    // EFFECTS: starts the application, and processes the given input
    private void runBlackJack() {
        running = true;
        String input;

        initialize();

        while (running) {
            startGame();
            input = scanner.next();

            if (input.equals("q")) {
                running = false;
            } else if (input.equals("p")) {
                startRoundDealer();
                startRoundPlayer();
            }
        }
        System.out.println("See You Next Time");
    }

    // EFFECTS: displays the menu of the game
    private void startGame() {
        System.out.println("\nWelcome To BlackJack");
        System.out.println("\tBalance: " + bank.getBalance());
        System.out.println("\t press \"p\" to play");
        System.out.println("\t press \"q\" to quit");
    }

    // MODIFIES: this
    // EFFECTS: initializes the the dealer, bank, hand, deck and the fields
    private void initialize() {
        bank = new Bank();
        deck = new Deck();
        hand = new Hand();
        dealer = new Dealer();
        hand.clear();
        dealer.clear();
        cardsInHand = "";
        currentCardsInPlayer = "";
        cardsInDealer = "";
        currentCardsInDealer = "";
        dealerDone = false;
        scanner = new Scanner(System.in);
    }

    // MODIFIES: this
    // EFFECTS: starts the round for the dealer and displays it's cards
    private void startRoundDealer() {
        deck.shuffle();
        dealer.hit(deck);
        dealer.hit(deck);
        System.out.println("Dealer: ");
        for (int i = dealer.size() - 1; i >= 0; i--) {
            Card printCard = dealer.get(i);
            String suit = printCard.getSuit();
            int val = printCard.getValue();
            String value = String.valueOf(val);
            currentCardsInDealer = currentCardsInDealer + " " + suit + value;
        }
        cardsInDealer = currentCardsInDealer;
        System.out.println(cardsInDealer);
        currentCardsInDealer = "";
    }

    // MODIFIES: this
    // EFFECTS: starts the round for the player and displays it's cards
    private void startRoundPlayer() {
        deck.shuffle();
        hand.hit(deck);
        hand.hit(deck);
        System.out.println("Player: ");
        for (int i = hand.size() - 1; i >= 0; i--) {
            Card printCard = hand.get(i);
            String suit = printCard.getSuit();
            int val = printCard.getValue();
            String value = String.valueOf(val);
            currentCardsInPlayer = currentCardsInPlayer + " " + suit + value;
        }
        cardsInHand = currentCardsInPlayer;
        System.out.println(cardsInHand);
        currentCardsInPlayer = "";
        hitOrStandPlayer();
    }

    // MODIFIES: this
    // EFFECTS: decides whether the dealer should hit or stand
    private void hitOrStandDealer() {
        if (dealer.getDealerValue() > 21) {
            dealerBust();
        } else {
            if (dealer.getDealerValue() < hand.getHandValue()) {
                hitDealer();
            } else {
                standing();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: decides whether the dealer should hit or stand
    private void hitOrStandPlayer() {
        if (hand.getHandValue() > 21) {
            playerBust();
        } else {
            System.out.println("(h)it or (s)tand");
            String input = scanner.next();
            if (input.equals("h")) {
                hitPlayer();
            } else if (input.equals("s")) {
                hitOrStandDealer();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a card to the dealer and prints out the dealer and player's hands
    public void hitDealer() {
        dealer.hit(deck);
        for (int i = dealer.size() - 1; i >= 0; i--) {
            Card printCard = dealer.get(i);
            String suit = printCard.getSuit();
            int val = printCard.getValue();
            String value = String.valueOf(val);
            currentCardsInDealer = currentCardsInDealer + " " + suit + value;
        }
        cardsInDealer = currentCardsInDealer;
        System.out.println("Dealer: ");
        System.out.println(cardsInDealer);
        System.out.println("Player: ");
        System.out.println(cardsInHand);
        currentCardsInDealer = "";
        System.out.println("enter \"n\" to continue");
        String input = scanner.next();
        if (input.equals("n")) {
            hitOrStandDealer();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a card to the player and prints out the dealer and player's hands
    public void hitPlayer() {
        hand.hit(deck);
        for (int i = hand.size() - 1; i >= 0; i--) {
            Card printCard = hand.get(i);
            String suit = printCard.getSuit();
            int val = printCard.getValue();
            String value = String.valueOf(val);
            currentCardsInPlayer = currentCardsInPlayer + " " + suit + value;
        }
        cardsInHand = currentCardsInPlayer;
        System.out.println("Dealer: ");
        System.out.println(cardsInDealer);
        System.out.println("Player: ");
        System.out.println(cardsInHand);
        currentCardsInPlayer = "";
        hitOrStandPlayer();
    }

    // MODIFIES: this
    // EFFECTS: decides whether the player or dealer wins
    public void standing() {
        if (dealer.getDealerValue() < hand.getHandValue()) {
            System.out.println("Player Wins!");
            bank.winOrLost(true);
        } else {
            System.out.println("Dealer Wins!");
            bank.winOrLost(false);
        }
        hand.clear();
        dealer.clear();
    }

    // MODIFIES: this
    // EFFECTS: prints out dealer wins, and reset fields
    private void dealerBust() {
        System.out.println("Dealer Bust, You Win!");
        bank.winOrLost(true);
        hand.clear();
        dealer.clear();
    }

    // MODIFIES: this
    // EFFECTS: prints out player wins, and reset fields
    private void playerBust() {
        System.out.println("Bust, You Lose!");
        bank.winOrLost(false);
        hand.clear();
        dealer.clear();
    }

}
