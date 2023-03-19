package Cards;

import Logic.Player;
import Logic.PlayerBase;
import Logic.Score;
import Logic.Turn;

public class Bonus300 extends Card {
    Bonus300() {
        super("Bonus300", 5);
    }

    public void turn(Player current_player, PlayerBase playerBase, Score player_score) {
        Turn.turn_Bonus300(this, player_score);
    }
}
