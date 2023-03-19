package Logic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

import static Logic.PlayerBase.getNumber_of_players;
import static org.junit.jupiter.api.Assertions.*;

class PlayerBaseTest {

    PlayerBase pb = new PlayerBase(4);

    @Test
    void testPlayerbase_setup() {
        pb.playerbase_setup(true);

        int i = 0;
        for (Player p : pb) {
            assertEquals("Player_" + i, p.getName());
            i++;
        }
    }

    @Test
    void testGetNumber_of_players() {
        assertEquals(4, getNumber_of_players());
    }

    @Test
    void testPlayerbase_score() {
        pb.playerbase_setup(true);

        ArrayList<Player> list2 = pb.get_players_with_highest_score();

        for (Player p : pb) {
            assertTrue(list2.contains(p));
        }
    }

    @Test
    void testIterator() {
        Player p1 = new Player("Bobby");
        for (Player p : pb)
            assertEquals(p, p1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Herbert", "Anton", "Peter"})
    void testName_validation_valid_inputs(String user_inputs) {
        // checks valid user inputs
        assertEquals(user_inputs, PlayerBase.name_validation(user_inputs));
    }

    @ParameterizedTest
    @ValueSource(strings = {" Herbert", ".herbert", "_her28BerTT", "4her_. BERT4", "", " "})
    void testName_validation_invalid_inputs(String user_inputs) {
        // checks invalid user inputs
        assertThrows(IllegalArgumentException.class, () -> PlayerBase.name_validation(user_inputs));
    }
}