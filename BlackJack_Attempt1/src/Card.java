public class Card {

    public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES, BLANK
    }

    public enum Value {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, BLANK
    }

    private Value value;
    private Suit suit;

    public Card(Value value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    public int getCardValue() {
        if (value == Value.ACE) {
            return 11;
        } if (isFaceCard()) {
            return 10;
        }
        return value.ordinal() + 1;
    }

    private boolean isFaceCard(){
        if(value == Value.JACK || value == Value.QUEEN || value == Value.KING) {
            return true;
        }
        return false;
    }

    public Suit getSuit() {
        return suit;
    }

    public Value getValue() {
        return value;
    }

    public void setBlank() {
        suit = Suit.BLANK;
        value = Value.BLANK;
    }

    public String toString() {
        return value + " OF " + suit;
    }
}
