import java.util.HashMap;

public class ConsoleBoardPainter implements BoardPainter {
    private HashMap<String, Field> fields;


    ConsoleBoardPainter(HashMap<String, Field> fields){
        this.fields = fields;
    }

    @Override
    public void paintBoard() {
        StringBuilder sb = new StringBuilder();
        sb.append("7");
        sb.append(getFieldRepresentation("a7"));
        sb.append("------");
        sb.append(getFieldRepresentation("d7"));
        sb.append("------");
        sb.append(getFieldRepresentation("g7"));
        sb.append("\n6| ");
        sb.append(getFieldRepresentation("b6"));
        sb.append("----");
        sb.append(getFieldRepresentation("d6"));
        sb.append("----");
        sb.append(getFieldRepresentation("f6"));
        sb.append(" |\n5");
        sb.append("| |  ");
        sb.append(getFieldRepresentation("c5"));
        sb.append("-");
        sb.append(getFieldRepresentation("d5"));
        sb.append("-");
        sb.append(getFieldRepresentation("e5"));
        sb.append("  | |\n4");
        sb.append(getFieldRepresentation("a4"));
        sb.append("-");
        sb.append(getFieldRepresentation("b4"));
        sb.append("--");
        sb.append(getFieldRepresentation("c4"));
        sb.append("   ");
        sb.append(getFieldRepresentation("e4"));
        sb.append("--");
        sb.append(getFieldRepresentation("f4"));
        sb.append("-");
        sb.append(getFieldRepresentation("g4"));
        sb.append("\n3");
        sb.append("| |  ");
        sb.append(getFieldRepresentation("c3"));
        sb.append("-");
        sb.append(getFieldRepresentation("d3"));
        sb.append("-");
        sb.append(getFieldRepresentation("e3"));
        sb.append("  | |\n2");
        sb.append("| ");
        sb.append(getFieldRepresentation("b2"));
        sb.append("----");
        sb.append(getFieldRepresentation("d2"));
        sb.append("----");
        sb.append(getFieldRepresentation("f2"));
        sb.append(" |\n1");
        sb.append(getFieldRepresentation("a1"));
        sb.append("------");
        sb.append(getFieldRepresentation("d1"));
        sb.append("------");
        sb.append(getFieldRepresentation("g1"));
        sb.append("\n a b  c d e  f g");
        System.out.println(sb.toString());
    }

    private String getFieldRepresentation(String coordinates){
        Field field = fields.get(coordinates);
        if(field.isTaken()){
            return Character.toString(field.getPawn().getPlayerColor());
        }
        else return "E";
    }


}
