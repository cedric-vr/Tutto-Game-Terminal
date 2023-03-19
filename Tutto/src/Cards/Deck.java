package Cards;

import java.util.HashMap;

public class Deck {
    private final HashMap<Card, Integer> deck = new HashMap<>();
    private static Deck instance;
    private static boolean is_set = false;

    private Deck() {
        this.setup();
    }

    public static Deck get_instance() {
        // ensure that only one deck instance exists
        if (!is_set) {
            is_set = true;
            instance = new Deck();
        }
        return instance;
    }

    public static void destroy() {
        // destroys the deck instance
        instance = null;
        is_set = false;
    }

    private void setup() {
        // sets up the deck with the corresponding number of card
        deck.put(new Bonus200(), 5);
        deck.put(new Bonus300(), 5);
        deck.put(new Bonus400(), 5);
        deck.put(new Bonus500(), 5);
        deck.put(new Bonus600(), 5);
        deck.put(new Fireworks(), 5);
        deck.put(new Stop(), 10);
        deck.put(new Straight(), 5);
        deck.put(new PlusMinus(), 5);
        deck.put(new x2(), 5);
        deck.put(new Cloverleaf(), 1);
    }

    public int size() {
        // returns the size of the deck
        int size = 0;
        for (int i : deck.values()) {
            size += i;
        }
        return size;
    }

    private int getRandomNumber(int min, int max) {
        // returns a random integer in the range of 1 and the size of the deck
        return (int) ((Math.random() * (max - min)) + min);
    }

    public Card draw() {
        // randomly draws a card from the deck
        int number = this.getRandomNumber(1, this.size());
        int count = 0;

        for (Card c : deck.keySet()) {
            if (this.size() == 0)
                break;

            else if (number <= count + deck.get(c)) {
                int temp = deck.get(c);
                deck.put(c, temp - 1);
                return c;
            } else {
                count += deck.get(c);
            }
        }
        this.setup();
        return draw();
    }
}

