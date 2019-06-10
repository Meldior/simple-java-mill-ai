public abstract class Algorithm {
    protected Heuristic heuristic;
    protected int algorithm_depth;
    protected Move bestMove;




    public Algorithm(Heuristic heuristic, int algorithm_depth){
        this.heuristic = heuristic;
        this.algorithm_depth = algorithm_depth;
    }

    protected int max(int x, int y){
        return x > y ? x : y;
    }

    protected int min(int x, int y){
        return x < y ? x : y;
    }

    public abstract Move getNextMove(Player maximizingPlayer, Player minimizingPlayer, Game game);
}