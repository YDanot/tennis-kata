package tennis;

public class Set {

    public final int player1score;

    public final int player2score;

    Set(){
        this(0,0);
    }

    Set(int player1score, int player2score) {
        this.player1score = player1score;
        this.player2score = player2score;
    }

    public String call() {
        return player1score + " - " + player2score;
    }

    Set winGame(Player player) {
        if (over()){
            throw new IllegalStateException("Set is over, you cannot win any game");
        }

        if (player == Player.PLAYER_1) {
            return startNewGame(player1score + 1, player2score);
        }
            return startNewGame(player1score, player2score+1);

    }

    protected Set startNewGame(int player1score, int player2score) {
        return new Set(player1score, player2score);
    }

    public Player winner() {

        if (player1score == 7 && player2score == 6){
            return Player.PLAYER_1;
        }
        if (player1score == 6 && player2score == 7){
            return Player.PLAYER_2;
        }

        if (player1score >= 6 && player1score - player2score >=2) {
            return Player.PLAYER_1;
        }

        if (player2score >= 6 && player2score - player1score >=2) {
            return Player.PLAYER_2;
        }

        return null;
    }

    boolean over() {
        return winner() != null;
    }
}
