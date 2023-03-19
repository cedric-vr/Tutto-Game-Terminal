package Dicing;

import Custom_Exceptions.InvalidDiceToKeepException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dice {
    // handles everything that has to do with dicing

    private static final int max_dices = 6;
    private static final ArrayList<Die> roll_list = new ArrayList<>(max_dices);
    private static final ArrayList<Die> keep_list = new ArrayList<>(max_dices);
    private static final ArrayList<Die> straight_keep_list = new ArrayList<>(max_dices);


    public static boolean has_accomplished_TUTTO() {
        // returns boolean whether a Tutto was accomplished or not
        return roll_list.size() == 0;
    }

    public static ArrayList<Die> get_roll_list() {
        // returns a deep copy of the roll_list
        return copy_arraylist(roll_list);
    }

    public static ArrayList<Die> get_keep_list() {
        // returns a deep copy of the keep_list
        return copy_arraylist(keep_list);
    }

    public static void fill_up_roll_list() {
        // add 6 dice to the roll_list
        empty_roll_list();
        empty_straight_keep_list();

        for (int i = 0; i < max_dices; i++) {
            roll_list.add(new Die());
        }
    }

    public static void empty_keep_list() {
        // empties keep_list
        keep_list.clear();
    }

    public static void empty_roll_list() {
        // empties roll_list
        roll_list.clear();
    }

    private static void empty_straight_keep_list() {
        // empties straight_keep_list
        straight_keep_list.clear();
    }

    public static void roll_dices() {
        // roll dice in roll_list
        for (Die die : roll_list) {
            die.roll_die();
        }
    }

    public static ArrayList<Die> convert_IntList_to_DieList(ArrayList<Integer> intArray) {
        // convert ArrayList<Integer> to ArrayList<Die>

        ArrayList<Die> verify_dice_list = new ArrayList<>();
        for (Integer i : intArray) {
            verify_dice_list.add(new Die(i));
        }
        return verify_dice_list;
    }

    public static boolean dice_possible() {
        // checks whether it is possible to keep a die

        int number_2 = 0;
        int number_3 = 0;
        int number_4 = 0;
        int number_6 = 0;

        for (Die element : roll_list) {
            switch (element.get_die_value()) {
                case 1:
                case 5:
                    return true;
                case 2:
                    number_2++;
                    break;
                case 3:
                    number_3++;
                    break;
                case 4:
                    number_4++;
                    break;
                case 6:
                    number_6++;
                    break;
            }
        }
        return number_2 >= 3 || number_3 >= 3 || number_4 >= 3 || number_6 >= 3;
    }

    public static boolean dice_possible_straight() {
        // checks whether it's possible to keep a die, when one has drawn a Straight card
        if (straight_keep_list.isEmpty()) {
            return true;
        }
        for (Die element : roll_list) {
            if (!(straight_keep_list.contains(element))) {
                return true;
            }
        }
        return false;
    }

    public static void check_user_keep_possible(ArrayList<Die> user_keep_list)
            throws InvalidDiceToKeepException {
        // initialize hashmaps which store the occurrences of the Die values of the user_keep_list and roll_list
        HashMap<Die, Integer> hm_user_keep_list = set_HashMap_Die(user_keep_list);
        HashMap<Die, Integer> hm_roll_list = set_HashMap_Die(roll_list);

        check_HashMap_allowed(hm_user_keep_list, hm_roll_list);

        // iterate through the hm_user_keep_list
        // to check whether the dice kept are allowed to keep
        for (Map.Entry<Die, Integer> entry : hm_user_keep_list.entrySet()) {
            Die current_die = entry.getKey();
            if (current_die.get_die_value() != 1 && current_die.get_die_value() != 5) {
                int chosen_number = entry.getValue();
                if (chosen_number != 3 && chosen_number != 6) {
                    throw new InvalidDiceToKeepException();
                }
            }
        }
    }

    public static void check_user_keep_possible_firework(ArrayList<Die> user_keep_list)
            throws InvalidDiceToKeepException {
        // initialize hashmaps which store the occurrences of the Die values of the user_keep_list and roll_list
        HashMap<Die, Integer> hm_user_keep_list = set_HashMap_Die(user_keep_list);
        HashMap<Die, Integer> hm_roll_list = set_HashMap_Die(roll_list);

        check_HashMap_allowed(hm_user_keep_list, hm_roll_list);

        // iterate through the hm_roll_list to check which dice can be kept
        // and check whether they are actually in the hm_user_keep_list
        for (Map.Entry<Die, Integer> entry : hm_roll_list.entrySet()) {
            Die current_roll_die = entry.getKey();
            int occurrence = entry.getValue();
            if (current_roll_die.get_die_value() != 1 && current_roll_die.get_die_value() != 5) {
                if (occurrence == 6) {
                    if (!hm_user_keep_list.containsKey(current_roll_die) || occurrence != hm_user_keep_list.get(current_roll_die)) {
                        throw new InvalidDiceToKeepException();
                    }
                } else if (occurrence >= 3) {
                    if (!hm_user_keep_list.containsKey(current_roll_die) || hm_user_keep_list.get(current_roll_die) != 3) {
                        throw new InvalidDiceToKeepException();
                    }
                } else {
                    if (hm_user_keep_list.containsKey(current_roll_die)) {
                        throw new InvalidDiceToKeepException();
                    }
                }
            } else {
                if (!hm_user_keep_list.containsKey(current_roll_die) || occurrence != hm_user_keep_list.get(current_roll_die)) {
                    throw new InvalidDiceToKeepException();
                }
            }
        }
    }

    private static void check_HashMap_allowed(HashMap<Die, Integer> hm_user_keep_list, HashMap<Die, Integer> hm_roll_list)
            throws InvalidDiceToKeepException {
        // iterate through the hm_user_keep_list to check whether all keys are also in the hm_roll_list
        // and check whether the values of the hm_user_keep_list aren't larger than in the hm_roll_list
        for (Map.Entry<Die, Integer> entry : hm_user_keep_list.entrySet()) {
            Die current_die = entry.getKey();
            if (!hm_roll_list.containsKey(current_die) || entry.getValue() > hm_roll_list.get(current_die)) {
                throw new InvalidDiceToKeepException();
            }
        }
    }

    public static HashMap<Die, Integer> set_HashMap_Die(ArrayList<Die> list) {
        // creates a HashMap
        HashMap<Die, Integer> map = new HashMap();
        for (Die current_die : list) {
            if (map.containsKey(current_die)) {
                map.put(current_die, map.get(current_die) + 1);
            } else {
                map.put(current_die, 1);
            }
        }
        return map;
    }

    public static void check_user_keep_possible_straight(ArrayList<Die> user_keep_list_straight)
            throws InvalidDiceToKeepException {
        // first make a deep copy of the straight_keep_list
        // iterate through the user_keep_list_straight and add each die to the copy
        // if a die is already in the copy throw an InvalidDiceToKeepExpection
        ArrayList<Die> copy_straight_keep_list = copy_arraylist(straight_keep_list);
        for (Die element : user_keep_list_straight) {
            if (copy_straight_keep_list.contains(element) || !roll_list.contains(element)) {
                throw new InvalidDiceToKeepException();
            } else {
                copy_straight_keep_list.add(element);
            }
        }
    }

    public static void transfer_to_keep(ArrayList<Integer> user_dice_to_keep_list) {
        for (Integer i : user_dice_to_keep_list) {
            move_one_die(i, roll_list, keep_list);
        }
    }

    public static void transfer_to_keep_straight(ArrayList<Integer> user_dice_to_keep_list) {
        // transfers dice from the roll_list to the straight_keep_list
        for (Integer i : user_dice_to_keep_list) {
            move_one_die(i, roll_list, straight_keep_list);
        }
    }

    private static void move_one_die(int n, ArrayList<Die> list, ArrayList<Die> list2) {
        // move one die from an ArrayList to another ArrayList
        // used to prevent moving all dice with a value of e.g. 5
        // instead, only moves one die with value 5

        for (Die d : list) {
            if (d.get_die_value() == n) {
                list2.add(d);
                list.remove(d);
                break;
            }
        }
    }

    private static ArrayList<Die> copy_arraylist(ArrayList<Die> arraylist_to_be_copied) {
        // create a deep copy of an ArrayList<Die>

        ArrayList<Die> arraylist_clone = new ArrayList<>();

        for (Die d : arraylist_to_be_copied) {
            arraylist_clone.add(new Die(d.get_die_value()));
        }

        empty_keep_list();
        return arraylist_clone;
    }


}