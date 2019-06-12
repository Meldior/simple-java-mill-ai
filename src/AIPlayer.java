public class AIPlayer extends Player {
    private Algorithm algorithm;

    AIPlayer(char color, Algorithm algorithm){
        super(color);
        this.algorithm = algorithm;
    }


    @Override
    public void makeMove(Player otherPlayer) {
        //long time = System.nanoTime();
        Move move = algorithm.getNextMove(this, otherPlayer, game);
        if(move == null){
            game.checkState();
            if(hasMove()){
                System.out.println(getPossibleMoves().size());
                move = getPossibleMoves().get(0);
            }
        }
        //else
        //    move.makeMove(this);
        System.out.println(move);
        move.makeMove(this);
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }
}
