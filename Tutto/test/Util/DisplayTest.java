package Util;

import Logic.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DisplayTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void testEnter_amount_players() {
        Display.enter_amount_players();
        assertEquals("Enter the number of players:", outputStreamCaptor.toString().trim());
    }

    @Test
    void testEnter_name() {
        Display.enter_name(1);
        assertEquals("Enter the 1. name:", outputStreamCaptor.toString().trim());
    }

    @Test
    void testEnter_winning_points() {
        Display.enter_winning_points();
        assertEquals("Enter the amount of points to win the game:", outputStreamCaptor.toString().trim());
    }

    @Test
    void testEnter_display_or_roll() {
        Display.enter_display_or_roll();
        assertEquals("Enter a 'R' to roll the dice or a 'D' to display the scores:", outputStreamCaptor.toString().trim());
    }

    @Test
    void testEnter_roll_or_end() {
        Display.enter_roll_or_end();
        assertEquals("Enter a 'R' to roll the dice or a 'E' to end your turn:", outputStreamCaptor.toString().trim());
    }

    @Test
    void testTutto_message() {
        Display.tutto_message();
        assertEquals("You accomplished a TUTTO.", outputStreamCaptor.toString().trim());
    }


    @Test
    void testInvalid_dice_input() {
        Display.invalid_dice_input();
        assertEquals("Invalid input.", outputStreamCaptor.toString().trim());
    }

    @Test
    void testInvalid_character_r_de_input() {
        Display.invalid_character_r_de_input();
        assertEquals("Invalid character.", outputStreamCaptor.toString().trim());
    }

    @Test
    void testInvalid_dice_to_keep() {
        Display.invalid_dice_to_keep();
        assertEquals("Invalid die/dice to keep.", outputStreamCaptor.toString().trim());
    }

    @Test
    void testEnter_dice_to_keep() {
        Display.enter_dice_to_keep();
        assertEquals("Enter the dice you want to keep:", outputStreamCaptor.toString().trim());
    }

    @Test
    void testDisplay_winner() {
        Player player = new Player("Herbert");
        Display.display_winner(player);
        assertEquals("Herbert won the game!", outputStreamCaptor.toString().trim());
    }

    @Test
    void testDisplay_scores() {
        Player p1 = new Player("Herbert");
        Display.display_scores(p1);
        assertEquals("Herbert: 0", outputStreamCaptor.toString().trim());
    }

    @Test
    void testEnd_turn() {
        Display.end_turn();
        assertEquals("Your turn is over.", outputStreamCaptor.toString().trim());
    }

    @Test
    void testShow_roll_list() {
        Display.show_roll_list();
        assertEquals("This is your roll list: []", outputStreamCaptor.toString().trim());
    }

    @Test
    void testShow_keep_list() {
        Display.show_keep_list();
        assertEquals("This is your keep list: []", outputStreamCaptor.toString().trim());
    }

    @Test
    void testInvalid_name_input() {
        Display.invalid_name_input();
        assertEquals("Invalid name input. Name must start with a capital letter.", outputStreamCaptor.toString().trim());
    }

    @Test
    void testInvalid_integer_input() {
        Display.invalid_integer_input();
        assertEquals("Invalid integer input.", outputStreamCaptor.toString().trim());
    }

    @Test
    void testInteger_larger_than_zero() {
        Display.integer_larger_than_zero();
        assertEquals("Integer must be larger than zero.", outputStreamCaptor.toString().trim());
    }

    @Test
    void testInteger_not_between_two_and_four() {
        Display.integer_not_between_two_and_four();
        assertEquals("Integer must be in range from two to four.", outputStreamCaptor.toString().trim());
    }

}