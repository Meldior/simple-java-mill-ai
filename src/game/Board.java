package game;

import game.Field;
import setup.Initializer;

import java.util.*;

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

    /*
    public void placePawn(String coordinates, game.Pawn pawn){
        game.Field field = fields.get(coordinates);
        if(!field.isTaken())
            field.setPawn(pawn);
    }
     */

/*
    public void movePawn(String coordinates, game.Pawn pawn){
        if(pawn.getLocation() != null){
            pawn.getLocation().setPawn(null);
        }
    }
*/
    public HashMap<String, Field> getFields() {
        return fields;
    }

    public List<Field> getEmptyFields(){
        List<Field> emptyFields = new ArrayList<>();
        for(Map.Entry<String, Field> entry: fields.entrySet()){
            if(!entry.getValue().isTaken())
                emptyFields.add(entry.getValue());
        }
        return emptyFields;
    }
}
