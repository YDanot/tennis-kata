package tennis2;

import org.assertj.core.util.Lists;

import java.util.List;

import static tennis2.Player.PLAYER_1;
import static tennis2.Player.PLAYER_2;

public class Match {

    private final List<Set> sets;

    private final int winningSetNumber;

    private Match(int winningSetNumber, List<Set> sets) {
        this.sets = sets;
        this.winningSetNumber = winningSetNumber;
    }

    private Match(int winningSetNumber) {
        this(winningSetNumber, Lists.list(new Set()));
    }

    public static Match classic() {
        return new Match(2);
    }

    public static Match grandslam() {
        return new Match(3);
    }

    public void point(Player player) {
        if (over()){
            throw new IllegalStateException("Match is over, you cannot play anymore");
        }
        currentGame().point(player);
        if (currentGame().isOver()) {
            if (currentSet().over()){
                startNewSet();
            }
            else{
                currentSet().startNewGame();
            }
        }
    }

    private Game currentGame() {
        return currentSet().currentGame();
    }

    private Set currentSet() {
        return sets.get(sets.size() - 1);
    }

    private void startNewSet() {
        if (sets.size() == winningSetNumber * 2 - 2){
            sets.add(new LastSet());
        }
        else{
            sets.add(new Set());
        }
    }


    private int setsWonBy(Player player) {
        return (int) sets.stream().filter(s -> s.winner() == player).count();
    }

    public String billboardPrint() {
        String player1Score = "";
        String player2Score = "";


        for (Set set : sets) {

            if (set.equals(currentSet())) {
                player1Score += "| " + currentSet().player1score() + " - " + currentGame().billboardScoreOf(PLAYER_1);
                player2Score += "| " + currentSet().player2score() + " - " + currentGame().billboardScoreOf(PLAYER_2);
            }
            else {
                player1Score += "| " + set.player1score() + " ";
                player2Score += "| " + set.player2score() + " ";
            }
        }

        return player1Score + "\n" + player2Score;
    }

    public boolean over() {
        return setsWonBy(PLAYER_1) == winningSetNumber || setsWonBy(PLAYER_2) == winningSetNumber;
    }

}