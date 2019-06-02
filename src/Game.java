import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Game {
    private Board board;
    private GameState state;
    private Player black, white, currentPlayer;
    private BoardPainter painter;

    public Game(Player white, Player black){
        state = GameState.DEPLOYING;
        board = new Board();
        this.black = black;
        this.black.setGame(this);
        this.white = white;
        this.white.setGame(this);
        painter = new ConsoleBoardPainter(this.board.getFields());
        currentPlayer = white;
    }


    public void placePawn(String coordinates, Pawn pawn){
        board.placePawn(coordinates, pawn);
        int mills = getMillsAmount(coordinates);
        System.out.println(mills);

        if(mills > 0){
            state = GameState.DELETING;
            for(int i = 0; i < mills; i++){
                painter.paintBoard();
                currentPlayer.makeMove();
            }


        }
    }

    private void checkState(){
        if(black.hasLost() || white.hasLost())
            state = GameState.FINISHED;
        else if(!white.hasMoreToPlace() && !black.hasMoreToPlace())
            state = GameState.MOVING_PAWNS;
        else if(!white.hasMoreToPlace() && (white.getPawns().size() <= 3 || black.getPawns().size() <= 3))
            state = GameState.THREE_LEFT;
        else
            state = GameState.DEPLOYING;
    }

    public void start(){
        System.out.println("starting game!");
        while(state != GameState.FINISHED){
            painter.paintBoard();
            System.out.println(currentPlayer);
            currentPlayer.makeMove();
            changePlayer();
            checkState();
        }
    }

    private void changePlayer(){
        if(currentPlayer == white)
            currentPlayer = black;
        else currentPlayer = white;
    }

    private boolean hasMill(Field left, Field middle, Field right){
        if(left.isTaken() && middle.isTaken() && right.isTaken()) {
            return left.getPawn().getPlayerColor() == middle.getPawn().getPlayerColor()
                    && middle.getPawn().getPlayerColor() == right.getPawn().getPlayerColor();
        }

        return false;
    }

    private int getMillsAmount(String coordinates){
        Field currentField = board.getField(coordinates).get();
        Optional<Field> topField = currentField.getTopField();
        Optional<Field> leftField = currentField.getLeftField();
        Optional<Field> rightField = currentField.getRightField();
        Optional<Field> bottomField = currentField.getBottomField();
        int mills = 0;
        if(topField.isPresent()){
            if(bottomField.isPresent()){
                if(hasMill(topField.get(), currentField, bottomField.get()))       {
                    mills++;                                                                    // field in the middle
                }
                                                                                                // *vertical*
            }
            else
                if(hasMill(currentField, topField.get(), topField.get().getTopField().get())){
                    mills++;                                                                    // field on the bottom
                }

        }
        else if(bottomField.isPresent()){                                                       // field on the top
            if(hasMill(currentField, bottomField.get(), bottomField.get().getBottomField().get())) {
                mills++;
            }
        }
        if(leftField.isPresent()){
            if(rightField.isPresent()){
                if(hasMill(leftField.get(), currentField, rightField.get())){
                    mills++;                                                                    // field in the middle
                }
                                                                                                // horizontal
            }
            else
                if(hasMill(currentField, leftField.get(), leftField.get().getLeftField().get())){
                    mills++;                                                                    //field on the right
                }
        }

        else if(rightField.isPresent()){
            if(hasMill(currentField, rightField.get(), rightField.get().getRightField().get())){//field on the left
                mills++;
            }
        }
        return mills;
    }

    public void removePawn(String coordinates){
        Optional<Field> optionalField = board.getField(coordinates);
        if(optionalField.isPresent()){
            Field field = optionalField.get();
            Pawn pawn = field.getPawn();
            field.setPawn(null);
            if(currentPlayer == white)
                black.removePawn(pawn);
            else
                white.removePawn(pawn);
        }
       else{
            System.out.println("something went wrong, deletion");
        }
    }

    public GameState getState() {
        return state;
    }

    public Optional<Field> getField(String coordinates){
        return board.getField(coordinates);
    }
}
