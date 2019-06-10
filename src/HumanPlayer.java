public class HumanPlayer extends Player {

    private MoveHandler moveHandler = new HumanMoveHandler();

    public HumanPlayer(char player){
        super(player);
        this.moveHandler = new HumanMoveHandler();
    }

    @Override
    public void makeMove(Player player) {
        moveHandler.makeMove(game, this);
    }
}
