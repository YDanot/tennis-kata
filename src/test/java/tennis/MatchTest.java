package tennis;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static tennis.MatchTestDSL.*;


public class MatchTest {

    @Test
    public void should_begin_0_0() {
        given_a_new_classic_match();
        then_score_should_be(
                "| 0 - 00",
                "| 0 - 00");
    }

    @Test
    public void should_be_0_0_15_0_when_player_1_wins_first_point() {
        given_a_new_classic_match();

        when_player_1_win_a_point();

        then_score_should_be(
                "| 0 - 15",
                "| 0 - 00");
    }

    @Test
    public void should_be_0_0_30_0_when_player_1_wins_2_first_point() {
        given_a_classic_match_with_score(
                "| 0 - 15",
                "| 0 - 00");

        when_player_1_win_a_point();

        then_score_should_be(
                "| 0 - 30",
                "| 0 - 00");
    }

    @Test
    public void should_be_0_0_40_0_when_player_1_wins_3_first_point() {
        given_a_classic_match_with_score(
                "| 0 - 30",
                "| 0 - 00");

        when_player_1_win_a_point();

        then_score_should_be(
                "| 0 - 40",
                "| 0 - 00");
    }

    @Test
    public void should_be_0_0_40_AD_when_player_1_wins_3_first_points_and_player2_wins_4_last() {
        given_a_classic_match_with_score(
                "| 0 - 40",
                "| 0 - 40");

        when_player_2_win_a_point();

        then_score_should_be(
                "| 0 - 40",
                "| 0 - AD");
    }

    @Test
    public void should_start_a_new_game_when_first_game_is_over() {
        given_a_classic_match_with_score(
                "| 0 - 40",
                "| 0 - 30");

        when_player_1_win_a_point();

        then_score_should_be(
                "| 1 - 00",
                "| 0 - 00");
    }

    @Test
    public void should_be_0_3_15_00_when_player_2_wins_3_first_games_and_player_1_wins_first_point_of_fourth_game() {
        given_a_classic_match_with_score(
                "| 0 - 00",
                "| 3 - 00");

        when_player_1_win_a_point();

        then_score_should_be(
                "| 0 - 15",
                "| 3 - 00");
    }

    @Test
    public void should_start_a_new_set_when_first_set_is_over() {
        given_a_classic_match_with_score(
                "| 5 - 40",
                "| 4 - 30");

        when_player_1_win_a_point();

        then_score_should_be(
                "| 6 | 0 - 00",
                "| 4 | 0 - 00");
    }

    @Test
    public void last_set_should_not_have_tie_break() {
        given_a_classic_match_with_score(
                "| 6 | 0 | 6 - 00",
                "| 0 | 6 | 6 - 00");

        when_player_1_win_a_point();

        then_score_should_be(
                "| 6 | 0 | 6 - 15",
                "| 0 | 6 | 6 - 00");
    }

    @Test
    public void every_set_except_last_should_have_tie_break_on_6_6() {
        given_a_classic_match_with_score(
                "| 6 - 00",
                "| 6 - 00");

        when_player_1_win_a_point();

        then_score_should_be(
                "| 6 - 1",
                "| 6 - 0");
    }

    @Test
    public void match_should_be_over_when_last_set_score_is_8_6() {
        given_a_classic_match_with_score(
                "| 6 | 0 | 7 - 40",
                "| 0 | 6 | 6 - 00");

        when_player_1_win_a_point();

        then_match_should_be_over();
    }

    @Test
    public void a_classic_match_should_be_over_when_a_player_wins_2_sets() {
        given_a_classic_match_with_score(
                "| 6 | 5 - 40",
                "| 0 | 4 - 00");

        when_player_1_win_a_point();

        then_match_should_be_over();
    }

    @Test
    public void a_grandslam_match_should_not_be_over_when_a_player_wins_2_sets() {
        given_a_grandslam_match_with_score(
                "| 6 | 5 - 40",
                "| 0 | 4 - 00");

        when_player_1_win_a_point();

        then_match_should_not_be_over();
    }

    @Test
    public void a_grandslam_match_should_be_over_when_a_player_wins_3_sets() {
        given_a_grandslam_match_with_score(
                "| 6 | 6 | 5 - 40",
                "| 0 | 0 | 4 - 00");

        when_player_1_win_a_point();

        then_match_should_be_over();
    }

    @Test
    public void player_should_not_be_able_to_score_after_winning_a_match() {
        given_a_grandslam_match_with_score(
                "| 6 | 6 | 6 | 0 - 00",
                "| 0 | 0 | 4 | 0 - 00");

        then_player_should_not_be_able_to_play_anymore();
    }

}