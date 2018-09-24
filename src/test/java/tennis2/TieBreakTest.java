package tennis2;

import org.assertj.core.api.Assertions;
import org.junit.Test;


import static tennis2.Player.PLAYER_1;
import static tennis2.Player.PLAYER_2;

public class TieBreakTest {

    private Game game = new TieBreak();

    @Test
    public void should_score_be_0_0_on_a_new_tiebreak() {
        Assertions.assertThat(game.call()).isEqualTo("0 - 0");
    }

    @Test
    public void should_score_be_1_0_on_the_first_point_win() {
        point(PLAYER_1);
        Assertions.assertThat(game.call()).isEqualTo("1 - 0");
    }

    @Test
    public void should_score_be_1_1_when_both_player_won_a_point() {
        point(PLAYER_1);
        point(PLAYER_2);
        Assertions.assertThat(game.call()).isEqualTo("1 - 1");
    }

    @Test
    public void should_score_be_2_0_when_a_player_1_won_the_first_2_points() {
        point(PLAYER_1);
        point(PLAYER_1);
        Assertions.assertThat(game.call()).isEqualTo("2 - 0");
    }

    @Test
    public void should_score_be_3_0_when_a_player_1_won_the_first_3_points() {
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        Assertions.assertThat(game.call()).isEqualTo("3 - 0");
    }

    @Test
    public void should_score_be_4_0_when_a_player_1_won_the_first_4_points() {
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        Assertions.assertThat(game.call()).isEqualTo("4 - 0");
    }

    @Test
    public void should_score_be_5_0_when_a_player_1_won_the_first_5_points() {
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        Assertions.assertThat(game.call()).isEqualTo("5 - 0");
    }

    @Test
    public void should_score_be_6_0_when_a_player_1_won_the_first_6_points() {
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        Assertions.assertThat(game.call()).isEqualTo("6 - 0");
    }


    @Test
    public void should_player_1_win_game_when_he_won_the_first_7_points() {
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        Assertions.assertThat(game.call()).isEqualTo("Player 1 wins");
    }

    @Test
    public void should_player_2_win_game_when_he_won_the_first_7_points() {
        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_2);
        Assertions.assertThat(game.call()).isEqualTo("Player 2 wins");
    }

    @Test
    public void should_not_be_over_on_7_6() {
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);

        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_1);

        Assertions.assertThat(game.call()).isEqualTo("7 - 6");
    }

    @Test
    public void should_be_over_on_9_7() {
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);

        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_1);
        point(PLAYER_2);
        point(PLAYER_1);
        point(PLAYER_1);

        Assertions.assertThat(game.call()).isEqualTo("Player 1 wins");
    }

    private void point(Player player) {
        game.point(player);
    }
}
