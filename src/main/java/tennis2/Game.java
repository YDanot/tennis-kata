package tennis2;


import static tennis2.Player.PLAYER_1;
import static tennis2.Player.PLAYER_2;
import static tennis2.Point.*;

public class Game {

    private Point player1Score;
    private Point player2Score;
    private Player winner;

    public Game() {
        this(LOVE, LOVE);
    }

    public Game(Point player1Score, Point player2Score) {
        this(player1Score, player2Score, null);
    }

    public Game(Point player1Score, Point player2Score, Player winner) {
        this.player1Score = player1Score;
        this.player2Score = player2Score;
        this.winner = winner;
    }

    public void point(Player player) {
        if (isGamePoint(player)) {
            game(player);
        } else {
            winPoint(player);
        }
    }

    protected boolean isGamePoint(Player player) {
        Point playerScore = scoreOf(player);
        Point opponentScore = scoreOf(opponent(player));
        return playerScore.equals(ADVANTAGE)
                || (playerScore.equals(FORTY)
                && !opponentScore.equals(ADVANTAGE)
                && !opponentScore.equals(FORTY));
    }

    private Game deuce() {
        return new Game(FORTY, FORTY);
    }

    protected void game(Player winner) {
        this.winner = winner;
    }

    private void winPoint(Player winner) {
        if (scoreOf(opponent(winner)).equals(ADVANTAGE)) {
            if (winner == PLAYER_2) {
                this.player1Score = FORTY;
            } else {
                player2Score = FORTY;
            }

        } else {
            if (winner == PLAYER_2) {
                pointPlayer2();
            } else {
                pointPlayer1();
            }
        }
    }

    private void pointPlayer1() {
        player1Score = next(player1Score);
    }

    private void pointPlayer2() {
        player2Score = next(player2Score);
    }

    private Point scoreOf(Player player) {
        return PLAYER_2.equals(player) ? player2Score : player1Score;
    }

    public String billboardScoreOf(Player player) {
        return scoreOf(player).billboardRepresentation();
    }

    protected Player opponent(Player player) {
        return player.equals(PLAYER_2) ? PLAYER_1 : PLAYER_2;
    }

    private Point next(Point point) {
        return Point.values()[point.ordinal() + 1];
    }

    public Player winner() {
        return winner;
    }

    public String call() {
        if (winner != null) {
            return winner == PLAYER_2 ? "Player 2 wins" : "Player 1 wins";
        }
        if (player2Score.equals(ADVANTAGE)) {
            return "ADVANTAGE Player 2";
        }

        if (player1Score.equals(ADVANTAGE)) {
            return "ADVANTAGE Player 1";
        }

        if (player1Score.equals(FORTY) && player2Score.equals(FORTY)) {
            return "DEUCE";
        }

        if (player1Score.equals(player2Score)) {
            return player1Score + " ALL";
        }

        return player1Score + " - " + player2Score;
    }

    public boolean isOver() {
        return winner() != null;
    }
}
