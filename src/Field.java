import java.util.ArrayList;
import java.util.List;

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
        StringBuilder sb = new StringBuilder();
        sb.append(coordinates);
        sb.append("\n");
        adjacentFields.stream()
                .forEach(field ->
                        sb.append(field.getCoordinates() + ";"));
        sb.append("\n");
        return sb.toString();
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
}
