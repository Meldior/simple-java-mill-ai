package move;


import game.Field;
import player.Player;

public class DeployingMove extends PossibleDeletionMove {

    public DeployingMove(Field to){
        super(null, to);
    }

    @Override
    public void makeMove(Player player) {
        player.placePawn(to.getCoordinates());
        checkMills(player);
    }

    @Override
    public void reverseMove(Player player) {
        reverseMoves(player);
        player.reversePawnPlacing(to.getPawn());
    }
}
