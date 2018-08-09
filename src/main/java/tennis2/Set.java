package tennis2;

import org.assertj.core.util.Lists;

import java.util.List;

public class Set {

    private final List<Game> games;

    Set() {
        this(Lists.list(new Game()));
    }

    private Set(List<Game> games) {
        this.games = games;
    }

    public String call() {
        return player1score() + " - " + player2score();
    }

    int player1score() {
        return (int) games.stream().filter(g -> g.winner() == Player.PLAYER_1).count();
    }

    int player2score() {
        return (int) games.stream().filter(g -> g.winner() == Player.PLAYER_2).count();
    }

    void startNewGame() {
        if (over()){
            throw new IllegalStateException("Set is over, you cannot win any game");
        }
        games.add(new Game());
    }

    public Player winner() {

        int player1score = player1score();
        int player2score = player2score();

        if (player1score == 7 && player2score == 6) {
            return Player.PLAYER_1;
        }
        if (player1score == 6 && player2score == 7) {
            return Player.PLAYER_2;
        }

        if (player1score >= 6 && player1score - player2score >= 2) {
            return Player.PLAYER_1;
        }

        if (player2score >= 6 && player2score - player1score >= 2) {
            return Player.PLAYER_2;
        }

        return null;
    }

    boolean over() {
        return winner() != null;
    }

    Game currentGame() {
        return games.get(games.size() - 1);
    }


}
