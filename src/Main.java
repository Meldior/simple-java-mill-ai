
public class Main {
    public static void main(String[] args) {
        final int depth = 5;
        Player player1 = new HumanPlayer('w');
        Player player2 = new AIPlayer('w', new MinMaxAlgorithm(new MostBlockedEnemyFieldsHeuristic(), 4));
        Game game = new Game(player2,
               new AIPlayer('b', new AlphaBetaPruningAlgorithm(new HighestPossibleMovesFieldHeuristic(), 6)), 0);
        game.start();
    }
}
