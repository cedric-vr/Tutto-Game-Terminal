package Cards;

import Logic.Player;
import Logic.PlayerBase;
import Logic.Score;

public abstract class Card {
    private final String type;
    private final int num_in_deck;

    Card(String type, int num_in_deck) {
        this.type = type;
        this.num_in_deck = num_in_deck;
    }

    public int getNum_in_deck() {
        // returns number of cards of specific type in deck
        return this.num_in_deck;
    }

    @Override
    public String toString() {
        // represents the card object as string
        return this.type;
    }

    public abstract void turn(Player current_player, PlayerBase playerBase, Score player_score);

}
