import java.util.Locale;
import java.util.Scanner;

public class BlackJack {

    //Global Constants
    public static final int MAX_SUITS = 4;
    public static final int MAX_VALUES = 13;
    public static int numGames = 0;
    public static int numGamesWon = 0;
    public static int playerTotal = 0;
    public static int dealerTotal = 0;
    public static final int BLACKJACK = 21;
    public static int numTies = 0;

    public static void main(String args[]) {
        gameRules();
        gameHub();
    }

    //Header text to the game; plays when the program starts
    public static void gameRules() {
        System.out.println("----This program runs a simulation of Blackjack.----\n\n" +
                "*The number of decks is set to one for this program.\n" +
                "*Once a card is selected, it will not be drawn again\n" +
                "until the deck is reconstructed.\n" +
                "*The deck is reconstructed once a game ends.\n" +
                "*Press 'h' or 'H' to hit.\n" +
                "*Press 's' or 'S' to stand.\n" +
                "*The dealer will stand at 17.");
    }

    //runs the game
    public static void gameHub() {
        char keepPlaying = 'y';
        //Deck deck = new Deck();
        while (keepPlaying == 'y' || keepPlaying == 'Y') {
            //deck = deckChecker(deck);
            gamePlay();
            keepPlaying = keepPlaying();
            numGames ++;
        }
        System.out.println("\n-----GAME STATISTICS-----\n " +
                "RECORD: " + numGamesWon + "-" + (numGames - numGamesWon) + "-" +
                numTies + "\n");
    }

    //runs the actual game
    public static void gamePlay() {
        Deck deck= new Deck();
        boolean win = false;
        System.out.println("Welcome to game " + (numGames + 1) + "\n" +
                "You are currently " + numGamesWon + "-" + (numGames - numGamesWon) + "-" + numTies + "\n");
        Hand dealerHand = new Hand(deck);
        Hand playerHand = new Hand(deck);
        System.out.println("\nThe Dealer's Hand is: " + dealerHand.dealerToString());
        System.out.println("Your hand is: " + playerHand.toString() + "\n");
        boolean endGame = playerTurns(playerHand, deck, dealerHand);
        if (!endGame) {
            win = dealerTurns(playerHand, deck, dealerHand);
        }
        if (win) {
            winText();
        } else if ((!endGame) && (dealerHand.getTotal() == playerHand.getTotal())) {
            tieText();
        } else if (!endGame) {
            loseText();
        }

    }

    public static char keepPlaying() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Would you like to keep playing?");
        System.out.print("Type 'Y' or 'y' to play another game: ");
        char input = keyboard.next().toLowerCase().charAt(0);
        keyboard.nextLine();
        return input;
    }

    public static void tieText() {
        System.out.println("\nYou and the dealer split!");
        numTies++;
    }

    public static void loseText() {
        System.out.println("\nAw, you lost!");
    }

    public static void winText() {
        System.out.println("\nCongratulations, you won!");
        numGamesWon++;
    }

    public static boolean dealerTurns(Hand playerHand, Deck deck, Hand dealerHand) {
        int turns = 0;
        System.out.println("\nThe dealer's current hand: " + dealerHand.toString() + "\n");
        while (dealerHand.getTotal() < 17) {
            dealerHand.addHand(deck);
            System.out.println("\nThe dealer's current hand after hitting is: " + dealerHand.toString());
            turns ++;
        }
        if (dealerHand.getTotal() > BLACKJACK) {
            System.out.println("The dealer busts!");
            return true;
        } else if (dealerHand.getTotal() == BLACKJACK && turns == 0) {
            System.out.println("The dealer got a blackjack! How unfortunate!");
            return false;
        } else if (dealerHand.getTotal() < playerHand.getTotal() && playerHand.getTotal() <= BLACKJACK) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean playerTurns(Hand playerHand, Deck deck, Hand dealerHand) {
        boolean keepGoing = true;
        int turns = 0;
        while(keepGoing) {
            if (playerHand.getTotal() == BLACKJACK && turns == 0) {
                System.out.println("Wow you're lucky! You got a Blackjack");
                winText();
                return true;
            }
            boolean hit = hitOrCheck();
            if (hit) {
                playerHand.addHand(deck);
                System.out.println("The Dealer's Hand is: " + dealerHand.dealerToString());
                System.out.println("Your new hand is: " + playerHand.toString());
            } else {
                keepGoing = false;
            }
            if (playerHand.getTotal() > BLACKJACK) {
                loseText();
                return true;
            } else if (playerHand.getTotal() == BLACKJACK) {
                return false;
            }
            turns ++;
        }
        return false;
    }

    public static boolean hitOrCheck() {
        boolean validInput = false;
        String input = null;
        char inputChar;
        while (!validInput) {
            System.out.print("What would you like to do? ");
            Scanner keyboard = new Scanner(System.in);
            input = keyboard.nextLine();
            inputChar = input.charAt(0);
            if ((inputChar != 's') && (inputChar != 'S') && (inputChar != 'H') && (inputChar != 'h')) {
                System.out.println("That is not a valid input.");
            } else if ((input.equals("H")) || (input.equals("h"))) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /*
    public static void hit(Hand playerHand, Deck deck) {
        playerHand.addHand(deck);
    }
     */

    //checks if the deck is less than 30 and if so, reconstructs it
    public static Deck deckChecker(Deck deck) {
        if (!deck.deckCheck()) {
            deck = new Deck();
            System.out.println("New deck has been constructed.\n");
        }
        return deck;
    }
}
