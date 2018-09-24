package tennis;

import java.util.ArrayList;
import java.util.List;

import static tennis.Player.PLAYER_1;
import static tennis.Player.PLAYER_2;

public class Match {

    private final List<Player> points = new ArrayList<>();
    private Game currentGame = new Game();
    private final List<Set> sets = new ArrayList<>();
    private Set currentSet = new Set();
    private final int winningSetNumber;

    private Match(int winningSetNumber) {
        this.winningSetNumber = winningSetNumber;
    }

    static Match classic() {
        return new Match(2);
    }

    static Match grandslam() {
        return new Match(3);
    }

    public void point(Player player) {
        points.add(player);
    }

    void play() {
        for (Player point : points) {
            if (over()) {
                throw new IllegalStateException("Match is over, you cannot play anymore");
            }
            winPoint(point);
        }
    }

    private void winPoint(Player player) {
        currentGame = currentGame.winPoint(player);
        if (currentGame.isOver()) {
            winGame(player);
        }
    }

    private void winGame(Player player) {
        currentSet = currentSet.winGame(player);
        if (currentSet.over()) {
            startNewSet();
        }
        startNewGame();
    }

    private void startNewGame() {
        currentGame = (!(currentSet instanceof LastSet) && currentSet.player1score == 6 && currentSet.player2score == 6) ? new TieBreak() : new Game();
    }

    private void startNewSet() {
        sets.add(currentSet);
        if (sets.size() == winningSetNumber * 2 - 2) {
            currentSet = new LastSet();
        } else {
            currentSet = new Set();
        }
    }

    String billboardPrint() {
        String player1Score = "";
        String player2Score = "";

        for (Set set : sets) {
            player1Score += "| " + set.player1score + " ";
            player2Score += "| " + set.player2score + " ";
        }

        player1Score += "| " + currentSet.player1score + " - " + currentGame.billboardScoreOf(PLAYER_1);
        player2Score += "| " + currentSet.player2score + " - " + currentGame.billboardScoreOf(PLAYER_2);

        return player1Score + "\n" + player2Score;
    }

    boolean over() {
        return setsWinBy(PLAYER_1) == winningSetNumber || setsWinBy(PLAYER_2) == winningSetNumber;
    }

    private int setsWinBy(Player player) {
        return (int) sets.stream().filter(f -> f.winner() == player).count();
    }

}