package Dicing;

import java.util.Objects;

public class Die {

    private static final int MAX = 6;  // maximum die value
    private static final int MIN = 1;  // minimum die value
    private int die_value;  // current value showing on the die

    public Die() {
        die_value = 0;  // initial die value
    }

    public Die(int x) {
        if (x > Die.MAX)
            die_value = Die.MAX;
        else if (x < Die.MIN)
            die_value = Die.MIN;
        else
            die_value = x;
    }

    public int roll_die() {
        // assigns a random integer in the range of MIN and MAX to the die
        die_value = (int) (Math.random() * MAX) + 1;
        return die_value;
    }

    public int get_die_value() {
        // returns die value
        return die_value;
    }

    @Override
    public String toString() {
        // Die representation as String
        return String.valueOf(this.get_die_value());
    }

    @Override
    public boolean equals(Object o) {
        // equals method override so that dies with the same value are equal
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Die die = (Die) o;
        return die_value == die.die_value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(die_value);
    }
}


