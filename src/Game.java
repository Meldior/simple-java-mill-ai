import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Game {
    private Board board;
    private GameState state;
    private Player black, white, currentPlayer;
    public BoardPainter painter;
    private int timeout;
    private int moveCounter;
    private final int MAX_MOVES = 400;

    public Game(Player white, Player black, int timeout){
        state = GameState.DEPLOYING;
        board = new Board();
        this.black = black;
        this.black.setGame(this);
        this.white = white;
        this.white.setGame(this);
        painter = new ConsoleBoardPainter(this.board.getFields());
        currentPlayer = white;
        this.timeout = timeout;
        this.moveCounter = 0;
    }


    public boolean placePawn(String coordinates, Pawn pawn){
        Optional<Field> optionalField = board.getField(coordinates);
        if(!optionalField.isPresent())
            return false;
        else{
            Field field = optionalField.get();
            pawn.setCurrentLocation(field);
            if(currentPlayer instanceof HumanPlayer){
                int mills = getMillsAmount(coordinates);
                if(mills > 0){
                    state = GameState.DELETING;
                    for(int i = 0; i < mills; i++){
                        painter.paintBoard();
                        currentPlayer.makeMove(getOtherPlayer());
                        checkState();
                    }
                }
            }
        }
        return true;
    }

    public void simulatePlacement(String coordinates, Pawn pawn){
        Optional<Field> optionalField = board.getField(coordinates);
        if(optionalField.isPresent()){
            Field field = optionalField.get();
            field.setPawn(pawn);
        }
    }



    public void checkState(){
        if(black.hasLost() || white.hasLost())
            state = GameState.FINISHED;
        else if(/*!white.hasMoreToPlace() && */!black.hasMoreToPlace())
            state = GameState.MOVING_PAWNS;
        else
            state = GameState.DEPLOYING;
    }

    public void start()  {

        System.out.println("starting game!");

        painter.paintBoard();
        while(state != GameState.FINISHED && moveCounter <= MAX_MOVES){
            System.out.println(currentPlayer);
            try{
                Thread.currentThread().sleep(timeout);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            currentPlayer.makeMove(getOtherPlayer());
            changePlayer();
            checkState();
            painter.paintBoard();
            moveCounter++;
        }
        if(white.hasLost()){
            System.out.println("winner: red");
        }
        else
            System.out.println("winner: yellow");
        if(white instanceof AIPlayer){
            ((AIPlayer) white).getAlgorithm().timeChecker.logToFile("./timeData/" + white.getClass().getName()
                    + "-" + ((AIPlayer) white).getAlgorithm().getHeuristic().getClass().getName() + "-" +
                    ((AIPlayer) white).getAlgorithm().getAlgorithm_depth() + ".txt");
        }

        if(black instanceof  AIPlayer){
            ((AIPlayer) black).getAlgorithm().timeChecker.logToFile("./timeData/" + black.getClass().getName()
                    + "-" + ((AIPlayer) black).getAlgorithm().getHeuristic().getClass().getName() + "-"
                    + ((AIPlayer) black).getAlgorithm().getAlgorithm_depth() + ".txt");
        }
        painter.paintBoard();
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

    public int getMillsAmount(String coordinates){
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
            field.removePawn();
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

    public List<Field> getEmptyFields(){
        return board.getEmptyFields();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getOtherPlayer(){
        if(currentPlayer == white)
            return black;
        return white;
    }

    public Player getOtherPlayer(Player player){
        if(player == white)
            return black;
        else
            return white;
    }
}
