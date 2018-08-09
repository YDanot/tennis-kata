package tennis2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static tennis2.Player.PLAYER_1;
import static tennis2.Player.PLAYER_2;


public class MatchTest {

    private Match match = Match.classic();

    @Test
    public void should_begin_0_0() {
        assertThat(render()).isEqualTo(
                "| 0 - 00" + "\n" +
                "| 0 - 00");
    }

    @Test
    public void should_be_0_0_15_0_when_player_1_wins_first_point() {
        winAPoint(PLAYER_1);
        assertThat(render()).isEqualTo(
                "| 0 - 15" + "\n" +
                "| 0 - 00");
    }

    @Test
    public void should_be_0_0_30_0_when_player_1_wins_2_first_point() {
        winAPoint(PLAYER_1);
        winAPoint(PLAYER_1);
        assertThat(render()).isEqualTo(
                "| 0 - 30" + "\n" +
                "| 0 - 00");
    }

    @Test
    public void should_be_0_0_40_0_when_player_1_wins_3_first_point() {
        winAPoint(PLAYER_1);
        winAPoint(PLAYER_1);
        winAPoint(PLAYER_1);
        assertThat(render()).isEqualTo(
                "| 0 - 40" + "\n" +
                "| 0 - 00");
    }

    @Test
    public void should_be_0_0_40_AD_when_player_1_wins_3_first_points_and_player2_wins_4_last() {
        winAPoint(PLAYER_1);
        winAPoint(PLAYER_1);
        winAPoint(PLAYER_1);
        winAPoint(PLAYER_2);
        winAPoint(PLAYER_2);
        winAPoint(PLAYER_2);
        winAPoint(PLAYER_2);

        assertThat(render()).isEqualTo(
                "| 0 - 40" + "\n" +
                "| 0 - AD");
    }

    @Test
    public void should_start_a_new_game_when_first_game_is_over() {
        winABlankGame(PLAYER_1);
        assertThat(render()).isEqualTo(
                "| 1 - 00" + "\n" +
                "| 0 - 00");
    }

    @Test
    public void should_be_0_3_15_00_when_player_2_wins_3_first_games_and_player_1_wins_first_point_of_fourth_game() {
        winABlankGame(PLAYER_2);
        winABlankGame(PLAYER_2);
        winABlankGame(PLAYER_2);
        winAPoint(PLAYER_1);
        assertThat(render()).isEqualTo(
                "| 0 - 15" + "\n" +
                "| 3 - 00");
    }

    @Test
    public void should_start_a_new_set_when_first_set_is_over() {
        winABlankSet(PLAYER_2);
        assertThat(render()).isEqualTo(
                "| 0 | 0 - 00" + "\n" +
                "| 6 | 0 - 00");
    }

    @Test
    public void last_set_should_not_have_tie_break() {
        winABlankSet(PLAYER_1);
        winABlankSet(PLAYER_2);

        winABlankGame(PLAYER_1);
        winABlankGame(PLAYER_1);
        winABlankGame(PLAYER_1);
        winABlankGame(PLAYER_1);
        winABlankGame(PLAYER_1);

        winABlankGame(PLAYER_2);
        winABlankGame(PLAYER_2);
        winABlankGame(PLAYER_2);
        winABlankGame(PLAYER_2);
        winABlankGame(PLAYER_2);
        winABlankGame(PLAYER_2);

        winABlankGame(PLAYER_1);
        winABlankGame(PLAYER_1);
        winABlankGame(PLAYER_2);

        assertThat(render()).isEqualTo(
                "| 6 | 0 | 7 - 00" + "\n" +
                "| 0 | 6 | 7 - 00");
    }

    @Test
    public void match_should_be_over_when_last_set_score_is_8_6() {
        winABlankSet(PLAYER_1);
        winABlankSet(PLAYER_2);

        winABlankGame(PLAYER_1);
        winABlankGame(PLAYER_1);
        winABlankGame(PLAYER_1);
        winABlankGame(PLAYER_1);
        winABlankGame(PLAYER_1);

        winABlankGame(PLAYER_2);
        winABlankGame(PLAYER_2);
        winABlankGame(PLAYER_2);
        winABlankGame(PLAYER_2);
        winABlankGame(PLAYER_2);
        winABlankGame(PLAYER_2);

        winABlankGame(PLAYER_1);
        winABlankGame(PLAYER_1);
        winABlankGame(PLAYER_1);

        assertThat(match.over()).isTrue();
    }

    @Test
    public void a_classic_match_should_be_over_when_a_player_wins_2_sets(){
        winABlankSet(PLAYER_1);
        winABlankSet(PLAYER_2);
        winABlankSet(PLAYER_1);
        assertThat(match.over()).isTrue();
    }

    @Test
    public void a_grandslam_match_should_not_be_over_when_a_player_wins_2_sets(){
        match = Match.grandSlam();
        winABlankSet(PLAYER_1);
        winABlankSet(PLAYER_2);
        winABlankSet(PLAYER_1);
        assertThat(match.over()).isFalse();
    }

    @Test
    public void a_grandslam_match_should_be_over_when_a_player_wins_3_sets(){
        match = Match.grandSlam();
        winABlankSet(PLAYER_1);
        winABlankSet(PLAYER_2);
        winABlankSet(PLAYER_1);
        winABlankSet(PLAYER_1);
        assertThat(match.over()).isTrue();
    }

    @Test(expected = IllegalStateException.class)
    public void player_should_not_be_able_to_score_after_winning_a_match() {
        winABlankSet(PLAYER_1);
        winABlankSet(PLAYER_2);
        winABlankSet(PLAYER_1);
        winAPoint(PLAYER_1);
    }

    private String render() {
        return match.billboardPrint();
    }

    private void winAPoint(Player player) {
        match.point(player);
    }

    private void winABlankGame(Player player) {
        winAPoint(player);
        winAPoint(player);
        winAPoint(player);
        winAPoint(player);
    }

    private void winABlankSet(Player player) {
        winABlankGame(player);
        winABlankGame(player);
        winABlankGame(player);
        winABlankGame(player);
        winABlankGame(player);
        winABlankGame(player);
    }

}