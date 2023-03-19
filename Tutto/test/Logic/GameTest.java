package Logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GameTest {
    @Test
    void singleton() {
        Game g1 = Game.get_instance();
        Game g2 = Game.get_instance();
        assertEquals(g1, g2);
    }
}
