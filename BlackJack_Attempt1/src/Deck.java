import java.util.Random;

public class Deck {

    private Card[][] deck = new Card[4][13];
    private Card.Suit[] suitsArray = Card.Suit.values();
    private Card.Value[] valueArray = Card.Value.values();

    public Deck() {
        for(int suitIndex = 0; suitIndex < 4; suitIndex ++) {
            for(int valueIndex = 0; valueIndex < 13; valueIndex ++) {
                deck[suitIndex][valueIndex] = new Card(valueArray[valueIndex],
                        suitsArray[suitIndex]);
            }
        }
    }

    public Card giveCard() {
        int suitIndex = randomSuit();
        int valueIndex = randomValue();
        while (deck[suitIndex][valueIndex].getSuit() == Card.Suit.BLANK) {
            suitIndex = randomSuit();
            valueIndex = randomValue();
        }
        deck[suitIndex][valueIndex].setBlank();
        Card current = new Card(valueArray[valueIndex], suitsArray[suitIndex]);
        return current;
    }

    /*
    private Card drawRandomCard() {
        Random rng = new Random();
        int suitIndex = rng.nextInt(4);
        int valueIndex = rng.nextInt(13);
        //remove from deck array permanently
        return deck[suitIndex][valueIndex];
    }
     */

    //returns true if at least 30 cards are not blank
    public boolean deckCheck() {
        int numNotBlank = 0;
        for(int suitIndex = 0; suitIndex < 4; suitIndex ++) {
            for (int valueIndex = 0; valueIndex < 13; valueIndex++) {
                if (deck[suitIndex][valueIndex].getSuit() != Card.Suit.BLANK) {
                    numNotBlank++;
                }
            }
        }
        return numNotBlank >= 30;
    }

    private int randomSuit() {
        Random rng = new Random();
        int suitIndex = rng.nextInt(4);
        return suitIndex;
    }

    private int randomValue() {
        Random rng = new Random();
        int valueIndex = rng.nextInt(13);
        return valueIndex;
    }
}
