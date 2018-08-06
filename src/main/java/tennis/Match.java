package tennis;

import java.util.ArrayList;
import java.util.List;

import static tennis.Point.*;

public class Match {

    List<Player> points = new ArrayList<Player>();

    void point(Player player) {
        if (currentGame().winner() == null)
            points.add(player);
        else {
            throw new IllegalStateException("Game is over, you shouldn't have called point");
        }
    }

    Game currentGame() {
        Game game = new Game(LOVE, LOVE);
        for (Player pointWinner : points) {
            game = game.winPoint(pointWinner);
        }

        return game;
    }


}