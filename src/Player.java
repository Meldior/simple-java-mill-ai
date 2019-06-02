import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public abstract class Player {
    protected MoveHandler moveHandler;

    private Game game;
    private HashSet<Pawn> pawns;
    private int placedPawns;
    private char color;
    private static final int amountOfPawns = 8;


    public Player(char color){
        placedPawns = 0;
        this.color = color;
        pawns = new HashSet<>();
    }

    public void placePawn(String coordinates){
        if(placedPawns < amountOfPawns) {
            Pawn newPawn = new Pawn(color);
            game.placePawn(coordinates, newPawn);
            pawns.add(newPawn);
        }
        placedPawns++;
    }

    public void removePawn(Pawn pawn){
        pawns.remove(pawn);
    }

    public boolean hasMoreToPlace(){
        return placedPawns < amountOfPawns;
    }

    public boolean hasLost(){
        if(game.getState() == GameState.MOVING_PAWNS)
            return !hasMove();
        return false;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public char getColor() {
        return color;
    }

    public void makeMove(){
        moveHandler.makeMove(game, this);
    }

    @Override
    public String toString() {
        return "Current player: " + color;
    }

    public List<Move> getPossibleMoves(){
        ArrayList<Move> moves = new ArrayList<>();
        for(Pawn pawn: pawns){                                                          //TODO GETTING A NULL POINTER HERE
            for(Field field: pawn.getLocation().getAdjacentFields())                    //PROBABLY SOMETHING TO DO WITH PAWN REMOVAL
                if(!field.isTaken()){
                    moves.add(new Move(pawn.getLocation(), field));
                }
        }
        return moves;
    }

    public boolean hasMove(){
        for(Pawn pawn: pawns){
            for(Field field: pawn.getLocation().getAdjacentFields())
                if(!field.isTaken()){
                    return true;
                }
        }
        return false;
    }

    public HashSet<Pawn> getPawns() {
        return pawns;
    }

    public void movePawn(Pawn pawn, String coordinates) {
        game.placePawn(coordinates, pawn);
    }
}
