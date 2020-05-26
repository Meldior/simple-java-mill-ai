import algorithm.AlphaBetaPruningAlgorithm;
import game.Game;
import heuristic.HighestPossibleMovesFieldHeuristic;
import player.AIPlayer;
import player.HumanPlayer;
import player.Player;

public class Main {
    public static void main(String[] args) {
        final int depth = 5;
        Player player1 = new HumanPlayer('w');
        Game game = new Game(player1,
               new AIPlayer('b', new AlphaBetaPruningAlgorithm(new HighestPossibleMovesFieldHeuristic(), 6)), 0);
        game.start();
    }
}
