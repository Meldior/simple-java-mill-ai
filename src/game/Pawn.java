package game;

public class Pawn {
    private Field currentLocation;
    private char player;
    private static int amountOfPawns = 0;
    private int id;

    public Pawn(char player){
        this.id = ++amountOfPawns;
        this.player = player;
    }

    public Field getLocation(){
        return currentLocation;
    }

    public char getPlayerColor() {
        return player;
    }

    public void setCurrentLocation(Field field){
        if(!field.isTaken()){
            if(currentLocation != null){
                currentLocation.removePawn();
            }
            field.setPawn(this);
            currentLocation = field;
        }
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return player + " " + currentLocation;
    }
}
