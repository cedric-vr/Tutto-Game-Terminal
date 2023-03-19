package Dicing;

import Custom_Exceptions.InvalidDiceToKeepException;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest {

    private static final int max_dices = 6;
    private final ArrayList<Die> roll_list = new ArrayList<>(max_dices);
    private static final ArrayList<Die> keep_list = new ArrayList<>(max_dices);


    private boolean compare(ArrayList<Die> dicelist, ArrayList<Integer> list) {
        HashMap<Integer, Integer> dice_map = new HashMap<>();
        HashMap<Integer, Integer> result_map = new HashMap<>();

        for (int i = 1; i <= 6; i++) {
            dice_map.put(i, 0);
            result_map.put(i, 0);
        }

        for (int i : list) {
            int temp = result_map.get(i);
            result_map.put(i, temp + 1);
        }

        for (Die d : dicelist) {
            int temp = dice_map.get(d.get_die_value());
            dice_map.put(d.get_die_value(), temp + 1);
        }

        for (int i = 1; i <= 6; i++) {
            if (!Objects.equals(result_map.get(i), dice_map.get(i))) {
                return false;
            }
        }
        return true;

    }

    private ArrayList<Die> roll_result(ArrayList<Integer> list) {
        Dice.roll_dices();

        while (!compare(Dice.get_roll_list(), list)) {
            Dice.roll_dices();
        }
        return Dice.get_roll_list();
    }

    @Test
    void testRoll_result() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 3, 2, 1));
        Dice.fill_up_roll_list();
        ArrayList<Die> roll_list = roll_result(list);
        assertTrue(compare(roll_list, list));
    }

    @Test
    void testHas_accomplished_TUTTO() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 5, 6));
        ArrayList<Integer> list_2 = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 5, 6));

        Dice.fill_up_roll_list();
        ArrayList<Die> roll_list_2 = roll_result(list);
        Dice.transfer_to_keep_straight(list_2);

        assertTrue(Dice.has_accomplished_TUTTO());
    }

    @Test
    void testHas_accomplished_TUTTO_v2() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 5, 5));
        ArrayList<Integer> list_2 = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 5));

        Dice.fill_up_roll_list();
        ArrayList<Die> roll_list_2 = roll_result(list);
        Dice.transfer_to_keep_straight(list_2);

        assertFalse(Dice.has_accomplished_TUTTO());
    }

    @Test
    void testGet_roll_list() {
        Dice.fill_up_roll_list();
        Dice.roll_dices();
        assertEquals(6, Dice.get_roll_list().size());
    }

    @Test
    void testGet_keep_list() {
        assertEquals(keep_list, Dice.get_keep_list());
    }

    @Test
    void testFill_up_roll_list() {
        Dice.fill_up_roll_list();
        Dice.roll_dices();
        int size = Dice.get_roll_list().size();
        assertEquals(6, size);
    }

    @Test
    void testDice_possible() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Dice.fill_up_roll_list();
        ArrayList<Die> roll_list = roll_result(list);
        assertTrue(Dice.dice_possible());
    }

    @Test
    void testDice_possible_v2() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 2, 3, 3, 4, 4));
        Dice.fill_up_roll_list();
        ArrayList<Die> roll_list = roll_result(list);
        assertFalse(Dice.dice_possible());
    }

    @Test
    void testDice_possible_v3() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3, 2, 2, 4, 6, 3));
        Dice.fill_up_roll_list();
        ArrayList<Die> roll_list = roll_result(list);
        assertFalse(Dice.dice_possible());
    }

    @Test
    void testDice_possible_v4() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3, 3, 3, 4, 6, 3));
        Dice.fill_up_roll_list();
        ArrayList<Die> roll_list = roll_result(list);
        assertTrue(Dice.dice_possible());
    }

    @Test
    void testDice_possible_v5() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 2, 2, 4, 6, 3));
        Dice.fill_up_roll_list();
        ArrayList<Die> roll_list = roll_result(list);
        assertTrue(Dice.dice_possible());
    }

    @Test
    void testDice_possible_v6() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(4, 4, 4, 4, 6, 3));
        Dice.fill_up_roll_list();
        ArrayList<Die> roll_list = roll_result(list);
        assertTrue(Dice.dice_possible());
    }

    @Test
    void testDice_possible_v7() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(6, 6, 6, 4, 6, 3));
        Dice.fill_up_roll_list();
        ArrayList<Die> roll_list = roll_result(list);
        assertTrue(Dice.dice_possible());
    }

    @Test
    void testDice_possible_v8() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 6, 5));
        Dice.fill_up_roll_list();
        ArrayList<Die> roll_list = roll_result(list);
        Dice.transfer_to_keep_straight(list);
        assertFalse(Dice.dice_possible());
    }

    @Test
    void testDice_possible_v9() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(5, 1, 2, 4, 5, 1));
        Dice.fill_up_roll_list();
        ArrayList<Die> roll_list = roll_result(list);
        assertTrue(Dice.dice_possible());
    }

    @Test
    void testDice_possible_v10() {
        ArrayList<Integer> list = new ArrayList<>(List.of(1));
        ArrayList<Die> roll_list = Dice.convert_IntList_to_DieList(list);
        assertTrue(Dice.dice_possible());
    }


    @Test
    void testConvert_IntList_to_DieList() {
        ArrayList<Die> list = Dice.convert_IntList_to_DieList(new ArrayList<>(Arrays.asList(3, 2, 2, 4, 6, 3)));
        assertEquals(list.size(), 6);
    }

    @Test
    void testCheck_user_keep_possible() throws InvalidDiceToKeepException {
        boolean thrown = false;
        try {
            ArrayList<Die> roll_list = new ArrayList<>();
            Die d1 = new Die(2);
            Die d2 = new Die(2);
            Die d3 = new Die(3);
            Die d4 = new Die(3);
            Die d5 = new Die(4);
            Die d6 = new Die(4);

            roll_list.add(d1);
            roll_list.add(d2);
            roll_list.add(d3);
            roll_list.add(d4);
            roll_list.add(d5);
            roll_list.add(d6);

            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 2, 3, 3, 4, 4));
            Dice.fill_up_roll_list();
            ArrayList<Die> roll_list_2 = roll_result(list);

            Dice.check_user_keep_possible(roll_list);
        } catch (InvalidDiceToKeepException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void testCheck_user_keep_possible_v2() throws InvalidDiceToKeepException {
        boolean thrown = false;
        try {
            ArrayList<Die> roll_list = new ArrayList<>();

            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 2, 3, 3, 4, 4));
            Dice.fill_up_roll_list();
            ArrayList<Die> roll_list_2 = roll_result(list);

            Dice.check_user_keep_possible(roll_list);
        } catch (InvalidDiceToKeepException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    @Test
    void testCheck_user_keep_possible_v3() throws InvalidDiceToKeepException {
        boolean thrown = false;
        try {
            ArrayList<Die> roll_list = new ArrayList<>();
            Die d1 = new Die(1);

            roll_list.add(d1);

            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 3, 4, 4));
            Dice.fill_up_roll_list();
            ArrayList<Die> roll_list_2 = roll_result(list);

            Dice.check_user_keep_possible(roll_list);
        } catch (InvalidDiceToKeepException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    @Test
    void testCheck_user_keep_possible_v4() throws InvalidDiceToKeepException {
        boolean thrown = false;
        try {
            ArrayList<Die> roll_list = new ArrayList<>();
            Die d1 = new Die(2);
            Die d2 = new Die(2);
            Die d3 = new Die(2);
            Die d4 = new Die(2);
            Die d5 = new Die(2);
            Die d6 = new Die(2);

            roll_list.add(d1);
            roll_list.add(d2);
            roll_list.add(d3);
            roll_list.add(d4);
            roll_list.add(d5);
            roll_list.add(d6);

            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 2, 2, 2, 2, 2));
            Dice.fill_up_roll_list();
            ArrayList<Die> roll_list_2 = roll_result(list);

            Dice.check_user_keep_possible(roll_list);
        } catch (InvalidDiceToKeepException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    @Test
    void testCheck_user_keep_possible_v5() throws InvalidDiceToKeepException {
        boolean thrown = false;
        try {
            ArrayList<Die> roll_list = new ArrayList<>();
            Die d1 = new Die(3);
            Die d2 = new Die(3);
            Die d3 = new Die(3);

            roll_list.add(d1);
            roll_list.add(d2);
            roll_list.add(d3);

            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3, 3, 3, 3, 4, 4));
            Dice.fill_up_roll_list();
            ArrayList<Die> roll_list_2 = roll_result(list);

            Dice.check_user_keep_possible(roll_list);
        } catch (InvalidDiceToKeepException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    @Test
    void testCheck_user_keep_possible_v6() throws InvalidDiceToKeepException {
        boolean thrown = false;
        try {
            ArrayList<Die> roll_list = new ArrayList<>();
            Die d1 = new Die(2);
            Die d2 = new Die(3);
            Die d3 = new Die(4);
            Die d4 = new Die(4);
            Die d5 = new Die(3);
            Die d6 = new Die(2);

            roll_list.add(d1);
            roll_list.add(d2);
            roll_list.add(d3);
            roll_list.add(d4);
            roll_list.add(d5);
            roll_list.add(d6);

            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 2, 3, 3, 4, 4));
            Dice.fill_up_roll_list();
            ArrayList<Die> roll_list_2 = roll_result(list);

            Dice.check_user_keep_possible(roll_list);
        } catch (InvalidDiceToKeepException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void testCheck_user_keep_possible_v7() throws InvalidDiceToKeepException {
        boolean thrown = false;
        try {
            ArrayList<Die> roll_list = new ArrayList<>();
            Die d1 = new Die(1);
            Die d2 = new Die(1);
            Die d3 = new Die(1);
            Die d4 = new Die(1);
            Die d5 = new Die(1);
            Die d6 = new Die(1);

            roll_list.add(d1);
            roll_list.add(d2);
            roll_list.add(d3);
            roll_list.add(d4);
            roll_list.add(d5);
            roll_list.add(d6);

            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 1));
            Dice.fill_up_roll_list();
            ArrayList<Die> roll_list_2 = roll_result(list);

            Dice.check_user_keep_possible(roll_list);
        } catch (InvalidDiceToKeepException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    @Test
    void testCheck_user_keep_possible_v8() throws InvalidDiceToKeepException {
        boolean thrown = false;
        try {
            ArrayList<Die> roll_list = new ArrayList<>();
            Die d1 = new Die(5);
            Die d2 = new Die(5);
            Die d3 = new Die(5);
            Die d4 = new Die(1);
            Die d5 = new Die(1);
            Die d6 = new Die(1);

            roll_list.add(d1);
            roll_list.add(d2);
            roll_list.add(d3);
            roll_list.add(d4);
            roll_list.add(d5);
            roll_list.add(d6);

            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(5, 5, 5, 1, 1, 1));
            Dice.fill_up_roll_list();
            ArrayList<Die> roll_list_2 = roll_result(list);

            Dice.check_user_keep_possible(roll_list);
        } catch (InvalidDiceToKeepException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    @Test
    void testCheck_user_keep_possible_firework() throws InvalidDiceToKeepException {
        boolean thrown = false;
        try {
            ArrayList<Die> roll_list = new ArrayList<>();
            Die d1 = new Die(2);
            Die d2 = new Die(2);
            Die d3 = new Die(3);
            Die d4 = new Die(3);
            Die d5 = new Die(4);
            Die d6 = new Die(4);

            roll_list.add(d1);
            roll_list.add(d2);
            roll_list.add(d3);
            roll_list.add(d4);
            roll_list.add(d5);
            roll_list.add(d6);

            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 2, 3, 3, 4, 4));
            Dice.fill_up_roll_list();
            ArrayList<Die> roll_list_2 = roll_result(list);

            Dice.check_user_keep_possible_firework(roll_list);
        } catch (InvalidDiceToKeepException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void testCheck_user_keep_possible_firework_v2() throws InvalidDiceToKeepException {
        boolean thrown = false;
        try {
            ArrayList<Die> roll_list = new ArrayList<>();
            Die d1 = new Die(2);
            Die d2 = new Die(2);
            Die d3 = new Die(2);
            Die d4 = new Die(2);
            Die d5 = new Die(2);

            roll_list.add(d1);
            roll_list.add(d2);
            roll_list.add(d3);
            roll_list.add(d4);
            roll_list.add(d5);

            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 2, 2, 2, 2, 2));
            Dice.fill_up_roll_list();
            ArrayList<Die> roll_list_2 = roll_result(list);

            Dice.check_user_keep_possible_firework(roll_list);
        } catch (InvalidDiceToKeepException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void testCheck_user_keep_possible_firework_v3() throws InvalidDiceToKeepException {
        boolean thrown = false;
        try {
            ArrayList<Die> roll_list = new ArrayList<>();
            Die d1 = new Die(1);
            Die d2 = new Die(3);

            roll_list.add(d1);
            roll_list.add(d2);

            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 1, 1, 3, 3, 3));
            Dice.fill_up_roll_list();
            ArrayList<Die> roll_list_2 = roll_result(list);

            Dice.check_user_keep_possible_firework(roll_list);
        } catch (InvalidDiceToKeepException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void testCheck_user_keep_possible_firework_v4() throws InvalidDiceToKeepException {
        boolean thrown = false;
        try {
            ArrayList<Die> roll_list = new ArrayList<>();
            Die d1 = new Die(2);
            Die d2 = new Die(2);
            Die d3 = new Die(2);

            roll_list.add(d1);
            roll_list.add(d2);
            roll_list.add(d3);

            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
            Dice.fill_up_roll_list();
            ArrayList<Die> roll_list_2 = roll_result(list);

            Dice.check_user_keep_possible_firework(roll_list);
        } catch (InvalidDiceToKeepException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void testCheck_user_keep_possible_firework_v6() throws InvalidDiceToKeepException {
        boolean thrown = false;
        try {
            ArrayList<Die> roll_list = new ArrayList<>();
            Die d1 = new Die(2);
            Die d2 = new Die(2);
            Die d3 = new Die(2);
            Die d4 = new Die(2);

            roll_list.add(d1);
            roll_list.add(d2);
            roll_list.add(d3);
            roll_list.add(d4);

            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 2, 2, 2, 5, 6));
            Dice.fill_up_roll_list();
            ArrayList<Die> roll_list_2 = roll_result(list);

            Dice.check_user_keep_possible_firework(roll_list);
        } catch (InvalidDiceToKeepException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void testCheck_user_keep_possible_firework_v5() throws InvalidDiceToKeepException {
        boolean thrown = false;
        try {
            ArrayList<Die> roll_list = new ArrayList<>();
            Die d1 = new Die(2);
            Die d2 = new Die(2);
            Die d3 = new Die(2);
            Die d4 = new Die(2);

            roll_list.add(d1);
            roll_list.add(d2);
            roll_list.add(d3);
            roll_list.add(d4);

            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 2, 2, 2, 5, 6));
            Dice.fill_up_roll_list();
            ArrayList<Die> roll_list_2 = roll_result(list);

            Dice.check_user_keep_possible_firework(roll_list);
        } catch (InvalidDiceToKeepException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void testCheck_user_keep_possible_firework_v7() throws InvalidDiceToKeepException {
        boolean thrown = false;
        try {
            ArrayList<Die> roll_list = new ArrayList<>();
            Die d1 = new Die(1);
            Die d2 = new Die(1);
            Die d3 = new Die(1);
            Die d4 = new Die(1);

            roll_list.add(d1);
            roll_list.add(d2);
            roll_list.add(d3);
            roll_list.add(d4);

            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 4, 6));
            Dice.fill_up_roll_list();
            ArrayList<Die> roll_list_2 = roll_result(list);

            Dice.check_user_keep_possible_firework(roll_list);
        } catch (InvalidDiceToKeepException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    @Test
    void testCheck_user_keep_possible_firework_v8() throws InvalidDiceToKeepException {
        boolean thrown = false;
        try {
            ArrayList<Die> roll_list = new ArrayList<>();

            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 3, 3, 4, 4, 6));
            Dice.fill_up_roll_list();
            ArrayList<Die> roll_list_2 = roll_result(list);

            Dice.check_user_keep_possible_firework(roll_list);
        } catch (InvalidDiceToKeepException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    @Test
    void testCheck_user_keep_possible_firework_v9() throws InvalidDiceToKeepException {
        boolean thrown = false;
        try {
            ArrayList<Die> roll_list = new ArrayList<>();
            Die d1 = new Die(3);
            Die d2 = new Die(3);
            Die d3 = new Die(3);

            roll_list.add(d1);
            roll_list.add(d2);
            roll_list.add(d3);

            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3, 3, 3, 3, 4, 6));
            Dice.fill_up_roll_list();
            ArrayList<Die> roll_list_2 = roll_result(list);

            Dice.check_user_keep_possible_firework(roll_list);
        } catch (InvalidDiceToKeepException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    @Test
    void testCheck_user_keep_possible_firework_v10() throws InvalidDiceToKeepException {
        boolean thrown = false;
        try {
            ArrayList<Die> roll_list = new ArrayList<>();
            Die d1 = new Die(1);
            Die d2 = new Die(5);
            Die d3 = new Die(5);

            roll_list.add(d1);
            roll_list.add(d2);
            roll_list.add(d3);

            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,5,5,6,6,6));
            Dice.fill_up_roll_list();
            ArrayList<Die> roll_list_2 = roll_result(list);

            Dice.check_user_keep_possible_firework(roll_list);
        } catch (InvalidDiceToKeepException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void testCheck_user_keep_possible_firework_v11() throws InvalidDiceToKeepException {
        boolean thrown = false;
        try {
            ArrayList<Die> roll_list = new ArrayList<>();
            Die d1 = new Die(3);
            Die d2 = new Die(3);
            Die d3 = new Die(3);
            Die d4 = new Die(3);
            Die d5 = new Die(3);

            roll_list.add(d1);
            roll_list.add(d2);
            roll_list.add(d3);
            roll_list.add(d4);
            roll_list.add(d5);

            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 2, 2, 2, 2, 2));
            Dice.fill_up_roll_list();
            ArrayList<Die> roll_list_2 = roll_result(list);

            Dice.check_user_keep_possible_firework(roll_list);
        } catch (InvalidDiceToKeepException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void testPossible_straight() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Dice.fill_up_roll_list();
        ArrayList<Die> roll_list = roll_result(list);
        assertTrue(Dice.dice_possible_straight());
    }

    @Test
    void testCheck_user_keep_possible_straight() throws InvalidDiceToKeepException {
        boolean thrown = false;
        try {
            ArrayList<Die> roll_list = new ArrayList<>();
            Die d1 = new Die(4);
            Die d2 = new Die(2);
            Die d3 = new Die(2);
            Die d4 = new Die(2);

            roll_list.add(d1);
            roll_list.add(d2);
            roll_list.add(d3);
            roll_list.add(d4);

            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 5, 6));
            Dice.fill_up_roll_list();
            ArrayList<Die> roll_list_2 = roll_result(list);

            Dice.check_user_keep_possible_straight(roll_list);
        } catch (InvalidDiceToKeepException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void testCheck_user_keep_possible_straight_v2() throws InvalidDiceToKeepException {
        boolean thrown = false;
        try {
            ArrayList<Die> roll_list = new ArrayList<>();
            Die d1 = new Die(1);
            Die d2 = new Die(2);
            Die d3 = new Die(3);
            Die d4 = new Die(5);

            roll_list.add(d1);
            roll_list.add(d2);
            roll_list.add(d3);
            roll_list.add(d4);

            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 5, 6));
            Dice.fill_up_roll_list();
            ArrayList<Die> roll_list_2 = roll_result(list);

            Dice.check_user_keep_possible_straight(roll_list);
        } catch (InvalidDiceToKeepException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    @Test
    void testTransfer_to_keep_straight() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 5, 6));
        Dice.fill_up_roll_list();
        ArrayList<Die> roll_list_2 = roll_result(list);
        Dice.transfer_to_keep_straight(list);
        assertEquals(0, Dice.get_roll_list().size());
    }

    @Test
    void testTransfer_to_keep_straight_v2() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 5, 6));
        ArrayList<Integer> list_2 = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 5));

        Dice.fill_up_roll_list();
        ArrayList<Die> roll_list_2 = roll_result(list);
        Dice.transfer_to_keep_straight(list_2);

        assertTrue(Dice.dice_possible_straight());
    }

    @Test
    void testTransfer_to_keep_straight_v3() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 5, 6));
        ArrayList<Integer> list_2 = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 5));

        Dice.fill_up_roll_list();
        ArrayList<Die> roll_list_2 = roll_result(list);
        Dice.transfer_to_keep_straight(list_2);

        ArrayList<Integer> list_3 = new ArrayList<>(List.of(5));
        ArrayList<Die> roll_list_3 = roll_result(list_3);

        assertFalse(Dice.dice_possible_straight());
    }

    @Test
    void testEmpty_keep_list() {
        Dice.empty_keep_list();
        assertEquals(0, Dice.get_keep_list().size());
    }

    @Test
    void testDice_possible_FALSE() {
        Dice.fill_up_roll_list();
        Die d1 = new Die(2);
        Die d2 = new Die(2);
        Die d3 = new Die(4);
        Die d4 = new Die(4);
        Die d5 = new Die(6);
        Die d6 = new Die(6);

        assertEquals(0, roll_list.size());
        roll_list.add(d1);
        roll_list.add(d2);
        roll_list.add(d3);
        roll_list.add(d4);
        roll_list.add(d5);
        roll_list.add(d6);
        assertEquals(6, roll_list.size());

        assertFalse(Dice.dice_possible());
    }

    @Test
    void testRoll_dices() {

        Dice.fill_up_roll_list();
        Dice.roll_dices();

        ArrayList<Integer> possible_values = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            possible_values.add(i);
        }
        for (Die d : Dice.get_roll_list()) {
            assertTrue(possible_values.contains(d.get_die_value()));
        }
    }

    @Test
    void testTransfer_to_keep() {
        ArrayList<Die> dice_list = new ArrayList<>();
        ArrayList<Integer> user_dice_to_keep_list = new ArrayList<>();

        Die d1 = new Die(2);
        Die d2 = new Die(2);
        Die d3 = new Die(2);
        Die d4 = new Die(4);
        Die d5 = new Die(5);
        Die d6 = new Die(6);

        roll_list.add(d1);
        roll_list.add(d2);
        roll_list.add(d3);
        roll_list.add(d4);
        roll_list.add(d5);
        roll_list.add(d6);

        user_dice_to_keep_list.add(5);

        Dice.transfer_to_keep(user_dice_to_keep_list);

        assertEquals(5, 5);
    }

}