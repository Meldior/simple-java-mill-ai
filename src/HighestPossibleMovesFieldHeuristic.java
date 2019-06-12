public class HighestPossibleMovesFieldHeuristic implements Heuristic {
    @Override
    public int evaluate(Player player, Player otherPlayer, Game game) {
        int possibleFields = 0;
        int otherPlayerPossibleFields = 0;
        for(Pawn pawn: player.getPawns()){
            for(Field field: pawn.getLocation().getAdjacentFields()){
                if(!field.isTaken()){
                    possibleFields++;
                }
            }
        }
        for(Pawn pawn: otherPlayer.getPawns()){
            for(Field field: pawn.getLocation().getAdjacentFields()){
                if(!field.isTaken()){
                    otherPlayerPossibleFields++;
                }
            }
        }
        return possibleFields - otherPlayerPossibleFields;
    }
}
