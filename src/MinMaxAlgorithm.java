import java.util.ArrayList;
import java.util.List;

public class MinMaxAlgorithm extends Algorithm {

    MinMaxAlgorithm(Heuristic heuristic, int algorithm_depth){
        super(heuristic, algorithm_depth);
    }



    private int minmax(Player maximizingPlayer, Player minimizingPlayer, Game game, int depth, boolean isMaximizing){
        //System.out.println(depth);
        game.checkState();
        if(depth == algorithm_depth){
            return heuristic.evaluate(maximizingPlayer, minimizingPlayer, game);
        }
        if(isMaximizing){
            int value = Integer.MIN_VALUE;
            for(Move move: maximizingPlayer.getPossibleMoves()){
                move.makeMove(maximizingPlayer);
                int maxVal = minmax(maximizingPlayer, minimizingPlayer, game, depth + 1, false);
                if(value < maxVal && depth == 0){
                    bestMove = move;
                }
                value = max(value, maxVal);
                move.reverseMove(maximizingPlayer);
            }
            return value;
        }
        if(!isMaximizing){
            int value = Integer.MAX_VALUE;
            for(Move move: minimizingPlayer.getPossibleMoves()){
                move.makeMove(minimizingPlayer);
                value = min(value, minmax(maximizingPlayer, minimizingPlayer, game, depth + 1, true));
                move.reverseMove(minimizingPlayer);
            }
            return value;
        }
        return 0;
    }


    @Override
    public Move getNextMove(Player maximizingPlayer, Player minimizingPlayer, Game game) {
        bestMove = null;
        minmax(maximizingPlayer, minimizingPlayer, game, 0, true);
        return bestMove;
    }
}
