package tennis;

public class LastSet extends Set {

    private LastSet(int player1score, int player2score) {
        super(player1score, player2score);
    }

    public LastSet() {
        super();
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

    protected Set startNewGame(int player1score, int player2score) {
        return new LastSet(player1score, player2score);
    }
}
