package Cards;

import Logic.Player;
import Logic.PlayerBase;
import Logic.Score;
import Logic.Turn;

public class PlusMinus extends Card {
    PlusMinus() {
        super("PlusMinus", 5);
    }

    public void turn(Player current_player, PlayerBase playerBase, Score player_score) {
        Turn.turn_PlusMinus(this, player_score, playerBase, current_player);
    }
}
