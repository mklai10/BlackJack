package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.concurrent.TimeUnit;

// BlackJack application
public class BlackJack extends JFrame {
    private final String sep = System.getProperty("file.separator");

    private boolean running;
    private boolean reset;
    private Bank bank;
    private Hand hand;
    private Dealer dealer;
    private Deck deck;
    //    private Scanner scanner;
    private String currentCardsInPlayer;
    private String cardsInHand;
    private String currentCardsInDealer;
    private String cardsInDealer;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/bank.json";

    private JButton playButton;
    private JButton quitButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton hitButton;
    private JButton standButton;
    private JButton continueButton;
    private JLabel cardsInHandLabel;
    private JLabel cardsInDealerLabel;
    private JLabel bustLabel;
    private JLabel balance;
    private JLabel playerWin;
    private JLabel dealerWin;

    // EFFECTS: runs the BlackJack application
    public BlackJack() {
        initializeGame();
        initializeJSwing();
        runBlackJack();
    }

    // MODIFIES: this
    // EFFECTS: initializes the dealer, bank, hand, deck and the fields,
    //          as wel as reader and writer
    private void initializeGame() {
        bank = new Bank("Player Bank");
        deck = new Deck();
        hand = new Hand();
        dealer = new Dealer();
        hand.clear();
        dealer.clear();
        cardsInHand = "";
        currentCardsInPlayer = "";
        cardsInDealer = "";
        currentCardsInDealer = "";
//        scanner = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: initializes JSwing and all buttons, labels, and sets display screen
    private void initializeJSwing() {
        playButton = new JButton("PLAY");
        quitButton = new JButton("QUIT");
        saveButton = new JButton("SAVE");
        loadButton = new JButton("LOAD");
        hitButton = new JButton("HIT");
        standButton = new JButton("STAND");
        continueButton = new JButton("Continue");
        cardsInHandLabel = new JLabel("Player: " + cardsInHand);
        cardsInDealerLabel = new JLabel("Dealer: " + cardsInDealer);
        bustLabel = new JLabel("BUST!");
        balance = new JLabel("Balance: " + bank.getBalance());
        playerWin = new JLabel("PLayer Wins");
        dealerWin = new JLabel("Dealer WIns");
        setDisplayScreen();
    }

    // MODIFIES: this
    // EFFECTS: starts the application
    private void runBlackJack() {
        running = true;
        reset = false;
        startGame();
//        String input;

//        while (running) {

//            input = scanner.next();

//            if (input.equals("q")) {
//                running = false;
//            } else if (input.equals("p")) {
//                startRoundDealer();
//                startRoundPlayer();
//            } else if (input.equals("s")) {
//                saveBank();
//            } else if (input.equals("l")) {
//                loadBank();
//            }
//        }
        System.out.println("See You Next Time");
    }

    // EFFECTS: creates the display screen panel and creates the buttons
    public void setDisplayScreen() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(200, 200));
        JPanel displayScreen = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(displayScreen, BorderLayout.CENTER);
        displayScreen.setVisible(true);
        saveAndLoadButtons();
        createPlayAndQuit();
        hitOrStandButtons();
        continueButton();
        addButtons(displayScreen);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds all buttons to the display screen
    private void addButtons(JPanel displayScreen) {
        displayScreen.add(hitButton);
        displayScreen.add(standButton);
        displayScreen.add(playButton);
        displayScreen.add(quitButton);
        displayScreen.add(saveButton);
        displayScreen.add(loadButton);
        displayScreen.add(cardsInHandLabel);
        displayScreen.add(cardsInDealerLabel);
        displayScreen.add(bustLabel);
        displayScreen.add(balance);
        displayScreen.add(continueButton);
        displayScreen.add(playerWin);
        displayScreen.add(dealerWin);
    }

    // MODIFIES: this
    // EFFECTS: sets up all buttons and labels then starts the round
    private void startGame() {
        playButton.setVisible(true);
        quitButton.setVisible(true);
        saveButton.setVisible(true);
        loadButton.setVisible(true);
        balance.setVisible(true);
        hitButton.setVisible(false);
        standButton.setVisible(false);
        continueButton.setVisible(false);
        cardsInHandLabel.setVisible(false);
        cardsInDealerLabel.setVisible(false);
        playerWin.setVisible(false);
        dealerWin.setVisible(false);
        bustLabel.setVisible(false);
        setEnable();

//        System.out.println("\nWelcome To BlackJack");
//        System.out.println("\tBalance: " + bank.getBalance());
//        System.out.println("\t press \"p\" to play");
//        System.out.println("\t press \"q\" to quit");
//        System.out.println("\t press \"s\" save bank to file");
//        System.out.println("\t press \"l\" load bank from file");

        startRoundDealer();
        startRoundPlayer();

        while (running) {
            resetGameIfBust();
        }
    }

    // MODIFIES: this
    // EFFECTS: sets enables to true for all buttons
    private void setEnable() {
        hitButton.setEnabled(true);
        standButton.setEnabled(true);
        playButton.setEnabled(true);
        quitButton.setEnabled(true);
        loadButton.setEnabled(true);
        saveButton.setEnabled(true);
    }

    // MODIFIES: dealer, hand
    // EFFECTS: if reset is true then clear both hands and rerun blackjack
    public void resetGameIfBust() {
        if (reset) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            running = false;
            hand.clear();
            dealer.clear();
            runBlackJack();
        }
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
            String suit = null;
            suit = printCard.getSuit();
            int val = printCard.getValue();
            String value = String.valueOf(val);
            currentCardsInPlayer = currentCardsInPlayer + " " + suit + value;
        }
        cardsInHand = currentCardsInPlayer;
        cardsInHandLabel.setText("Player: " + cardsInHand);
        System.out.println(cardsInHand);
        currentCardsInPlayer = "";
//        hitOrStandPlayer();
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
            String suit = null;
            suit = printCard.getSuit();
            int val = printCard.getValue();
            String value = String.valueOf(val);
            currentCardsInDealer = currentCardsInDealer + " " + suit + value;
        }
        cardsInDealer = currentCardsInDealer;
        cardsInDealerLabel.setText("Dealer: " + cardsInDealer);

        System.out.println(cardsInDealer);
        currentCardsInDealer = "";
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
//    private void hitOrStandPlayer() {
//        if (hand.getHandValue() > 21) {
//            playerBust();
//        } else {
//            System.out.println("(h)it or (s)tand");
//            String input = scanner.next();
//            if (input.equals("h")) {
//                hitPlayer();
//            } else if (input.equals("s")) {
//                hitOrStandDealer();
//            }
//        }
//    }

    // MODIFIES: this
    // EFFECTS: adds a card to the dealer and prints out the dealer and player's hands
    public void hitDealer() {
        dealer.hit(deck);
        for (int i = dealer.size() - 1; i >= 0; i--) {
            Card printCard = dealer.get(i);
            String suit = null;
            suit = printCard.getSuit();
            int val = printCard.getValue();
            String value = String.valueOf(val);
            currentCardsInDealer = currentCardsInDealer + " " + suit + value;
        }
        cardsInDealer = currentCardsInDealer;
        System.out.println("Dealer: ");
        System.out.println(cardsInDealer);
        cardsInDealerLabel.setText("Dealer: " + cardsInDealer);
        System.out.println("Player: ");
        System.out.println(cardsInHand);
        currentCardsInDealer = "";
//        System.out.println("enter \"n\" to continue");
//        String input = scanner.next();
//        if (input.equals("n")) {
//            hitOrStandDealer();
//        }
    }

    // MODIFIES: this
    // EFFECTS: adds a card to the player and prints out the dealer and player's hands
    public void hitPlayer() {
//        if (hand.getHandValue() > 21) {
//            playerBust();
//        } else {
        hitButton.setEnabled(false);
        hand.hit(deck);
        for (int i = hand.size() - 1; i >= 0; i--) {
            Card printCard = hand.get(i);
            String suit = null;
            suit = printCard.getSuit();
            int val = printCard.getValue();
            String value = String.valueOf(val);
            currentCardsInPlayer = currentCardsInPlayer + " " + suit + value;
        }
        cardsInHand = currentCardsInPlayer;
        System.out.println("Dealer: ");
        System.out.println(cardsInDealer);
        cardsInDealerLabel.setText("Dealer: " + cardsInDealer);
        System.out.println("Player: ");
        System.out.println(cardsInHand);
        cardsInHandLabel.setText("Player: " + cardsInHand);
        currentCardsInPlayer = "";
//            if (hand.getHandValue() > 21) {
//                playerBust();
//            }
//        }
    }

    // MODIFIES: this
    // EFFECTS: causes the dealer to win playing the correct sound and showing right label
    public void standing() {
//        if (dealer.getDealerValue() < hand.getHandValue()) {
//            System.out.println("Player Wins!");
//            bank.winOrLost(true);
//            balance.setText("Balance: " + String.valueOf(bank.getBalance()));
//        } else {
        continueButton.setVisible(false);
        playSound(System.getProperty("user.dir") + sep
                + "resources" + sep + "lost.wav");
        System.out.println("Dealer Wins!");
        bank.winOrLost(false);
        balance.setText("Balance: " + bank.getBalance());
        dealerWin.setVisible(true);
        reset = true;
//        }
    }

    // MODIFIES: this
    // EFFECTS: shows dealer bust, and reset fields
    private void dealerBust() {
        System.out.println("Dealer Bust, You Win!");
        bank.winOrLost(true);
        balance.setText("Balance: " + bank.getBalance());
//        hand.clear();
//        dealer.clear();
        reset = true;
        continueButton.setVisible(false);
        bustLabel.setVisible(true);
        playerWin.setVisible(true);
        playSound(System.getProperty("user.dir") + sep
                + "resources" + sep + "ding.wav");
    }

    // MODIFIES: this
    // EFFECTS: shows player bust, and reset fields
    private void playerBust() {
        if (hand.getHandValue() > 21) {
            hitButton.setEnabled(false);
            standButton.setEnabled(false);
            System.out.println("Bust, You Lose!");
            bank.winOrLost(false);
            balance.setText("Balance: " + bank.getBalance());
            reset = true;
            bustLabel.setVisible(true);
            playSound(System.getProperty("user.dir") + sep
                    + "resources" + sep + "lost.wav");
            dealerWin.setVisible(true);
        } else {
            hitButton.setEnabled(true);
        }
    }

    // MODIFIES: this
    // EFFECTS: saves the workroom to file
    private void saveBank() {
        try {
            jsonWriter.open();
            jsonWriter.write(bank);
            jsonWriter.close();
            System.out.println("Saved " + bank.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
        reset = true;
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadBank() {
        try {
            playButton.setEnabled(false);
            bank = jsonReader.read();
            System.out.println("Loaded " + bank.getName() + " from " + JSON_STORE);
            balance.setText("Balance: " + bank.getBalance());
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        reset = true;
    }


    // EFFECTS: sets the action for the play and quit buttons
    public void createPlayAndQuit() {
        playButton.setActionCommand("play");
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playButton.setVisible(false);
                quitButton.setVisible(false);
                saveButton.setVisible(false);
                loadButton.setVisible(false);
                continueButton.setVisible(false);
                balance.setVisible(false);
                cardsInHandLabel.setVisible(true);
                cardsInDealerLabel.setVisible(true);
                hitButton.setVisible(true);
                standButton.setVisible(true);
            }
        });
        quitButton.setActionCommand("quit");
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }


    // EFFECTS: sets the action for the save and load buttons
    private void saveAndLoadButtons() {
        saveButton.setActionCommand("save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playButton.setEnabled(false);
                quitButton.setEnabled(false);
                loadButton.setEnabled(false);
                saveBank();
                playSound(System.getProperty("user.dir") + sep
                        + "resources" + sep + "ding2.wav");
            }
        });
        loadButton.setActionCommand("load");
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playButton.setEnabled(false);
                quitButton.setEnabled(false);
                saveButton.setEnabled(false);
                loadBank();
                playSound(System.getProperty("user.dir") + sep
                        + "resources" + sep + "ding2.wav");
            }
        });
    }

    // EFFECTS: sets the action for the hit and stand buttons
    private void hitOrStandButtons() {
        hitButton.setActionCommand("hit");
        hitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hitPlayer();
                playerBust();
            }
        });
        hitButton.setVisible(false);
        standButton.setActionCommand("stand");
        standButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hitButton.setEnabled(false);
                standButton.setEnabled(false);
                hitOrStandDealer();
                continueButton.setVisible(true);
            }
        });
        standButton.setVisible(false);
    }

    // EFFECTS: sets the action for the continue button
    private void continueButton() {
        continueButton.setActionCommand("continue");
        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hitOrStandDealer();
            }
        });
        continueButton.setVisible(false);
    }

    // EFFECTS: plays the given sound with called, if file not found then throw exception
    public void playSound(String filepath) {
        InputStream music;
        try {
            music = new FileInputStream(new File(filepath));
            AudioStream audio = new AudioStream(music);
            AudioPlayer.player.start(audio);
        } catch (Exception e) {
            System.out.println("Error file not found");
        }
    }
}




