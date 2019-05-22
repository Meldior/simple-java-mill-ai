public class Pawn {
    private Field currentLocation;
    private char player;


    public Pawn(Field currentLocation, char player){
        this.currentLocation = currentLocation;
        this.player = player;
    }

    public Field getLocation(){
        return currentLocation;
    }

    public char getPlayer() {
        return player;
    }
}
