package Cards;

import Logic.Player;
import Logic.PlayerBase;
import Logic.Score;
import Logic.Turn;

public class Cloverleaf extends Card {
    Cloverleaf() {
        super("Cloverleaf", 1);
    }

    public void turn(Player current_player, PlayerBase playerBase, Score player_score) {
        Turn.turn_cloverleaf(this, player_score, current_player);
    }
}
