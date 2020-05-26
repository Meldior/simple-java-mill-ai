package movehandler;

import game.Game;
import player.Player;

public interface MoveHandler {
    void makeMove(Game game, Player player);
}
