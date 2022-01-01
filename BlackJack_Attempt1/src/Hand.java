import java.util.ArrayList;
import java.util.Arrays;

public class Hand {

    private ArrayList<Card> cards = new ArrayList<Card>();
    private final int STARTING_CARDS = 2;
    private int maxIndex = 1;

    public Hand(Deck deck) {
        for(int currentDrawn = 0; currentDrawn < STARTING_CARDS; currentDrawn ++) {
            cards.add(deck.giveCard());
        }
    }

    public int getTotal() {
        int total = 0;
        for(int index = 0; index <= maxIndex; index++) {
            total += cards.get(index).getCardValue();
        }
        return total;
    }

    public void addHand(Deck deck) {
        cards.add(deck.giveCard());
        maxIndex ++;
    }

    public String dealerToString() {
        return "[HIDDEN, " + cards.get(1) + "]";
    }

    public String toString() {
        return cards.toString();
    }
}
