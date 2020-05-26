package player;

import algorithm.Algorithm;
import move.Move;

public class AIPlayer extends Player {
    private final Algorithm algorithm;

    public AIPlayer(char color, Algorithm algorithm){
        super(color);
        this.algorithm = algorithm;
    }


    @Override
    public void makeMove(Player otherPlayer) {
        Move move = algorithm.getNextMove(this, otherPlayer, game);
        if(move == null){
            game.checkState();
        }
        else
            move.makeMove(this);
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }
}
