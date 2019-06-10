public abstract class Move {
    protected Field from, to;

    Move(Field from, Field to){
        this.from = from;
        this.to = to;
    }

    abstract void makeMove(Player player);

    abstract void reverseMove(Player player);

    @Override
    public String toString() {
        if(from == null){
            return getClass().getName() + " " + to.toString();
        }
        else if(to == null)
            return getClass().getName() + " " + from.toString();
        else
            return getClass().getName() + " " + from.toString() + " " + to.toString();
    }
}
