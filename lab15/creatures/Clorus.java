package creatures;

import huglife.*;

import java.awt.Color;
import java.util.Map;
import java.util.List;


/**
 * Created by Hau on 5/3/17.
 */
public class Clorus extends Creature {
    private int r;
    private int g;
    private int b;

    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;


    }

    public Clorus() {
        this(1);

    }

    public Color color() {
        return color(r, g, b);
    }


    public void move() {
        energy -= 0.03;
    }


    public void stay() {
        energy -= 0.01;
    }


    public void attack(Creature c) {
        energy += c.energy();
    }


    public Creature replicate() {
        energy /= 2;
        return new Clorus(energy);
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empts = getNeighborsOfType(neighbors, "empty");
        List<Direction> plips = getNeighborsOfType(neighbors, "plips");

        if (empts.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }
        Direction attDir = HugLifeUtils.randomEntry(plips);
        if (plips.size() >= 1) {
            return new Action(Action.ActionType.ATTACK, attDir);
        }

        Direction direction = HugLifeUtils.randomEntry(empts);
        if (energy >= 1) {
            return new Action(Action.ActionType.REPLICATE, direction);
        }

        return new Action(Action.ActionType.MOVE, direction);
    }
}
