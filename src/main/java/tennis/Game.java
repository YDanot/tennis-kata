package tennis;


import static tennis.Player.PLAYER_1;
import static tennis.Player.PLAYER_2;
import static tennis.Point.*;

public class Game {

    private final Point player1Score;
    private final Point player2Score;
    private final Player winner;

    public Game(){
        this(LOVE,LOVE);
    }
    public Game(Point player1Score, Point player2Score) {
        this(player1Score,player2Score,null);
    }

    public Game(Point player1Score, Point player2Score, Player winner) {
        this.player1Score = player1Score;
        this.player2Score = player2Score;
        this.winner = winner;
    }

    public Game winPoint(Player player) {
        if (isGamePoint(player)) {
            return game(player);
        }

        return point(player);
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

    protected Game game(Player winner) {
        return new Game(player1Score, player2Score, winner);
    }

    protected Game point(Player winner) {
        if (scoreOf(opponent(winner)).equals(ADVANTAGE)) {
            return deuce();
        }
        return winner == PLAYER_2 ? pointPlayer2() : pointPlayer1();
    }

    private Game pointPlayer1() {
        return new Game(next(player1Score), player2Score);
    }

    private Game pointPlayer2() {
        return new Game(player1Score, next(player2Score));
    }

    private Point scoreOf(Player player) {
        return PLAYER_2.equals(player) ? player2Score : player1Score;
    }

    public String billboardScoreOf(Player player) {
        return scoreOf(player).billboardRepresentation();
    }

    protected Player opponent(Player player) {
        return player.equals(Player.PLAYER_2) ? PLAYER_1 : Player.PLAYER_2;
    }

    private Point next(Point point) {
        return Point.values()[point.ordinal() + 1];
    }

    public Player winner() {
        return winner;
    }

    public String call() {
        if (winner != null){
            return winner == PLAYER_2 ? "Player 2 wins" : "Player 1 wins";
        }
        if (player2Score.equals(ADVANTAGE)){
            return "ADVANTAGE Player 2";
        }

        if (player1Score.equals(ADVANTAGE)){
            return "ADVANTAGE Player 1";
        }

        if (player1Score.equals(FORTY) && player2Score.equals(FORTY)){
            return "DEUCE";
        }

        if (player1Score.equals(player2Score)){
            return player1Score + " ALL";
        }

        return player1Score + " - " + player2Score;
    }

    boolean isOver() {
        return winner() != null;
    }
}
