package Cards;

import Logic.Player;
import Logic.PlayerBase;
import Logic.Score;
import Logic.Turn;

public class Fireworks extends Card {
    Fireworks() {
        super("Fireworks", 5);
    }

    public void turn(Player current_player, PlayerBase playerBase, Score player_score) {
        Turn.turn_firework(this, player_score);
    }
}
