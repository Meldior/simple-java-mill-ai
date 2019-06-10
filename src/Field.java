import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Field {
    private String coordinates;
    private List<Field> adjacentFields;
    private Pawn pawn;


    public Field(String coordinates){
        this.coordinates = coordinates;
        adjacentFields = new ArrayList<Field>();
        pawn = null;
    }

    public boolean isTaken(){
        return pawn != null;
    }

    public String getCoordinates(){
        return coordinates;
    }

    public List<Field> getAdjacentFields() {
        return adjacentFields;
    }

    @Override
    public String toString() {
        /*
        StringBuilder sb = new StringBuilder();
        sb.append(coordinates);
        sb.append("\n");
        adjacentFields.stream()
                .forEach(field ->
                        sb.append(field.getCoordinates() + ";"));
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
        */

        return coordinates;
    }

    @Override
    public boolean equals(Object o) {
        Field f = (Field)o;
        return coordinates.equals(f.getCoordinates());
    }

    public void addAdjacentField(Field field){
        if(!adjacentFields.contains(field))
                adjacentFields.add(field);
    }

    public Pawn getPawn() {
        return pawn;
    }

    public void setPawn(Pawn pawn){
        this.pawn = pawn;
    }

    public void removePawn(){
        this.pawn = null;
    }

    public Optional<Field> getTopField(){
        return adjacentFields.stream()
                .filter(f -> f.getCoordinates().charAt(1) > coordinates.charAt(1))
                .findFirst();
    }


    public Optional<Field> getBottomField(){
        return adjacentFields.stream()
                .filter(f -> f.getCoordinates().charAt(1) < coordinates.charAt(1))
                .findFirst();
    }

    public Optional<Field> getLeftField(){
        return adjacentFields.stream()
                .filter(f -> f.getCoordinates().charAt(0) < coordinates.charAt(0))
                .findFirst();
    }

    public Optional<Field> getRightField(){
        return adjacentFields.stream()
                .filter(f -> f.getCoordinates().charAt(0) > coordinates.charAt(0))
                .findFirst();
    }


}
