package heuristic;

import game.Game;
import player.Player;

public interface Heuristic {
    int evaluate(Player player, Player otherPlayer, Game game);
}
