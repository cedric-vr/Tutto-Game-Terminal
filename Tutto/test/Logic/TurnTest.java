package Logic;

import Custom_Exceptions.InvalidCharacterException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TurnTest {

    @Test
    void testUser_r_de_R() {
        // test if "R" is returned
        // simulate user input "R"
        String user_input = String.format("R%s", System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(user_input.getBytes());
        System.setIn(bais);

        assertEquals("R", Turn.user_r_de("E"));
    }

    @Test
    void testUser_input_r_de_validation_RE() throws InvalidCharacterException {
        // check if regex for "R" or "E" is activated
        assertEquals("R", Turn.user_input_r_de_validation("R", "E"));
        assertEquals("E", Turn.user_input_r_de_validation("E", "E"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"r", "e", "1", "Rr", "Ee", " ", ""})
    void testUser_input_r_de_validation_not_RE(String false_user_inputs) {
        // check if InvalidCharacterException is thrown for invalid inputs
        assertThrows(InvalidCharacterException.class,
                () -> Turn.user_input_r_de_validation(false_user_inputs, "E"));
    }

    @Test
    void testValidate_input_transfer_dice_from_roll_to_keep_correct_input() {
        // check if valid user inputs results in expected intArray
        String user_input = "5,5";
        ArrayList<Integer> int_Array = new ArrayList<>();
        int_Array.add(5);
        int_Array.add(5);
        assertEquals(int_Array, Turn.validate_input_transfer_dice_from_roll_to_keep(user_input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"5,5,", "", "7", "sfd", "5,99", " "})
    void testValidate_input_transfer_dice_from_roll_to_keep_false_input(String user_inputs) {
        // check if IllegalArgumentException is thrown for invalid inputs
        assertThrows(IllegalArgumentException.class, () ->
                Turn.validate_input_transfer_dice_from_roll_to_keep(user_inputs));
    }

    @Test
    void testGet_Tutto_counter() {
        assertFalse(Turn.get_tutto_counter(2));
    }

    @Test
    void testGet_Tutto_counter_v2() {
        assertTrue(Turn.get_tutto_counter(0));
    }

}