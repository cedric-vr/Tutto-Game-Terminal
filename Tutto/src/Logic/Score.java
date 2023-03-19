package Logic;

import Dicing.Dice;
import Dicing.Die;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Score {
    private int points_score;
    private boolean points_over_win;
    private int temporary_score;

    public Score() {
        points_score = 0;
        points_over_win = false;
        temporary_score = 0;
    }

    public void update_score() {
        // updates the score by adding the points from the temporary score
        points_score += temporary_score;
        temporary_score = 0;
        check_points_over_win();
    }

    public int getPoints_score() {
        // returns the points which a player has
        return points_score;
    }

    public boolean getWinningPointsScore() {
        // returns a boolean which indicates whether the player has more or less points
        // than are required to win the game
        return points_over_win;
    }

    public void temporary_score_zero() {
        // sets the temporary_score to zero, if a player doesn't win any points
        temporary_score = 0;
    }

    public boolean check_points_over_win() {
        // checks whether the score is equal or larger than the points needed to win
        // if yes the points_over_win will be true
        if (points_score >= Game.getWinning_points()) {
            points_over_win = true;
        }
        return points_over_win;
    }

    public void calculate_score(ArrayList<Die> keep_list) {
        // calls the method to set up a HashMap with the dice kept after a roll
        // calls the calculator method to calculate the points
        HashMap<Die, Integer> die_map;
        die_map = Dice.set_HashMap_Die(keep_list);
        calculator(die_map);
    }


    private void calculator(HashMap<Die, Integer> die_map) {
        // loops through the HashMap die_map to call the corresponding methods to calculate the points
        for (Map.Entry<Die, Integer> entry : die_map.entrySet()) {
            Die current_die = entry.getKey();
            int current_key = current_die.get_die_value();
            if (current_key == 1) {
                temporary_score += calculate_1(entry.getValue());
            } else if (current_key == 5) {
                temporary_score += calculate_5(entry.getValue());
            } else if (current_key == 2 || current_key == 3 || current_key == 4 || current_key == 6) {
                temporary_score += calculate_rest_numbers(current_key, entry.getValue());
            }
        }
    }

    private int calculate_1(int amount) {
        // calculates the points for the number of 1s kept
        int rest = amount % 3;
        int points_ones = rest * 100;
        if (amount == 6) {
            points_ones += 2000;
        }
        if (amount < 6 && amount >= 3) {
            points_ones += 1000;
        }
        return points_ones;
    }

    private int calculate_5(int amount) {
        // calculates the points for the number of 5s kept
        int rest = amount % 3;
        int points_fives = rest * 50;
        if (amount == 6) {
            points_fives += 1000;
        }
        if (amount < 6 && amount >= 3) {
            points_fives += 500;
        }
        return points_fives;
    }

    private int calculate_rest_numbers(int value, int amount) {
        // calculates the points for the number of 2s, 3s, 4s or 6s kept
        if (amount == 3) {
            return 100 * value;
        } else if (amount == 6) {
            return 200 * value;
        }
        return 0;
    }


    public void points_add(int n) {
        // adds the bonus points from a bonus card to the temporary score
        temporary_score += n;
    }

    public void points_x2() {
        // calculates x2 of the temporary points
        temporary_score = temporary_score * 2;
    }

    public void points_straight() {
        // adds the points for achieving a straight to the temporary score
        temporary_score += 2000;
    }

    public void points_plus_minus_tutto(PlayerBase players, Score score, Player current_player) {
        // find the player(s) with most points
        // if player_most_points is not equal to the current player
        // player with most points gets deducted 1000 points
        // current player gets a 1000 points added to the temporary score
        ArrayList<Player> players_with_highest_score = players.get_players_with_highest_score();
        for (Player p : players_with_highest_score) {
            Score s_highest = p.getScore();
            if (p != current_player) {
                s_highest.points_score -= 1000;
            }
        }
        temporary_score += 1000;
    }

}


