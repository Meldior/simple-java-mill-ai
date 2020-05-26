package move;

import game.Field;
import player.Player;

public abstract class Move {
    protected Field from, to;

    Move(Field from, Field to){
        this.from = from;
        this.to = to;
    }

    abstract public void makeMove(Player player);

    abstract public void reverseMove(Player player);

    public Field getTo() {
        return to;
    }

    public Field getFrom() {
        return from;
    }

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
