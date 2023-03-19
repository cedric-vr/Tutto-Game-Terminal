package Util;

import Cards.Card;
import Dicing.Dice;
import Logic.Player;

public class Display {
    // class that handles all outputs to the terminal

    public static void enter_amount_players() {
        // asks for the number of players
        System.out.println("Enter the number of players:");
    }

    public static void enter_name(int name_counter) {
        // asks the players to enter their names
        System.out.println("Enter the " + name_counter + ". name:");
    }

    public static void enter_winning_points() {
        // asks how many points are necessary to win the game
        System.out.println("Enter the amount of points to win the game:");
    }

    public static void enter_display_or_roll() {
        // asks the player whether he wants to roll the dice
        // or whether he wants to display all current scores
        System.out.println("Enter a 'R' to roll the dice or a 'D' to display the scores:");
    }

    public static void enter_roll_or_end() {
        // asks whether the player wants to continue and roll the dice
        // or whether he wants to end his turn
        System.out.println("Enter a 'R' to roll the dice or a 'E' to end your turn:");
    }

    public static void enter_dice_to_keep() {
        // message to enter dice the player wants to keep
        System.out.println("Enter the dice you want to keep:");
    }

    public static void tutto_message() {
        // message that the player achieved a Tutto
        System.out.println("You accomplished a TUTTO.");
    }

    public static void display_cardType(Card card) {
        // message which card was drawn
        System.out.println("You drew a " + card.toString() + " card.");
    }

    public static void display_winner(Player player) {
        // displays who has won the game
        System.out.println(player.getName() + " won the game!");
    }

    public static void display_scores_decoration() {
        // message decoration which gets printed before the scores are displayed
        System.out.println("------------------------");
        System.out.println("-------- Scores --------");
    }

    public static void display_scores(Player player) {
        // displays name and score of player
        System.out.println(player.getName() + ": " + player.getPointScore());
    }

    public static void display_player_turn(Player player) {
        // displays whose turn it is
        System.out.println("------------------------");
        System.out.println("It's " + player.getName() + "'s turn.");
    }

    public static void end_turn() {
        // displays message when player chooses to end his turn or when no dice can be kept
        System.out.println("Your turn is over.");
    }

    public static void show_roll_list() {
        // displays roll_list
        System.out.println("This is your roll list: " + Dice.get_roll_list());
    }

    public static void show_keep_list() {
        // displays keep_list
        System.out.println("This is your keep list: " + Dice.get_keep_list());
    }

    public static void invalid_name_input() {
        // message when an invalid input was given for a name
        System.out.println("Invalid name input. Name must start with a capital letter.");
    }

    public static void invalid_integer_input() {
        // message when an invalid input for an integer was given
        System.out.println("Invalid integer input.");
    }

    public static void invalid_character_r_de_input() {
        // message when an invalid character was given when a D/E or R were asked
        System.out.println("Invalid character.");
    }

    public static void invalid_dice_to_keep() {
        // message when an invalid dice was input to keep
        System.out.println("Invalid die/dice to keep.");
    }

    public static void invalid_dice_input() {
        // message when an invalid dice was given as an input by a player
        System.out.println("Invalid input.");
    }

    public static void integer_larger_than_zero() {
        // message when a negative integer or 0 is entered for the number of winning points
        System.out.println("Integer must be larger than zero.");
    }

    public static void integer_not_between_two_and_four() {
        // message when a negative integer or 0 is entered for the number of winning points
        System.out.println("Integer must be in range from two to four.");
    }


}
