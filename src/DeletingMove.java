
public class DeletingMove extends Move {

    DeletingMove(Field from){
        super(from, null);
    }

    @Override
    public void makeMove(Player player) {
        player.getGame().getOtherPlayer(player).removePawn(from.getPawn());
        from.getPawn().getLocation().removePawn();
    }

    @Override
    public void reverseMove(Player player) {
        //Pawn pawn = new Pawn(player.getGame().getOtherPlayer(player).getColor());
        //player.p
        //player.getGame().getOtherPlayer(player).movePawn(from.getCoordinates(), pawn);
        //player.getGame().getOtherPlayer(player).placePawn(from.getCoordinates());
        Pawn pawn = new Pawn(player.getGame().getOtherPlayer(player).getColor());
        pawn.setCurrentLocation(from);
        player.getGame().getOtherPlayer(player).reversePawnDeletion(from.getCoordinates(), pawn);
    }
}


