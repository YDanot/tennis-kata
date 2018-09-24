package tennis;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Date;

import static tennis.Player.PLAYER_1;
import static tennis.Player.PLAYER_2;

public class SetTest {

    private Set set;

    @Before
    public void setup() {
        set = new Set();
    }

    @Test
    public void score_should_be_0_0_on_a_new_set() {
        Assertions.assertThat(set.call()).isEqualTo("0 - 0");
    }

    @Test
    public void score_should_be_1_0_when_player_1_win_first_game() {
        winGame(PLAYER_1);
        Assertions.assertThat(set.call()).isEqualTo("1 - 0");
    }

    @Test
    public void score_should_be_0_1_when_player_2_win_first_game() {
        winGame(PLAYER_2);
        Assertions.assertThat(set.call()).isEqualTo("0 - 1");
    }

    @Test
    public void score_should_be_2_1_when_player_2_wins_1_game_and_player_1_wins_2_games() {
        winGame(PLAYER_2);
        winGames(PLAYER_1,2);
        Assertions.assertThat(set.call()).isEqualTo("2 - 1");
    }

    @Test
    public void score_should_be_1_2_when_player_1_wins_1_game_and_player_2_wins_2_games() {
        winGame(PLAYER_1);
        winGames(PLAYER_2,2);
        Assertions.assertThat(set.call()).isEqualTo("1 - 2");
    }

    @Test
    public void set_should_not_be_over_on_2_1() {
        winGames(PLAYER_1,2);
        winGame(PLAYER_2);
        Assertions.assertThat(set.over()).isFalse();
    }

    @Test
    public void set_should_be_won_by_player_1_when_player_1_wins_6_first_games() {
        winGames(PLAYER_1,6);
        Assertions.assertThat(set.over()).isTrue();
        Assertions.assertThat(set.winner()).isEqualTo(PLAYER_1);
    }

    @Test
    public void set_should_be_won_by_player_2_when_player_2_wins_6_first_games() {
        winGames(PLAYER_2,6);
        Assertions.assertThat(set.over()).isTrue();
        Assertions.assertThat(set.winner()).isEqualTo(PLAYER_2);
    }

    @Test
    public void set_should_not_be_over_on_6_5() {
        winGames(PLAYER_1,5);
        winGames(PLAYER_2,5);
        winGame(PLAYER_1);

        Assertions.assertThat(set.over()).isFalse();
    }

    @Test
    public void set_should_be_over_on_7_5() {
        winGames(PLAYER_1,5);
        winGames(PLAYER_2,5);
        winGames(PLAYER_1,2);

        Assertions.assertThat(set.over()).isTrue();
    }

    @Test
    public void set_should_be_over_on_7_6() {
        winGames(PLAYER_1, 5);
        winGames(PLAYER_2, 6);
        winGames(PLAYER_1, 2);

        Assertions.assertThat(set.over()).isTrue();
    }

    @Test
    public void last_set_should_not_be_over_on_7_6() {
        set = new LastSet();
        winGames(PLAYER_1, 5);
        winGames(PLAYER_2, 6);
        winGames(PLAYER_1, 2);

        Assertions.assertThat(set.over()).isFalse();
    }

    @Test(expected = IllegalStateException.class)
    public void player_should_not_be_able_to_score_after_winning_a_game() {
        winGames(PLAYER_2, 7);
    }

    private void winGame(Player player) {
        winGames(player, 1);
    }

    private void winGames(Player player, int nbGame) {
        for (int i = 0; i < nbGame; i++) {
            set = set.winGame(player);
        }
    }

}
