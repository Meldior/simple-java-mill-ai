public class HumanPlayer extends Player {

    public HumanPlayer(char player){
        super(player);
        this.moveHandler = new HumanMoveHandler();
    }

}
