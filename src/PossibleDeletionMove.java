import java.util.*;

public abstract class PossibleDeletionMove extends Move {
    public List<DeletingMove> deletingMoves;

    PossibleDeletionMove(Field from, Field to) {
        super(from, to);
    }

    protected void checkMills(Player player){
        int mills = player.getGame().getMillsAmount(to.getCoordinates());
        if(mills > 0){
            deletingMoves = new ArrayList<>();
            HashSet<Pawn> pawnsToDelete = player.getGame().getOtherPlayer(player).getPawns();
            Iterator<Pawn> pawnIterator = pawnsToDelete.iterator();
            //System.out.println(pawnsToDelete);
            int i = 0;
            while(i < mills && pawnIterator.hasNext()){
                Pawn pawn = pawnIterator.next();
                DeletingMove move = new DeletingMove(pawn.getLocation());
                pawnIterator.remove();
                deletingMoves.add(move);
                move.makeMove(player);
                i++;
            }
        }
    }

    protected void reverseMoves(Player player){
        if(deletingMoves != null){
            ListIterator<DeletingMove> deletingMoveListIterator = deletingMoves.listIterator(deletingMoves.size());
            while(deletingMoveListIterator.hasPrevious()){
                deletingMoveListIterator.previous().reverseMove(player);
            }
        }
    }
}
