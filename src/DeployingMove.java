

public class DeployingMove extends PossibleDeletionMove{

    DeployingMove(Field to){
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
