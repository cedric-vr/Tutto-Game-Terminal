package Cards;

import Logic.Player;
import Logic.PlayerBase;
import Logic.Score;
import Logic.Turn;

public class x2 extends Card {
    x2() {
        super("x2", 5);
    }

    public void turn(Player current_player, PlayerBase playerBase, Score player_score) {
        Turn.turn_x2(this, player_score);
    }
}
