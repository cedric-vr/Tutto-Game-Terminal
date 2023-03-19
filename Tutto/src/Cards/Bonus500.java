package Cards;

import Logic.Player;
import Logic.PlayerBase;
import Logic.Score;
import Logic.Turn;

public class Bonus500 extends Card {
    Bonus500() {
        super("Bonus500", 5);
    }

    public void turn(Player current_player, PlayerBase playerBase, Score player_score) {
        Turn.turn_Bonus500(this, player_score);
    }
}
