package Dicing;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DieTest {
    Die d1 = new Die(2);
    Die d2 = new Die(2);
    Die d3 = new Die(5);

    @Test
    void testDie_initialization() {
        assertDoesNotThrow(() -> new Die(1));
        assertDoesNotThrow(() -> new Die(2));
        assertDoesNotThrow(() -> new Die(3));
        assertDoesNotThrow(() -> new Die(4));
        assertDoesNotThrow(() -> new Die(5));
        assertDoesNotThrow(() -> new Die(6));
    }

    @Test
    void testRoll_die() {
        ArrayList<Integer> possible_values = new ArrayList<>();
        // create possible die values: [1, 2, 3, 4, 5, 6]
        for (int i = 1; i <= 6; i++) {
            possible_values.add(i);
        }
        // roll die 100 times to make sure that all values between 1 & 6 are rolled
        for (int i = 0; i < 100; i++) {
            d1.roll_die();
            // check if value is in ArrayList
            assertTrue(possible_values.contains(d1.get_die_value()));
        }
    }

    @Test
    void testGet_die_value() {
        Die d = new Die(6);
        assertEquals(6, d.get_die_value());
    }

    @Test
    void testGet_die_value_v2() {
        Die d = new Die(0);
        assertEquals(1, d.get_die_value());
    }

    @Test
    void testGet_die_value_v3() {
        Die d = new Die(7);
        assertEquals(6, d.get_die_value());
    }

    @Test
    void testToString() {
        assertEquals("5", d3.toString());
    }

    @Test
    void testEquals() {
        assertEquals(d1, d1);
        assertNotEquals(d1, null);
        assertEquals(d1, d2);
        assertNotEquals(d1, d3);
        assertNotEquals(d1, 1);
    }

    @Test
    void testHashCode() {
        assertEquals(d1.hashCode(), d2.hashCode());
    }
}