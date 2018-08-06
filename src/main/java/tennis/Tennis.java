package tennis;

import java.util.ArrayList;
import java.util.List;

import static tennis.Point.*;

public class Tennis {

    List<Player> points = new ArrayList<Player>();

    void point(Player player) {
        if (game().winner() == null)
            points.add(player);
        else {
            throw new IllegalStateException("Game is over, you shouldn't have called point");
        }
    }

    Game game() {
        Game game = new Game(LOVE, LOVE);
        for (Player pointWinner : points) {
            game = game.winPoint(pointWinner);
        }

        return game;
    }

}