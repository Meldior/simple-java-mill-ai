public class AIPlayer extends Player {
    Algorithm algorithm;

    AIPlayer(char color, Algorithm algorithm){
        super(color);
        this.algorithm = algorithm;
    }


    @Override
    public void makeMove(Player otherPlayer) {
        //long time = System.nanoTime();
        Move move = algorithm.getNextMove(this, otherPlayer, game);
        if(move == null)
            game.checkState();
        System.out.println(move);
        move.makeMove(this);
    }
}
