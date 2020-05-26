package player;

import game.Field;
import game.Game;
import game.GameState;
import game.Pawn;
import move.ChangingPositionMove;
import move.DeletingMove;
import move.DeployingMove;
import move.Move;
import movehandler.MoveHandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public abstract class Player {
    protected MoveHandler moveHandler;

    protected Game game;
    private final HashSet<Pawn> pawns;
    private int placedPawns;
    private final char color;
    private boolean threeLeft;

    protected static final int amountOfPawns = 9;
    //protected game.Field lastUsedField;


    public Player(char color){
        placedPawns = 0;
        this.color = color;
        pawns = new HashSet<>();
        threeLeft = false;
    }

    public void placePawn(String coordinates){
        if(placedPawns < amountOfPawns) {
            Pawn newPawn = new Pawn(color);
            game.placePawn(coordinates, newPawn);
            pawns.add(newPawn);
            placedPawns++;
        }
        if(game.getState() == GameState.MOVING_PAWNS && (pawns.size() <= 3)){
            threeLeft = true;
        }
    }

    public void removePawn(Pawn pawn){
        pawns.remove(pawn);
        if(game.getState() == GameState.MOVING_PAWNS && pawns.size() <= 3)
            threeLeft = true;
    }

    public boolean hasMoreToPlace(){
        return placedPawns < amountOfPawns;
    }

    public boolean hasLost(){
        return (game.getState() == GameState.MOVING_PAWNS && pawns.size() <= 2) || !hasMove();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public char getColor() {
        return color;
    }

    public Game getGame() {
        return game;
    }

    @Override
    public String toString() {
        return "Current player: " + color;
    }

    public List<Move> getPossibleMoves(){
        ArrayList<Move> moves = new ArrayList<>();
        if(game.getState() == GameState.DEPLOYING){
            for(Field field: game.getEmptyFields()){
                moves.add(new DeployingMove(field));
            }
        }
        else if(game.getState() == GameState.DELETING){
            for(Pawn pawn: game.getOtherPlayer(this).getPawns()){
                moves.add(new DeletingMove(pawn.getLocation()));
            }
        }
        else if(!threeLeft){
            for (Pawn pawn : pawns) {
                    for (Field field : pawn.getLocation().getAdjacentFields()) {
                        if (!field.isTaken() /*&& field != lastUsedField*/) {
                            moves.add(new ChangingPositionMove(pawn.getLocation(), field));
                        }
                    }
                }
            }
        else {
            for (Pawn pawn : pawns) {
                for (Field field : game.getEmptyFields()) {
                    moves.add(new ChangingPositionMove(pawn.getLocation(), field));
                    //if(field != lastUsedField){
                        //}
                }
            }
        }



        return moves;
    }



    public boolean hasMove(){
        if(game.getState() == GameState.DEPLOYING)
            return true;
        else {
            for(Pawn pawn: pawns){
                for(Field field: pawn.getLocation().getAdjacentFields())
                    if(!field.isTaken() /*&& field != lastUsedField*/){
                        return true;
                    }
            }
        }
        return false;
    }

    public HashSet<Pawn> getPawns() {
        return pawns;
    }


    public void movePawn(String coordinates, Pawn pawn) {
        game.placePawn(coordinates, pawn);
    }

    public void reversePawnPlacing(Pawn pawn){
        if (pawn != null){
            pawns.remove(pawn);
            pawn.getLocation().removePawn();
        }
        placedPawns--;
    }

    public void reversePawnDeletion(String coordinates, Pawn pawn){
        game.placePawn(coordinates, pawn);
        pawns.add(pawn);
        if(pawns.size() > 3)
            threeLeft = false;
    }

    public abstract void makeMove(Player otherPlayer);

}
