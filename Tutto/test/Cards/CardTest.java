package Cards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardTest {

    @Test
    void testGetNum_in_deck() {
        Card bonus200 = new Bonus200();
        assertEquals(5, bonus200.getNum_in_deck());
    }

    @Test
    void testToString() {
        Card b200 = new Bonus200();
        assertEquals("Bonus200", b200.toString());
    }
}