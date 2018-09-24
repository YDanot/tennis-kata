package tennis;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static tennis.Player.PLAYER_1;
import static tennis.Player.PLAYER_2;

class MatchTestDSL {

    private static Match match = Match.classic();

    static void then_player_should_not_be_able_to_play_anymore() {
        match.point(PLAYER_1);
        assertThatExceptionOfType(IllegalStateException.class).isThrownBy(() -> match.play());
    }

    static void then_match_should_be_over() {
        match.play();
        assertThat(match.over()).isTrue();
    }

    static void then_match_should_not_be_over() {
        match.play();
        assertThat(match.over()).isFalse();
    }

    static void then_score_should_be(String player1Line, String player2Line) {
        assertThat(render()).isEqualTo(player1Line + "\n" + player2Line);
    }

    static void when_player_1_win_a_point() {
        winAPoint(PLAYER_1);
    }

    static void when_player_2_win_a_point() {
        winAPoint(PLAYER_2);
    }

    private static String render() {
        match.play();
        return match.billboardPrint();
    }

    private static void winAPoint(Player player) {
        match.point(player);
    }

    private static void winABlankGame(Player player) {
        winAPoint(player);
        winAPoint(player);
        winAPoint(player);
        winAPoint(player);
    }

    static void given_a_grandslam_match_with_score(String renderedPlayer1ScoreLines, String renderedPlayer2ScoreLines) {
        match = Match.grandslam();
        buildMatch(renderedPlayer1ScoreLines, renderedPlayer2ScoreLines);
    }

    static void given_a_classic_match_with_score(String renderedPlayer1ScoreLines, String renderedPlayer2ScoreLines) {
        match = Match.classic();
        buildMatch(renderedPlayer1ScoreLines, renderedPlayer2ScoreLines);
    }

    private static void buildMatch(String renderedPlayer1ScoreLines, String renderedPlayer2ScoreLines) {

        for (Set set : getSets(renderedPlayer1ScoreLines, renderedPlayer2ScoreLines)) {
            set.play();
        }

        getCurrentGame(renderedPlayer1ScoreLines, renderedPlayer2ScoreLines).play();
    }


    private static List<Set> getSets(String renderedPlayer1ScoreLines, String renderedPlayer2ScoreLines) {
        List<Set> sets = new ArrayList<>();

        String[] player1SetScoreLine = renderedPlayer1ScoreLines.split("-")[0].split("\\|");
        String[] player2SetScoreLine = renderedPlayer2ScoreLines.split("-")[0].split("\\|");

        for (int i = 1; i < player1SetScoreLine.length; i++) {
            sets.add(new Set(Integer.valueOf(player1SetScoreLine[i].trim()), Integer.valueOf(player2SetScoreLine[i].trim())));
        }
        return sets;
    }

    private static CurrentGame getCurrentGame(String renderedPlayer1ScoreLines, String renderedPlayer2ScoreLines) {
        int currentGameScorePlayer1 = toNbPoints(toPoint(renderedPlayer1ScoreLines.split("-")[1].trim()));
        int currentGameScorePlayer2 = toNbPoints(toPoint(renderedPlayer2ScoreLines.split("-")[1].trim()));

        return new CurrentGame(currentGameScorePlayer1, currentGameScorePlayer2);
    }

    private static int toNbPoints(Point point) {
        return point.ordinal();
    }

    private static Point toPoint(String pointInString) {
        for (Point point : Point.values()) {
            if (point.billboardRepresentation().equals(pointInString))
                return point;
        }
        throw new IllegalArgumentException(pointInString + " does not exists as score representation");
    }

    static void given_a_new_classic_match() {
        match = Match.classic();
    }

    static class Set {
        final int player1;
        final int player2;

        Set(int player1, int player2) {
            this.player1 = player1;
            this.player2 = player2;
        }

        private void play() {
            MatchTestDSL.play(player1, player2, MatchTestDSL::winABlankGame);
        }
    }

    static class CurrentGame {
        final int player1;
        final int player2;

        CurrentGame(int player1, int player2) {
            this.player1 = player1;
            this.player2 = player2;
        }

        private void play() {
            MatchTestDSL.play(player1, player2, MatchTestDSL::winAPoint);
        }
    }

    private static void play(int player1, int player2, Consumer<Player> function) {
        if (player1 != 0 || player2 != 0) {
            int i = 0;
            if (player1 > player2) {
                function.accept(PLAYER_1);
                player1--;
                i++;
            } else {
                function.accept(PLAYER_2);
                player2--;
            }


            while (player1 != 0 || player2 != 0) {
                if (i % 2 == 0 && player1 != 0) {
                    function.accept(PLAYER_1);
                    player1--;
                } else if (player2 != 0) {
                    function.accept(PLAYER_2);
                    player2--;
                }
                i++;
            }
        }
    }
}
