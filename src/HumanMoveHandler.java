import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class HumanMoveHandler implements MoveHandler {
    private Scanner sc = new Scanner(System.in);


    @Override
    public void makeMove(Game game, Player player) {
        System.out.println(game.getState());
        if(game.getState() == GameState.DEPLOYING){
            System.out.println("Please select the field to put the pawn on");
            String coordinates = sc.nextLine();
            Optional<Field> fieldOptional = game.getField(coordinates);
            while(!fieldOptional.isPresent() || fieldOptional.get().isTaken()){
                System.out.println("Wrong field, try again");
                coordinates = sc.nextLine();
                fieldOptional = game.getField(coordinates);
            }
            player.placePawn(coordinates);
        }
        else if(game.getState() == GameState.DELETING){
            System.out.println("Please remove a pawn of your enemy");
            String coordinates = sc.nextLine();
            Optional<Field> fieldOptional = game.getField(coordinates);
            while(!fieldOptional.isPresent() || !fieldOptional.get().isTaken() ||
                    fieldOptional.get().getPawn().getPlayerColor()  == player.getColor()){
                System.out.println("Wrong field, try again");
                coordinates = sc.nextLine();
                fieldOptional = game.getField(coordinates);
            }
            game.removePawn(coordinates);
        }

        else {
            System.out.println("Select the pawn that you wish to move");
            List<Move> possibleMoves;
            possibleMoves = player.getPossibleMoves();
            ArrayList<Field> possibleFields = new ArrayList<>();
            Pawn pawn = null;
            boolean isValidPawn = false;
            while(!isValidPawn){
                String coordinates = sc.nextLine();
                for(Move move: possibleMoves){
                    if(move.getFrom().getCoordinates().equals(coordinates)){
                        isValidPawn = true;
                        pawn = move.getFrom().getPawn();
                        possibleFields.add(move.getTo());
                    }
                }
                if(!isValidPawn)
                    System.out.println("Wrong field, try again");
            }
            System.out.println("Pick a place to move the pawn to");
            String coordinates;
            boolean isValidField = false;
            while(!isValidField){
                coordinates = sc.nextLine();
                for(Field field: possibleFields){
                    if(field.getCoordinates().equals(coordinates)){
                        isValidField = true;
                        player.movePawn(field.getCoordinates(), pawn);
                        break;
                    }
                }
                if(!isValidField)
                    System.out.println("Wrong field, try again");
            }
        }

    }
}




 /*
        else if(game.getState() == GameState.MOVING_PAWNS){
            System.out.println("Select the pawn that you wish to move");
            List<Move> possibleMoves = player.getPossibleMoves();
            ArrayList<Field> possibleFields = new ArrayList<>();
            Pawn pawn = null;
            boolean isValidPawn = false;
            while(!isValidPawn){
                String coordinates = sc.nextLine();
                for(Move move: possibleMoves){
                    if(move.getFrom().getCoordinates().equals(coordinates)){
                        isValidPawn = true;
                        pawn = move.getFrom().getPawn();
                        possibleFields.add(move.getTo());
                    }
                }
                if(!isValidPawn)
                    System.out.println("Wrong field, try again");
            }
            System.out.println("Pick a place to move the pawn to");
            String coordinates;
            boolean isValidField = false;
            while(!isValidField){
                coordinates = sc.nextLine();
                for(Field field: possibleFields){
                    if(field.getCoordinates().equals(coordinates)){
                        isValidField = true;
                        player.movePawn(pawn, field.getCoordinates());
                        break;
                    }
                }
                if(!isValidField)
                    System.out.println("Wrong field, try again");
            }
        }

        else if(game.getState() == GameState.THREE_LEFT){
            System.out.println("Select the pawn that you wish to move");
            List<Move> possibleMoves = player.getPossibleMoves();
            ArrayList<Field> possibleFields = new ArrayList<>();
            Pawn pawn = null;
            boolean isValidPawn = false;
            while(!isValidPawn){
                String coordinates = sc.nextLine();
                for(Move move: possibleMoves){
                    if(move.getFrom().getCoordinates().equals(coordinates)){
                        isValidPawn = true;
                        pawn = move.getFrom().getPawn();
                        possibleFields.add(move.getTo());
                    }
                }
                if(!isValidPawn)
                    System.out.println("Wrong field, try again");
            }
        }

         */