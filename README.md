# BlackJack

## Matthew Lai

**What Does The Application Do?**   
This application will allow you to play the simple casino game of BlackJack.The player should be able to place bets and 
hope to win against the house. This application will be single player only, against the house. Therefore, this
application should be able to...
- "hit" (draw a card from the deck)
- "stand" (do not draw a card)
- "bet" (place in game currency on your games)
-  keep track of the currency the user has

**Usage And Reasoning**  
The people who will use this application is anyone that wants to play the game of BlackJack. It's a great pass time and 
could help you make a lot of fake currency. This project interests me because I've always wanted to be able to code a
using a language that I have never learned before. I also loved playing BlackJack when I was kid, and it is hard to find 
a good BlackJack application online to play.

## User Stories

- As a user, I want to be able to hit when I need another card
- As a user, I want to be able to stand when I don't want anymore cards
- As a user, I want to have a working deck of cards
- As a user, I want to be able to track my currency and see how much I have left 
- As a user, I want to be able to save my currency
- As a user, I want to be able to load my currency from file

## Phase 4: Task 2

Made the Card constructor in the Card class more robust as well as made the drawFromDeck method in the Deck class more robust.

## Phase 4: Task 3

 - One major thing I noticed is that the hand and dealer classes are exactly the same. Therefore, I wouldn't need one of the classes.
 - Another thing i realized was that the Main class is not needed, the main could just contain all the code in BlackJack.
 - Also, the dealer class and the Hand class could be subclasses of Deck reducing the coupling. 
 - If Hand and Dealer were subclasses of Deck then Card could be a subclass of those, once again reducing coupling.
 - There are many functions that are called and created within the BlackJack class which could be refactored out to increase cohesion.