package Logic;

public class Player {
    private final String name;
    private final Score score;

    private boolean is_winner;

    public Player(String name) {
        this.name = name;
        this.score = new Score();
        this.is_winner = false;

    }

    public String getName() {
        // returns the name of the player
        return name;
    }

    public Score getScore() {
        // returns the Score object of the player
        return score;
    }

    public void update_isWinner() {
        // updates the isWinner field of the player
        // when his score is equal or higher than the number of points required to win the game
        // or when he achieved 2 Tuttos when he drew a Cloverleaf card
        if (score.getWinningPointsScore() || Turn.get_tutto_counter(2)) {
            is_winner = true;
        }
    }

    public boolean isIs_winner() {
        // returns boolean whether the player is the winner or not
        return is_winner;
    }

    public int getPointScore() {
        // returns the points of the player
        return score.getPoints_score();
    }

    @Override
    public String toString() {
        // player objects are depicted as the name of the player
        return this.name;
    }
}
