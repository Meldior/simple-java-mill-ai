
public class Main {
    public static void main(String[] args) {
        Game game = new Game(new AIPlayer('w', new MinMaxAlgorithm(new TwoPieceConfigurationHeuristic(), 5)),
               new AIPlayer('b', new AlphaBetaPruningAlgorithm(new TwoPieceConfigurationHeuristic(), 7)), 2000);
        //Game game = new Game(new HumanPlayer('w'), new HumanPlayer('b'), 0);
        game.start();
    }
}
