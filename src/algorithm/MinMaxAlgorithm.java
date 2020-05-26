package algorithm;

import game.Game;
import heuristic.Heuristic;
import move.Move;
import player.Player;
import profiling.TimeChecker;

public class MinMaxAlgorithm extends Algorithm {

    MinMaxAlgorithm(Heuristic heuristic, int algorithm_depth){
        super(heuristic, algorithm_depth);
        this.timeChecker = new TimeChecker("MinMax");
    }



    private int minmax(Player maximizingPlayer, Player minimizingPlayer, Game game, int depth, boolean isMaximizing){

        //System.out.println(depth);
        timeChecker.tick();
        game.checkState();
        if(depth == algorithm_depth){
            return heuristic.evaluate(maximizingPlayer, minimizingPlayer, game);
        }
        if (isMaximizing){
            int value = Integer.MIN_VALUE;
            for(Move move: maximizingPlayer.getPossibleMoves()){
                move.makeMove(maximizingPlayer);
                int maxVal = minmax(maximizingPlayer, minimizingPlayer, game, depth + 1, false);
                if(value < maxVal && depth == 0){
                    bestMove = move;
                    //System.out.println("changed: " + value + " " + maxVal);
                }
                value = max(value, maxVal);
                move.reverseMove(maximizingPlayer);

            }
            return value;
        }
        else {
            int value = Integer.MAX_VALUE;
            for(Move move: minimizingPlayer.getPossibleMoves()){
                move.makeMove(minimizingPlayer);
                value = min(value, minmax(maximizingPlayer, minimizingPlayer, game, depth + 1, true));
                move.reverseMove(minimizingPlayer);
            }
            return value;
        }
    }


    @Override
    public Move getNextMove(Player maximizingPlayer, Player minimizingPlayer, Game game) {
        bestMove = null;
        timeChecker.clear();
        timeChecker.start();
        minmax(maximizingPlayer, minimizingPlayer, game, 0, true);
        timeChecker.end();
        return bestMove;
    }
}
