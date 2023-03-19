package Cards;

import Logic.Player;
import Logic.PlayerBase;
import Logic.Score;
import Logic.Turn;

public class Stop extends Card {
    Stop() {
        super("Stop", 10);
    }

    public void turn(Player current_player, PlayerBase playerBase, Score player_score) {
        Turn.turn_stop(this, player_score);
    }
}
