
public class AlphaBetaPruningAlgorithm extends Algorithm {

    AlphaBetaPruningAlgorithm(Heuristic heuristic, int algorithm_depth){
        super(heuristic, algorithm_depth);
    }


    private int alphaBeta(Player maximizingPlayer, Player minimizingPlayer, Game game, int depth, boolean isMaximizing, int alpha, int beta){
        game.checkState();
        if(depth == algorithm_depth){
            return heuristic.evaluate(maximizingPlayer, minimizingPlayer, game);
        }
        if(isMaximizing){
            int value = Integer.MIN_VALUE;
            for(Move move: maximizingPlayer.getPossibleMoves()){
                move.makeMove(maximizingPlayer);
                int maxVal = alphaBeta(maximizingPlayer, minimizingPlayer, game, depth + 1, false, alpha, beta);
                if(value < maxVal && depth == 0){
                    bestMove = move;
                }
                value = max(value, maxVal);
                alpha = max(value, alpha);
                move.reverseMove(maximizingPlayer);
                if(alpha >= beta)
                    break;
            }
            return value;
        }
        if(!isMaximizing){
            int value = Integer.MAX_VALUE;
            for(Move move: minimizingPlayer.getPossibleMoves()){
                move.makeMove(minimizingPlayer);
                value = min(value, alphaBeta(maximizingPlayer, minimizingPlayer, game, depth + 1, true, alpha, beta));
                beta = min(value, beta);
                move.reverseMove(minimizingPlayer);
                if(alpha >= beta)
                    break;
            }
            return value;
        }
        return 0;
    }

    @Override
    public Move getNextMove(Player maximizingPlayer, Player minimizingPlayer, Game game) {
        bestMove = null;
        alphaBeta(maximizingPlayer, minimizingPlayer, game, 0, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return bestMove;
    }

}
