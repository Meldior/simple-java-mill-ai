import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Board {
    private HashMap<String, Field> fields;

    Board(){
        fields = Initializer.initFields("./data/fieldData");
    }

    Optional<Field> getField(String coordinates){
        return Optional.ofNullable(fields.get(coordinates));
    }

    List<Field> getAdjacentFields(String coordinates){
        return fields.get(coordinates).getAdjacentFields();
    }

    void placePawn(String coordinates, Pawn pawn){
        Field field = fields.get(coordinates);
        if(!field.isTaken())
            field.setPawn(pawn);
    }

    public HashMap<String, Field> getFields() {
        return fields;
    }
}
