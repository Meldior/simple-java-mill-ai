package setup;

import game.Field;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Initializer {
    public static HashMap<String, Field> initFields(String filename) {
        List<Field> createdFields = new ArrayList<>(24);
        try {
            Scanner sc = new Scanner(new FileReader(filename));
            while(sc.hasNext()){
                String coordinates = sc.nextLine();
                if(coordinates.equals("END"))
                    break;
                Field field = getField(createdFields, coordinates);
                String[] adjacentCoordinates = sc.nextLine().split(";");
                Arrays.stream(adjacentCoordinates)
                        .forEach(s -> {
                            Field adjacentField = getField(createdFields, s);
                            field.addAdjacentField(adjacentField);
                        });
                sc.nextLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<String, Field> fieldmap = new HashMap<>();
        createdFields.stream().forEach(
                field -> fieldmap.put(field.getCoordinates(), field)
        );
        return fieldmap;
    }

    private static Field getField(List<Field> createdFields, String coordinates){
        for(Field f: createdFields){
            if(f.getCoordinates().equals(coordinates))
                return f;
        }
        Field f = new Field(coordinates);
        createdFields.add(f);
        return f;
    }
}
