package Cards;

import Logic.Player;
import Logic.PlayerBase;
import Logic.Score;
import Logic.Turn;

public class Straight extends Card {
    Straight() {
        super("Straight", 5);
    }

    public void turn(Player current_player, PlayerBase playerBase, Score player_score) {
        Turn.turn_straight(this, player_score);
    }
}
