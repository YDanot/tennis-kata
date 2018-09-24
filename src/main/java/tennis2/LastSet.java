package tennis2;

public class LastSet extends Set {

    public Player winner() {

        int player1score = player1score();
        int player2score = player2score();
        if (player1score >= 6 && player1score - player2score >= 2) {
            return Player.PLAYER_1;
        }

        if (player2score >= 6 && player2score - player1score >= 2) {
            return Player.PLAYER_2;
        }

        return null;
    }

    @Override
    void startNewGame() {
        if (over()) {
            throw new IllegalStateException("Set is over, you cannot win any game");
        }
        games.add(new Game());
    }
}
