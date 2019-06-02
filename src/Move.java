public class Move {
    private Field from, to;

    Move(Field from, Field to){
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return from.toString() + " -> " + to.toString();
    }

    public Field getFrom() {
        return from;
    }

    public Field getTo() {
        return to;
    }
}
