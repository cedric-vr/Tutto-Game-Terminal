package Cards;

import Logic.Player;
import Logic.PlayerBase;
import Logic.Score;

public class NullCard extends Card {
    NullCard() {
        super("NullCard", 0);
    }

    public void turn(Player current_player, PlayerBase playerBase, Score player_score) {
    }
}
