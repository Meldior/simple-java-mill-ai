public class ChangingPositionMove extends PossibleDeletionMove{


    ChangingPositionMove(Field from, Field to){
        super(from, to);
    }

    @Override
    void makeMove(Player player) {
        //player.lastUsedField = from;
        player.movePawn(to.getCoordinates(), from.getPawn());
        checkMills(player);
    }

    @Override
    void reverseMove(Player player) {
        reverseMoves(player);
        player.movePawn(from.getCoordinates(), to.getPawn());
    }


}
