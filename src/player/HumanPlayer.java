package player;

import movehandler.HumanMoveHandler;
import movehandler.MoveHandler;

public class HumanPlayer extends Player {

    private final MoveHandler moveHandler;

    public HumanPlayer(char player){
        super(player);
        this.moveHandler = new HumanMoveHandler();
    }

    @Override
    public void makeMove(Player player) {
        moveHandler.makeMove(game, this);
    }
}
