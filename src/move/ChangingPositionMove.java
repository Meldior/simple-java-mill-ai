package move;

import game.Field;
import player.Player;

public class ChangingPositionMove extends PossibleDeletionMove {


    public ChangingPositionMove(Field from, Field to){
        super(from, to);
    }

    @Override
    public void makeMove(Player player) {
        player.movePawn(to.getCoordinates(), from.getPawn());
        checkMills(player);
    }

    @Override
    public void reverseMove(Player player) {
        reverseMoves(player);
        player.movePawn(from.getCoordinates(), to.getPawn());
    }


}
