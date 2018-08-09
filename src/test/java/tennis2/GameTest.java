package tennis2;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static tennis2.Player.PLAYER_1;
import static tennis2.Player.PLAYER_2;

public class GameTest {

    private Game game = new Game();

    @Test
    public void should_score_be_love_all_on_a_new_game() {
        Assertions.assertThat(game.call()).isEqualTo("LOVE ALL");
    }

    @Test
    public void should_score_be_fifteen_love_on_the_first_point_win() {
        point(PLAYER_1);
        Assertions.assertThat(game.call()).isEqualTo("FIFTEEN - LOVE");
    }

    @Test
    public void should_score_be_fifteen_all_when_both_player_won_a_point() {
        point(PLAYER_1);
        point(PLAYER_2);
        Assertions.assertThat(game.call()).isEqualTo("FIFTEEN ALL");
    }

    @Test
    public void should_score_be_thirty_love_when_a_player_1_won_the_first_2_points() {
        point(PLAYER_1);
        point(PLAYER_1);
        Assertions.assertThat(game.call()).isEqualTo("THIRTY - LOVE");
    }

    @Test
    public void should_score_be_fourty_love_when_a_player_1_won_the_first_3_points() {
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        Assertions.assertThat(game.call()).isEqualTo("FORTY - LOVE");
    }

    @Test
    public void should_score_be_deuce_when_both_player_won_3_points() {
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_2);
        Assertions.assertThat(game.call()).isEqualTo("DEUCE");
    }

    @Test
    public void should_score_be_adv_player_1_when_player_1_scores_in_deuce() {
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_1);
        Assertions.assertThat(game.call()).isEqualTo("ADVANTAGE Player 1");
    }

    @Test
    public void should_score_be_adv_player_2_when_player_2_scores_in_deuce() {
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_2);
        Assertions.assertThat(game.call()).isEqualTo("ADVANTAGE Player 2");
    }

    @Test
    public void should_score_be_deuce_when_both_player_won_4_points() {
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_1);
        point(PLAYER_2);
        Assertions.assertThat(game.call()).isEqualTo("DEUCE");
    }

    @Test
    public void should_player_1_win_game_when_he_won_the_first_4_points() {
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        Assertions.assertThat(game.call()).isEqualTo("Player 1 wins");
    }

    @Test
    public void should_player_2_win_game_when_he_won_the_first_4_points() {
        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_2);
        Assertions.assertThat(game.call()).isEqualTo("Player 2 wins");
    }

    @Test
    public void should_player_2_won_game_when_he_won_4_points() {
        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_1);
        point(PLAYER_2);
        point(PLAYER_2);
        Assertions.assertThat(game.call()).isEqualTo("Player 2 wins");
    }

    @Test
    public void should_player_win_when_he_scores_with_advantage() {
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_1);
        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_2);
        point(PLAYER_1);
        point(PLAYER_1);
        Assertions.assertThat(game.call()).isEqualTo("Player 1 wins");
    }
    

    private void point(Player player) {
        game.point(player);
    }
}
