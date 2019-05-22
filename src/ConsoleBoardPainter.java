import java.util.HashMap;
import java.util.List;

public class ConsoleBoardPainter implements BoardPainter {
    HashMap<String, String> fields = new HashMap<>();

    @Override
    public void paintBoard() {
        StringBuilder sb = new StringBuilder();
        sb.append(fields.get("a7").getCoordinates());
        sb.append("--");
        sb.append(fields.get("d7").getCoordinates());
        sb.append("--");
        sb.append(fields.get("g7").getCoordinates());
        System.out.println(sb.toString());
    }

    public void initBoard(List<Field> fieldData) {
        for(Field f: fieldData){
            fields.put(f.getCoordinates(), f.getPawn().ge);
        }
    }
}
