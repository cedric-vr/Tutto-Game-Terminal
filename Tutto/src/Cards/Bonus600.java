package Cards;

import Logic.Player;
import Logic.PlayerBase;
import Logic.Score;
import Logic.Turn;

public class Bonus600 extends Card {
    Bonus600() {
        super("Bonus600", 5);
    }

    public void turn(Player current_player, PlayerBase playerBase, Score player_score) {
        Turn.turn_Bonus600(this, player_score);
    }
}
