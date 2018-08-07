package tennis;

public class Set {

    public final int player1score;

    public final int player2score;

    public Set(){
        this(0,0);
    }

    public Set(int player1score, int player2score) {
        this.player1score = player1score;
        this.player2score = player2score;
    }

    public String call() {
        return player1score + " - " + player2score;
    }

    public Set winGame(Player player) {
        if (over()){
            throw new IllegalStateException("Set is over, you cannot win any game");
        }

        if (player == Player.PLAYER_1) {
            return new Set(player1score+1, player2score);
        }
            return new Set(player1score, player2score+1);

    }

    public Player winner() {

        if (player1score >=6 && player1score - player2score >=2) {
            return Player.PLAYER_1;
        }

        if (player2score >=6 && player2score - player1score >=2) {
            return Player.PLAYER_2;
        }

        return null;
    }

    public boolean over() {
        return winner() != null;
    }
}
