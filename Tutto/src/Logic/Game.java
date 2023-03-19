package Logic;

import Custom_Exceptions.IntegerNotBetweenTwoAndFourError;
import Custom_Exceptions.IntegerNotPositiveException;
import Util.Display;
import Util.Input;

import java.util.Objects;

public class Game {
    private static int winning_points;
    private static PlayerBase playerbase;
    private String roll_display;

    private static Game instance;

    private static boolean is_set = false;

    private Game() {
    }

    public static Game get_instance() {
        // ensures that only one instance of a game object exists
        if (!is_set) {
            is_set = true;
            instance = new Game();
        }
        return instance;
    }

    public void start() {
        // calls the setup, loop and end of the game
        setup();
        loop();
        end();

    }

    private void setup() {
        // number of players, instantiate Playerbase and Players and the amount of points which are necessary to win the game
        // handles the game flow
        Display.enter_amount_players();
        int number_of_players = user_input_validation(true);  // get user input & validate
        playerbase = new PlayerBase(number_of_players);
        playerbase.playerbase_setup(false);

        Display.enter_winning_points();
        winning_points = user_input_validation(false);  // get user input & validate

    }

    private void loop() {
        // iterates through the playerbase until the game_over boolean is true
        // each player is asked to choose whether he wants to display all scores or roll the dices

        boolean game_over = false;

        while (!game_over) {
            for (Player player : playerbase) {
                Display.display_player_turn(player);
                Display.enter_display_or_roll();

                roll_display = Turn.user_r_de("D");

                if (Objects.equals(roll_display, "D")) {
                    score_display();

                } else {
                    Turn.make_first_move(player, playerbase);
                    player.update_isWinner();
                }
                if (player.isIs_winner()) {
                    game_over = true;
                    break;
                }
            }
        }
    }

    private static void end() {
        // iterates through the playerbase to find the winner
        // calls the method which handles the display of all scores
        for (Player p : playerbase) {
            if (p.isIs_winner()) {
                Display.display_winner(p);
            }
        }
        score_display();
    }


    private static void score_display() {
        // method which leads to the display of all players
        Display.display_scores_decoration();
        for (Player player_display : playerbase) {
            Display.display_scores(player_display);
        }
    }

    public static int getWinning_points() {
        // returns an integer which is the number of points required to win
        return winning_points;
    }

//

    private Integer user_input_validation(boolean n_players) {
        // ask for user input and validate it
        // returns integer
        while (true) {
            try {
                String n = Input.get_user_input_string();
                int numbers_of_players = Integer.parseInt(n);
                if (n_players) {
                    if (numbers_of_players < 2 || numbers_of_players > 4) throw new IntegerNotBetweenTwoAndFourError();
                } else {
                    if (numbers_of_players < 1) throw new IntegerNotPositiveException();
                }
                return numbers_of_players;

            } catch (IntegerNotBetweenTwoAndFourError e) {
                Display.integer_not_between_two_and_four();
            } catch (IntegerNotPositiveException e) {
                Display.integer_larger_than_zero();
            } catch (Exception e) {
                Display.invalid_integer_input();
            }
        }
    }


}
