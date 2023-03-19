package Logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player p = new Player("Herbert");

    @Test
    void testGetName() {
        assertEquals("Herbert", p.getName());
    }

    @Test
    void testToString() {
        assertEquals(p.toString(), "Herbert");
    }

    @Test
    void testIsIsWinner() {
        assertFalse(p.isIs_winner());
    }

    @Test
    void TestUpdateIsWinner_v1() {
        p.update_isWinner();
        assertFalse(p.isIs_winner());
    }

    @Test
    void TestUpdateIsWinner_v2() {
        Score player_score = p.getScore();
        player_score.points_add(500);
        player_score.update_score();
        p.update_isWinner();
        assertTrue(p.isIs_winner());
    }

}

