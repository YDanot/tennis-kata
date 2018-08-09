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

    private Match(int winningSetNumber){
        this(new ArrayList<>(), new Game(), new Set(), new ArrayList<>(),winningSetNumber);
    }

    public static Match classic(){
        return new Match(2);
    }

    public static Match grandslam(){
        return new Match(3);
    }

    public void point(Player player) {
        points.add(player);
    }

    public Match play() {
        Match match = this;
        for (Player point : points) {
            if (over()){
                throw new IllegalStateException("Match is over, you cannot play anymore");
            }
            match = match.winPoint(point);
        }

        return new Match(new ArrayList<>(), match.currentGame, match.currentSet, match.finishedSets, match.winningSetNumber);
    }

    private Match winPoint(Player player) {
        Match match = new Match(points, currentGame.winPoint(player), currentSet, finishedSets, winningSetNumber);
        if (match.currentGame.isOver()){
            match = match.winGame(player);
        }
        return match;
    }

    private Match winGame(Player player) {
        Match match = new Match(points, currentGame, currentSet.winGame(player), finishedSets, winningSetNumber);
        if (match.currentSet.over()){
            match = match.startNewSet();
        }
        return match.startNewGame();
    }

    private Match startNewGame() {
        return new Match(points, new Game(), currentSet, finishedSets,winningSetNumber);
    }

    private Match startNewSet() {
        finishedSets.add(currentSet);
        if (finishedSets.size() == winningSetNumber * 2 - 2){
            return new Match(points, currentGame, new LastSet(), finishedSets,winningSetNumber);
        }
        return new Match(points, currentGame, new Set(), finishedSets,winningSetNumber);
    }

    String billboardPrint() {
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