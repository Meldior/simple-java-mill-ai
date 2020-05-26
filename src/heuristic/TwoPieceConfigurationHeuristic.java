package heuristic;

import game.Field;
import game.Game;
import game.Pawn;
import player.Player;

public class TwoPieceConfigurationHeuristic implements Heuristic {

    private int countTwoPieceConfigurations(Field field, Player player){
        int cnt = 0;
        for(Field adjacentField: field.getAdjacentFields()){
            if(adjacentField.isTaken() && adjacentField.getPawn().getPlayerColor() == player.getColor()){
                cnt++;
            }
        }
        return cnt;
    }


    @Override
    public int evaluate(Player player, Player otherPlayer, Game game) {
        int numOfTwoPieceConfigurations = 0;
        int numOfOtherPlayersTwoPieceConfigurations = 0;
        for(Pawn pawn: player.getPawns()){
            numOfTwoPieceConfigurations += countTwoPieceConfigurations(pawn.getLocation(), player);
        }
        for(Pawn pawn: otherPlayer.getPawns()){
            numOfOtherPlayersTwoPieceConfigurations += countTwoPieceConfigurations(pawn.getLocation(), otherPlayer);
        }
        return numOfTwoPieceConfigurations - numOfOtherPlayersTwoPieceConfigurations;
    }
}
