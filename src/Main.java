import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Field> fields = Initializer.initFields();
        ConsoleBoardPainter consoleBoardPainter = new ConsoleBoardPainter();
        consoleBoardPainter.initBoard(fields);
        consoleBoardPainter.paintBoard();
    }
}
