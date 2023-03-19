package Cards;

import Logic.Player;
import Logic.PlayerBase;
import Logic.Score;
import Logic.Turn;

public class Bonus200 extends Card {
    Bonus200() {
        super("Bonus200", 5);
    }

    public void turn(Player current_player, PlayerBase playerBase, Score player_score) {
        Turn.turn_Bonus200(this, player_score);
    }
}
