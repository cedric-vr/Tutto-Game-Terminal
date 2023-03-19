package Util;

import java.util.Scanner;

public class Input {
    // handles all the user input from the terminal
    private static final Scanner user_input = new Scanner(System.in);

    public static String get_user_input_string() {
        return user_input.nextLine();
    }

}
