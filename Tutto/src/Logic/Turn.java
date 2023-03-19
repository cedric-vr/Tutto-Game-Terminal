package Logic;

import Cards.*;
import Custom_Exceptions.InvalidCharacterException;
import Custom_Exceptions.InvalidDiceToKeepException;
import Dicing.Dice;
import Util.Display;
import Util.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Turn {

    private static final Deck deck = Deck.get_instance();
    private static PlayerBase playerbase;
    private static Player current_player;
    private static int tutto_counter;

    public static void make_first_move(Player player, PlayerBase base_of_players) {
        // assigns static variables playerbase and current_player
        // calls the make_move method
        playerbase = base_of_players;
        current_player = player;
        Score playerscore = player.getScore();
        make_move(playerscore);
    }


    public static void make_move(Score playerscore) {
        // draws a card
        tutto_counter = 0;
        Card drawnCard = deck.draw();
        Display.display_cardType(drawnCard);
        drawnCard.turn(current_player, playerbase, playerscore);
    }

    private static void turn_bonus(int bonus, Score player_score) {
        // handles the sequence of events of a bonus card
        if (card_loop(player_score)) {
            player_score.points_add(bonus);
            card_tutto(player_score);
        }
    }

    public static void turn_Bonus200(Bonus200 card, Score player_score) {
        // turn when a Bonus200 card was drawn
        turn_bonus(200, player_score);

    }

    public static void turn_Bonus300(Bonus300 card, Score player_score) {
        // turn when a Bonus300 card was drawn
        turn_bonus(300, player_score);
    }

    public static void turn_Bonus400(Bonus400 card, Score player_score) {
        // turn when a Bonus400 card was drawn
        turn_bonus(400, player_score);
    }

    public static void turn_Bonus500(Bonus500 card, Score player_score) {
        // turn when a Bonus500 card was drawn
        turn_bonus(500, player_score);
    }

    public static void turn_Bonus600(Bonus600 card, Score player_score) {
        // turn when a Bonus600 card was drawn
        turn_bonus(600, player_score);
    }

    public static void turn_x2(x2 card, Score player_score) {
        // turn when a x2 card was drawn
        if (card_loop(player_score)) {
            player_score.points_x2();
            card_tutto(player_score);
        }
    }

    public static void turn_stop(Stop card, Score player_score) {
        // turn when a stop card is drawn
        // ends the turn of the player
        end_move(player_score);
    }

    public static void turn_firework(Fireworks card, Score player_score) {
        // turn when a fireworks card was drawn
        // after each dice roll the number of points get calculated
        // turn ends only if a null was rolled
        Dice.fill_up_roll_list();
        while (true) {
            Dice.roll_dices();
            Display.show_roll_list();
            if (!Dice.dice_possible()) {
                player_score.update_score();
                Display.end_turn();
                return;
            }
            turn_dice_to_keep_firework();
            player_score.calculate_score(Dice.get_keep_list());
            if (Dice.has_accomplished_TUTTO()) {
                Dice.fill_up_roll_list();
            }
        }
    }

    public static void turn_PlusMinus(PlusMinus card, Score player_score, PlayerBase playerbase, Player current_player) {
        // turn when a PlusMinus card was drawn
        Dice.fill_up_roll_list();
        while (true) {
            Dice.roll_dices();
            Display.show_roll_list();
            if (!Dice.dice_possible()) {
                end_move(player_score);
                return;
            }

            turn_dice_to_keep();
            if (Dice.has_accomplished_TUTTO()) {
                player_score.points_plus_minus_tutto(playerbase, player_score, current_player);
                card_tutto(player_score);
                return;
            }
        }
    }

    public static void turn_cloverleaf(Cloverleaf card, Score player_score, Player current_player) {
        // method when a cloverleaf card was drawn
        // a Tutto_counter keeps track of the number of tuttos which were achieved
        Dice.fill_up_roll_list();
        while (true) {
            Dice.roll_dices();
            Display.show_roll_list();
            if (!Dice.dice_possible()) {
                end_move(player_score);
                return;
            }
            turn_dice_to_keep();
            if (Dice.has_accomplished_TUTTO()) {
                tutto_counter += 1;
                Dice.fill_up_roll_list();
            }
            if (tutto_counter == 2) {
                return;
            }
        }
    }

    public static boolean get_tutto_counter(int n) {
        // returns whether n tuttos where scored when a cloverleaf card was drawn
        return tutto_counter == n;
    }

    public static void turn_straight(Straight card, Score player_score) {
        // turn when a straight card was drawn
        Dice.fill_up_roll_list();

        while (true) {
            Dice.roll_dices();
            Display.show_roll_list();
            if (!Dice.dice_possible_straight()) {
                end_move(player_score);
                return;
            }
            turn_dice_to_keep_straight();

            if (Dice.has_accomplished_TUTTO()) {
                player_score.points_straight();
                card_tutto(player_score);
                return;
            }
        }
    }

    private static boolean card_loop(Score player_score) {
        // method which contains the loop which happens when a Bonus or x2 card was drawn
        Dice.fill_up_roll_list();

        while (true) {
            Dice.roll_dices();
            Display.show_roll_list();

            if (!Dice.dice_possible()) {
                end_move(player_score);
                return false;
            }

            turn_dice_to_keep();
            player_score.calculate_score(Dice.get_keep_list());

            if (Dice.has_accomplished_TUTTO()) {
                return true;
            }

            Display.enter_roll_or_end();
            String roll_end = user_r_de("E");

            if (Objects.equals(roll_end, "R")) {
                continue;
            } else if (Objects.equals(roll_end, "E")) {
                player_score.update_score();
                Display.end_turn();
                return false;
            }
        }
    }

    private static void card_tutto(Score player_score) {
        // method when a Tutto was accomplished
        // Player can choose whether he wants to draw another card or end his turn
        Display.tutto_message();
        Display.enter_roll_or_end();
        String roll_endTurn = user_r_de("E");
        if (Objects.equals(roll_endTurn, "R")) {
            make_move(player_score);
        } else if (Objects.equals(roll_endTurn, "E")) {
            player_score.update_score();
            Display.end_turn();
        }
    }

    private static void end_move(Score player_score) {
        // method when it wasn't possible to keep any dice or when a stop card was drawn
        // calls the method to set the temporary score to 0
        player_score.temporary_score_zero();
        Display.end_turn();
    }

    public static void turn_dice_to_keep() {
        // get user input for dice to keep
        // and validate input

        while (true) {
            Display.enter_dice_to_keep();
            try {
                String s = Input.get_user_input_string();
                ArrayList<Integer> user_dice_to_keep_list = validate_input_transfer_dice_from_roll_to_keep(s);
                Dice.check_user_keep_possible(Dice.convert_IntList_to_DieList(user_dice_to_keep_list));
                Dice.transfer_to_keep(user_dice_to_keep_list);
                break;
            } catch (IllegalArgumentException e) {
                Display.invalid_dice_input();
            } catch (InvalidDiceToKeepException e) {
                Display.invalid_dice_to_keep();
            }
        }
    }

    public static void turn_dice_to_keep_firework() {
        // get user input for dice to keep when a firework card was drawn
        // and validate input

        while (true) {
            Display.enter_dice_to_keep();
            try {
                String s = Input.get_user_input_string();
                ArrayList<Integer> user_dice_to_keep_list = validate_input_transfer_dice_from_roll_to_keep(s);
                Dice.check_user_keep_possible_firework(Dice.convert_IntList_to_DieList(user_dice_to_keep_list));
                Dice.transfer_to_keep(user_dice_to_keep_list);
                break;
            } catch (IllegalArgumentException e) {
                Display.invalid_dice_input();
            } catch (InvalidDiceToKeepException e) {
                Display.invalid_dice_to_keep();
            }
        }
    }

    public static void turn_dice_to_keep_straight() {
        // get user input for dice to keep when a straight card was drawn
        // and validate input

        while (true) {
            Display.enter_dice_to_keep();
            try {
                String s = Input.get_user_input_string();
                ArrayList<Integer> user_dice_to_keep_list = validate_input_transfer_dice_from_roll_to_keep(s);
                Dice.check_user_keep_possible_straight(Dice.convert_IntList_to_DieList(user_dice_to_keep_list));
                Dice.transfer_to_keep_straight(user_dice_to_keep_list);
                break;
            } catch (IllegalArgumentException e) {
                Display.invalid_dice_input();
            } catch (InvalidDiceToKeepException e) {
                Display.invalid_dice_to_keep();
            }
        }
    }

    public static String user_r_de(String de) {
        // get user input for R (roll dice again) or D (display the current scores of all players) or E (end turn)

        while (true) {
            try {
                String user_input_r_de = Input.get_user_input_string();
                user_input_r_de_validation(user_input_r_de, de);
                return user_input_r_de;
            } catch (InvalidCharacterException e) {
                Display.invalid_character_r_de_input();
            }
        }
    }

    public static String user_input_r_de_validation(String user_input_r_d, String character) throws InvalidCharacterException {
        // input validation for R/D/E input
        String s = "^[R" + character + "]$";
        Pattern pattern = Pattern.compile(s);
        Matcher matcher = pattern.matcher(user_input_r_d);

        if (matcher.find()) {
            return user_input_r_d;
        } else {
            throw new InvalidCharacterException();
        }
    }

    public static ArrayList<Integer> validate_input_transfer_dice_from_roll_to_keep(String user_dice_to_keep_list) {
        // create list of which dice user wants to keep
        // after rolling the dice, the user can see e.g. [1, 2, 5, 4, 4, 1]
        // by typing 1, 5 he will keep the dice 1 and 5

        Pattern pattern = Pattern.compile("^((\\b([1-6]),*\\b)*)$");
        Matcher matcher = pattern.matcher(user_dice_to_keep_list);

        if (matcher.find()) {
            List<String> strArray = Arrays.asList(user_dice_to_keep_list.split(","));
            ArrayList<Integer> intArray = new ArrayList<>(strArray.size());

            for (String s : strArray) {
                intArray.add(Integer.valueOf(s));
            }
            return intArray;
        } else {
            throw new IllegalArgumentException();
        }
    }


}