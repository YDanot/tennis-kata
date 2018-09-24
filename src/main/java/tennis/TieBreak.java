package tennis;

import static tennis.Player.PLAYER_2;
import static tennis.TieBreak.Point.SIX;
import static tennis.TieBreak.Point.ZERO;

public class TieBreak extends Game {

    private final Point player1Score;
    private final Point player2Score;
    private final Player winner;

    public TieBreak() {
        this(ZERO,ZERO,null);
    }

    private TieBreak(Point player1Score, Point player2Score) {
        this(player1Score, player2Score, null);
    }

    private TieBreak(Point player1Score, Point player2Score, Player winner) {
        this.player1Score = player1Score;
        this.player2Score = player2Score;
        this.winner = winner;
    }

    @Override
    public Game winPoint(Player player) {
        return super.winPoint(player);
    }

    @Override
    protected Game game(Player winner) {
        return new TieBreak(player1Score, player2Score, winner);
    }

    @Override
    protected boolean isGamePoint(Player player) {
        return scoreOf(player).greatherThanOrEqualsTo(SIX) && scoreOf(player).gapWith(scoreOf(opponent(player))) >= 1;
    }

    private Point scoreOf(Player player) {
        return PLAYER_2.equals(player) ? player2Score : player1Score;
    }

    @Override
    protected Game point(Player winner) {
        return winner == PLAYER_2 ? pointPlayer2() : pointPlayer1();
    }

    private Game pointPlayer1() {
        return new TieBreak(player1Score.next(), player2Score);
    }

    private Game pointPlayer2() {
        return new TieBreak(player1Score, player2Score.next());
    }

    @Override
    public String billboardScoreOf(Player player) {
        return String.valueOf(scoreOf(player).value);
    }


    @Override
    public String call() {
        if (winner != null){
            return winner == PLAYER_2 ? "Player 2 wins" : "Player 1 wins";
        }
        return player1Score.value + " - " + player2Score.value;
    }

    @Override
    public Player winner() {
        return winner;
    }

    static class Point {
        static final Point ZERO = new Point();
        static final Point SIX = new Point(6);
        private final int value;

        private Point(){
            this(0);
        }

        private Point(int value) {
            if (value < 0)
                throw new IllegalArgumentException("A tie break point is positive");
            this.value = value;
        }

        Point next(){
            return new Point(value + 1);
        }

        boolean greatherThanOrEqualsTo(Point point){
            return this.value >= point.value;
        }

        int gapWith(Point opponentScore) {
            return value - opponentScore.value;
        }
    }
}
