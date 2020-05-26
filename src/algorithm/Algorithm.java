package algorithm;

import game.Game;
import heuristic.Heuristic;
import move.Move;
import player.Player;
import profiling.TimeChecker;

public abstract class Algorithm {
    protected Heuristic heuristic;
    protected int algorithm_depth;
    protected Move bestMove;
    public TimeChecker timeChecker;





    public Algorithm(Heuristic heuristic, int algorithm_depth){
        this.heuristic = heuristic;
        this.algorithm_depth = algorithm_depth;
    }

    protected int max(int x, int y){
        return Math.max(x, y);
    }

    protected int min(int x, int y){
        return Math.min(x, y);
    }

    public abstract Move getNextMove(Player maximizingPlayer, Player minimizingPlayer, Game game);

    public Heuristic getHeuristic() {
        return heuristic;
    }

    public int getAlgorithm_depth() {
        return algorithm_depth;
    }
}
