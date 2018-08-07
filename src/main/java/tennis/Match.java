package tennis;

import java.util.ArrayList;
import java.util.List;

import static tennis.Player.PLAYER_1;
import static tennis.Player.PLAYER_2;

public class Match {

    private final List<Player> points            ;
    private final Game currentGame ;
    private final Set currentSet;
    private final List<Set> finishedSets;
    private final int winningSetNumber;

    private Match(List<Player> points, Game currentGame, Set currentSet, List<Set> finishedSets, int winningSetNumber) {
        this.points = points;
        this.currentGame = currentGame;
        this.currentSet = currentSet;
        this.finishedSets = finishedSets;
        this.winningSetNumber = winningSetNumber;
    }

    public static Match classic(){
         return new Match(new ArrayList<>(), new Game(), new Set(), new ArrayList<>(), 2);
    }

    public void point(Player player) {
        points.add(player);
    }

    public Match play() {
        Game currentGame = this.currentGame;
        Set currentSet = this.currentSet;

        for (Player point : points) {
            if (over()){
                throw new IllegalStateException("Match is over, you cannot play anymore");
            }
            currentGame = currentGame.winPoint(point);
            if (currentGame.winner() != null){
                currentSet = currentSet.winGame(currentGame.winner());
                if (currentSet.over()){
                    finishedSets.add(currentSet);
                    if (finishedSets.size() == 2){
                        currentSet = new LastSet();
                    }
                    else {
                        currentSet = new Set();
                    }
                }
                currentGame = new Game();
            }
        }
        return new Match(new ArrayList<>(), currentGame, currentSet, finishedSets, winningSetNumber);
    }


    String print() {
        String player1Score = "";
        String player2Score = "";

        for (Set set : finishedSets) {
            player1Score += "| "+set.player1score + " ";
            player2Score += "| "+set.player2score + " ";
        }

        player1Score += "| "+currentSet.player1score+" - "+ currentGame.billboardScoreOf(PLAYER_1);
        player2Score += "| "+currentSet.player2score+" - "+ currentGame.billboardScoreOf(PLAYER_2);

        return  player1Score + "\n" + player2Score;
    }

    public boolean over(){
        return setsWinBy(PLAYER_1) == winningSetNumber || setsWinBy(PLAYER_2) == winningSetNumber;
    }

    private int setsWinBy(Player player) {
        return (int)finishedSets.stream().filter(f -> f.winner() == player).count();
    }

}