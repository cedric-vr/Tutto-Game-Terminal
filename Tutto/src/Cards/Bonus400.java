package Cards;

import Logic.Player;
import Logic.PlayerBase;
import Logic.Score;
import Logic.Turn;

public class Bonus400 extends Card {
    Bonus400() {
        super("Bonus400", 5);
    }

    public void turn(Player current_player, PlayerBase playerBase, Score player_score) {
        Turn.turn_Bonus400(this, player_score);
    }
}
