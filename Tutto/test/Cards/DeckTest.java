package Cards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeckTest {

    @Test
    void singelton() {
        Deck.destroy();
        Deck d1 = Deck.get_instance();
        Deck d2 = Deck.get_instance();
        assertEquals(d1, d2);
    }

    @Test
    void testDraw_57() {
        Deck.destroy();
        Deck d = Deck.get_instance();
        for (int i = 1; i <= 56; i++)
            d.draw();
        d.draw();
        assertEquals(55, d.size());
    }
}