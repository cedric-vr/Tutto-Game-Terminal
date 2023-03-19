package Logic;

import Util.Display;
import Util.Input;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PlayerBase implements Iterable<Player> {
    private final ArrayList<Player> players;
    private static int number_of_players = 0;

    public PlayerBase(int number_of_players) {
        this.players = new ArrayList<>();
        PlayerBase.number_of_players = number_of_players;
    }

    private void add_player(Player p) {
        // adds a player to players (playerbase)
        this.players.add(p);
    }

    private void order_alpha() {
        // sorts players alphabetically
        this.players.sort(Comparator.comparing(Player::getName));
    }

    public void playerbase_setup(boolean auto_names) {
        // sets up the playerbase by getting the name of each player
        for (int i = 0; i < number_of_players; i++) {
            while (true) {
                try {

                    String name;

                    if (auto_names)
                        name = "Player_" + i;

                    else {
                        Display.enter_name(i + 1);
                        name = Input.get_user_input_string();
                    }
                    name_validation(name);
                    Player p = new Player(name);
                    this.add_player(p);
                    break;
                } catch (IllegalArgumentException e) {
                    Display.invalid_name_input();
                }
            }
        }
        this.order_alpha();
    }

    public ArrayList<Player> get_players_with_highest_score() {
        // returns a list off the player(s) with the highest score
        // sorts a copy of the players list by their score
        ArrayList<Player> players_sorted_by_score = new ArrayList<>(players);
        players_sorted_by_score.sort(Comparator.comparing(Player::getPointScore));

        // makes new list with the player with the highest score in it
        ArrayList<Player> players_with_highest_scores = new ArrayList<>();

        //adds all players that have the same highest score
        for (Player p : players_sorted_by_score) {
            if (p.getPointScore() == players_sorted_by_score.get(number_of_players - 1).getPointScore()) {
                players_with_highest_scores.add(p);
            }
        }
        return players_with_highest_scores;
    }

    public static int getNumber_of_players() {
        // returns the number of players
        return number_of_players;
    }

    @Override
    public Iterator<Player> iterator() {
        return this.players.iterator();
    }

    public static String name_validation(String name) {
        // Input validation for player names
        // name must start with a big letter and have at least one character
        // name can have lower/upper case letters, numbers, . and _

        Pattern pattern = Pattern.compile("^[A-Z][a-zA-Z0-9 ._]*$");
        Matcher matcher = pattern.matcher(name);

        if (matcher.find()) {
            return name;
        } else {
            throw new IllegalArgumentException();
        }
    }

}
