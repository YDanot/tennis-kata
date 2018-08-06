package tennis;

public class Set {

    private int player1score;

    private int player2score;

    public String call() {
        return player1score + " - " + player2score;
    }

    public void winGame(Player player) {
        if (over()){
            throw new IllegalStateException("Set is over, you cannot win any game");
        }

        if (player == Player.PLAYER_1) {
            player1score++;
        } else {
            player2score++;
        }
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
