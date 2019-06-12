public class MostBlockedEnemyFieldsHeuristic implements Heuristic {
    @Override
    public int evaluate(Player player, Player otherPlayer, Game game) {
        int blockedEnemyFields = 0;
        for(Pawn pawn: otherPlayer.getPawns()){
            for(Field field: pawn.getLocation().getAdjacentFields()){
                if(field.isTaken() && field.getPawn().getPlayerColor() == player.getColor())
                    blockedEnemyFields++;
            }
        }
        return blockedEnemyFields;
    }
}
