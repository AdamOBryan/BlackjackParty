package edu.pitt.blackjackParty;


/**
 * @author Adam O'Bryan CS445 - Spring 2016 Assignment 1 RandIndexQueue 
 */

public class Blackjack {

    int decksInShoe = 0;
    int roundsInGame = 0;
    int decks = 0;
    int totalCards = 0;
    int shufflePoint = 0;

    int handsPlayed = 0;
    int dealerWins = 0;
    int playerWins = 0;
    int pushes = 0;
    int dealerBJs = 0;
    int playerBJs = 0;
    Card burnCard;

    int cardsInShoe = 0;
    RandIndexQueue<Card> theShoe;
    int cardsInDiscardPile = 0;
    RandIndexQueue<Card> discardPile;

    int cardsInPlayer = 0;
    RandIndexQueue<Card> playerHand; 
    // 11 is the highest amount of cards possible to get Black Jack
    int cardsInDealer = 0;
    RandIndexQueue<Card> dealerHand; 

//START PROGRAM
    public Blackjack(int Rounds, int Decks) {

        roundsInGame = Rounds;
        decks = Decks;
        totalCards = Decks * 52;
        shufflePoint = totalCards / 4;
        int playerHandValue;
        int dealerHandValue;

        theShoe = new RandIndexQueue(totalCards);
        discardPile = new RandIndexQueue(totalCards);
        playerHand = new RandIndexQueue(11);
        dealerHand = new RandIndexQueue(11);
        fillShoe();
        System.out.println("cardsInShoe" +cardsInShoe +"\n" + "theShoe.size" + theShoe.size());  ///***
        System.out.println(theShoe.toString() + "\n\n");   ////*****
        System.out.println("Starting Blackjack with " + Rounds + " rounds and " + Decks + " decks in the shoe! \n\n");
        // EACH ROUND 
        for (int thisRound = 0; thisRound < roundsInGame; thisRound++) {

            System.out.println("- Round " + (thisRound+1) + " beginning -");
        playerHand.clear();
        dealerHand.clear();
            //MAKE HANDS
            burnCard = drawCard();
            playerHand.addItem(drawCard());
            dealerHand.addItem(drawCard());
            playerHand.addItem(drawCard());
            dealerHand.addItem(drawCard());
            


            playerHandValue = gamblePlayerHand();
            if (playerHandValue == 21) {
                System.out.println("BLACKJACK!");
            }
            dealerHandValue = gambleDealerHand();
            if (dealerHandValue == 21) {
                System.out.println("BLACKJACK!");
            }

            //COMPARE HANDS
            while (playerHandValue < 17) {
                Card theCard = drawCard();
                playerHand.addItem(theCard);
                System.out.println("\nPlayer Hits! \nAnd Gets: "+ theCard.toString());
                playerHandValue = gamblePlayerHand();
            }
            System.out.println("Player Stands!");
            while (dealerHandValue < 17) {
                Card theCard = drawCard();
                dealerHand.addItem(theCard);
                System.out.println("\nDealer Hits! \nAnd Gets: "+ theCard.toString());
                dealerHandValue = gambleDealerHand();
            }
            System.out.println("Dealer Stands");
            
            // DISPLAY HAND VALUES
            System.out.println("\nPlayer Hand Total: " + playerHandValue);
            System.out.println("Dealer Hand Total: " + dealerHandValue );
            
            // CHOOSE THE WINNER
            if (dealerHandValue>21 && playerHandValue>21) {
                System.out.println("It's A Push! \n\n");
                itsAPush();
            }else if (playerHandValue == dealerHandValue) {
                System.out.println("It's A Push! \n\n");
                itsAPush();
            }else if (playerHandValue > dealerHandValue || dealerHandValue > 21) {
                if (playerHandValue < 22) {
                    System.out.println("Player Wins! Hand Total: " + playerHandValue + "\n\n");
                    playerWin();
                }
            }  else {
                System.out.println("Dealer Wins! Hand Total: " + dealerHandValue + "\n\n");
                dealerWins();
            }
            
            
           
            //System.out.println("shoe size = " + theShoe.size() + "\nDiscard Pile size = " + discardPile.size() + "\nShuffle Point is: " + shufflePoint + "\n\n");
            
             //CHECK SHOE FOR SHUFFLE
            if (theShoe.size() < shufflePoint){
                shuffleShoe();
                System.out.println("\n * Shuffling the Shoe in Round " + thisRound + " *\n");
            }

        } // END ROUND LOOP

        // PRINT ROUND STATS
            System.out.println(statString());
    } // End CONSTRUCTOR FOR GAME

    // PLAYER GAMBLE
    public int gamblePlayerHand() {
//System.out.println("Gambler: Player"); ///****
        boolean isThereAOne = false;
        int handValue = 0;

    //get Array Toal
        for (int x = 0; x < playerHand.size(); x++) {
            //for (Card teCard : playerHand){
            Card theCard = playerHand.get(x);
            //System.out.println(theCard.toString()); 
            int cardValue = theCard.value();
            //System.out.println(cardValue); ///****
            if (cardValue == 1) {
                isThereAOne = true;
            }
            handValue += cardValue;

            if (isThereAOne) {   //compare handValue vs handValue+10
                int highValue = handValue + 10;
                if (highValue < 22) {
                    handValue = highValue;
                }
            }
        }

        System.out.println("Player " + playerHand.toString() + ": " + handValue);

        if (handValue > 21) {
            System.out.println("Player BUSTS!!");
        }

        return handValue;

    }

    ;

    // DEALER GAMBLE
       public int gambleDealerHand() {
// System.out.println("GamblerDealer"); ///****

        boolean isThereAOne = false;
        int handValue = 0;

    //get Array Toal
        for (int x = 0; x < dealerHand.size(); x++) {
            Card theCard = dealerHand.get(x);
            int cardValue = theCard.value();
            //System.out.println(cardValue); ///****
            if (cardValue == 1) {
                isThereAOne = true;
            }
            handValue += cardValue;

            if (isThereAOne) {   //compare handValue vs handValue+10
                int highValue = handValue + 10;
                if (highValue < 22) {
                    handValue = highValue;
                }
            }
        }

        System.out.println("Dealer " + dealerHand.toString() + ": " + handValue);

        if (handValue > 21) {
            System.out.println("Dealer BUSTS!!");
        }

        return handValue;

    }

    ;
     
       
 public Card drawCard() {
//System.out.println("drawcard");   //////**
        Card chosenCard = theShoe.endPick();    //////    endPick();
        discardPile.addItem(chosenCard);
        cardsInShoe--;

        return chosenCard;

    }
//returns true if sum is less than 17

    public boolean shouldWeHit(int[] hand) {
        int handTotal = 0;
        for (int x : hand) {
            handTotal += x;
        }
        return handTotal < 17;
    }

//returns string of game stats
    public String statString() {
        String GameStats = "\n"
                + "After " + roundsInGame + " rounds, here are the results:\n"
                + "Dealer Wins: " + dealerWins + " \n"
                + "Player Wins: " + playerWins + " \n"
                + "Pushes: " + pushes + " \n"
                + "------------------------";
        return GameStats;
    }

    private void playerWin() {
        handsPlayed++;
        playerWins++;
    }

    private void itsAPush() {
        handsPlayed++;
        pushes++;

    }

    private void dealerWins() {
        handsPlayed++;
        dealerWins++;
    }

    public void fillShoe() {
    //for(int x = 0; x<decks; x++){
        //   String[] suits = {"Spades", "Clubs", "Diamonds", "Hearts"};
        // String[] ranks = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten","Jack", "Queen", "King", "Ace"};

        /*  for(String s : suits){
         for (String r :ranks){
                        
         Card thCard = new Card(Card.Suits.x, Card.Ranks.y);
                        
         theShoe.addItem(thCard);
         }
                
         }*/
        for (int x = 0; x < decks; x++) {
            System.out.println("starting to add deck " +x);
            for (Card.Suits suit : Card.Suits.values()) {
                for (Card.Ranks rank : Card.Ranks.values()) {
                    Card theCard = new Card(suit, rank);
                    theShoe.addItem(theCard);
                    cardsInShoe++;
                    //System.out.println(theCard);
                }
            }
        }
        shuffleShoe();
    }
    
    public void shuffleShoe(){
                for ( int i = 0; i < discardPile.size(); i++){
                    //Card theCard = discardPile.endPick();
                    theShoe.addItem(discardPile.endPick());
                }                
                theShoe.shuffle();
                
            }

/// MAIN STRING
    public static void main(String[] args) {
        //public Blackjack(int Rounds, int Decks) {
        //  NEEDS TO READ IN FROM COMMAND LINE FOR ACTUAL PROJECT!!!!! 
        //roundsInGame = Rounds;
        //totalCards = Decks * 52;  

        int Rounds = 15; //FOR TESTING
        int Decks = 3; //FOR TESTING
        Blackjack game = new Blackjack(Rounds, Decks);
        
    }

}//end BlackJack Class
