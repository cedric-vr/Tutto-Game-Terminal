package Logic;

import Dicing.Die;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoreTest {

    Score score = new Score();

    @Test
    void testScore_is_zero_when_initialized() {
        assertEquals(0, score.getPoints_score());
    }

    @Test
    void testUpdate_score() {
        score.update_score();

        assertEquals(0, score.getPoints_score());
    }

    @Test
    void testGetPoints_score() {
        assertEquals(0, score.getPoints_score());
    }

    @Test
    void testCheck_points_over_win() {
        score.points_add(600);
        score.update_score();
        assertEquals(600, score.getPoints_score());
    }

    @Test
    void TestTemporary_score_zero() {
        score.points_add(100);
        score.temporary_score_zero();
        score.update_score();
        assertEquals(0, score.getPoints_score());
    }

    @Test
    void testCalculate_score_222222() {
        ArrayList<Die> keep_list = new ArrayList<>();
        Die d1 = new Die(2);
        Die d2 = new Die(2);
        Die d3 = new Die(2);
        Die d4 = new Die(2);
        Die d5 = new Die(2);
        Die d6 = new Die(2);

        keep_list.add(d1);
        keep_list.add(d2);
        keep_list.add(d3);
        keep_list.add(d4);
        keep_list.add(d5);
        keep_list.add(d6);

        score.calculate_score(keep_list);
        score.update_score();

        assertEquals(400, score.getPoints_score());
    }

    @Test
    void testCalculate_score_222456() {
        ArrayList<Die> keep_list = new ArrayList<>();
        Die d1 = new Die(2);
        Die d2 = new Die(2);
        Die d3 = new Die(2);
        Die d4 = new Die(4);
        Die d5 = new Die(5);
        Die d6 = new Die(6);

        keep_list.add(d1);
        keep_list.add(d2);
        keep_list.add(d3);
        keep_list.add(d4);
        keep_list.add(d5);
        keep_list.add(d6);

        score.calculate_score(keep_list);
        score.update_score();

        assertEquals(250, score.getPoints_score());
    }

    @Test
    void testCalculate_score_111555() {
        ArrayList<Die> keep_list = new ArrayList<>();
        Die d1 = new Die(1);
        Die d2 = new Die(1);
        Die d3 = new Die(1);
        Die d4 = new Die(5);
        Die d5 = new Die(5);
        Die d6 = new Die(5);

        keep_list.add(d1);
        keep_list.add(d2);
        keep_list.add(d3);
        keep_list.add(d4);
        keep_list.add(d5);
        keep_list.add(d6);

        score.calculate_score(keep_list);
        score.update_score();

        assertEquals(1500, score.getPoints_score());
    }

    @Test
    void testCalculate_score_111111() {
        ArrayList<Die> keep_list = new ArrayList<>();
        Die d1 = new Die(1);
        Die d2 = new Die(1);
        Die d3 = new Die(1);
        Die d4 = new Die(1);
        Die d5 = new Die(1);
        Die d6 = new Die(1);

        keep_list.add(d1);
        keep_list.add(d2);
        keep_list.add(d3);
        keep_list.add(d4);
        keep_list.add(d5);
        keep_list.add(d6);

        score.calculate_score(keep_list);
        score.update_score();

        assertEquals(2000, score.getPoints_score());
    }

    @Test
    void testCalculate_score_555555() {
        ArrayList<Die> keep_list = new ArrayList<>();
        Die d1 = new Die(5);
        Die d2 = new Die(5);
        Die d3 = new Die(5);
        Die d4 = new Die(5);
        Die d5 = new Die(5);
        Die d6 = new Die(5);

        keep_list.add(d1);
        keep_list.add(d2);
        keep_list.add(d3);
        keep_list.add(d4);
        keep_list.add(d5);
        keep_list.add(d6);

        score.calculate_score(keep_list);
        score.update_score();

        assertEquals(1000, score.getPoints_score());
    }

    @Test
    void testCalculate_score_155155() {
        ArrayList<Die> keep_list = new ArrayList<>();
        Die d1 = new Die(1);
        Die d2 = new Die(5);
        Die d3 = new Die(5);
        Die d4 = new Die(1);
        Die d5 = new Die(1);
        Die d6 = new Die(5);

        keep_list.add(d1);
        keep_list.add(d2);
        keep_list.add(d3);
        keep_list.add(d4);
        keep_list.add(d5);
        keep_list.add(d6);

        score.calculate_score(keep_list);
        score.update_score();

        assertEquals(1500, score.getPoints_score());
    }

    @Test
    void testCalculate_score_146235() {
        ArrayList<Die> keep_list = new ArrayList<>();
        Die d1 = new Die(1);
        Die d2 = new Die(4);
        Die d3 = new Die(6);
        Die d4 = new Die(2);
        Die d5 = new Die(3);
        Die d6 = new Die(5);

        keep_list.add(d1);
        keep_list.add(d2);
        keep_list.add(d3);
        keep_list.add(d4);
        keep_list.add(d5);
        keep_list.add(d6);

        score.calculate_score(keep_list);
        score.update_score();

        assertEquals(150, score.getPoints_score());
    }

    @Test
    void testAdd_points() {
        score.points_add(100);
        score.update_score();
        assertEquals(100, score.getPoints_score());
    }

    @Test
    void testPoints_x2() {
        score.points_x2();
        score.update_score();
        assertEquals(0, score.getPoints_score());
    }

    @Test
    void testPoints_straight() {
        score.points_straight();
        score.update_score();
        assertEquals(2000, score.getPoints_score());
    }

    @Test
    void testPoints_plus_minus_tutto_v1() {
        PlayerBase pb = new PlayerBase(2);
        pb.playerbase_setup(true);
        Player p = pb.get_players_with_highest_score().get(0);
        Score score1 = new Score();
        score1.points_add(100);
        score1.update_score();
        score.points_plus_minus_tutto(pb, score1, p);
        assertEquals(0, p.getScore().getPoints_score());
    }

    @Test
    void testPoints_plus_minus_tutto_v2() {
        PlayerBase pb = new PlayerBase(2);
        pb.playerbase_setup(true);
        Score score1 = new Score();

        Player p1 = new Player("Test");
        for (Player p : pb) {
            p.getScore().points_add(100);
            p.getScore().update_score();
            p1 = p;
            break;
        }

        Player p = pb.get_players_with_highest_score().get(0);

        Score score = new Score();
        score.points_plus_minus_tutto(pb, score1, p);
        assertEquals(100, p1.getScore().getPoints_score());
    }

}