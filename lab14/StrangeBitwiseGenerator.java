import lab14lib.Generator;

/**
 * Created by Hau on 4/28/17.
 */
public class StrangeBitwiseGenerator implements Generator {
    private int period;
    private int state;

    public StrangeBitwiseGenerator(int period) {
        this.period = period;
        state = 0;
    }

    private double normalize(int state) {
        double turn = period / 2;
        return (state - turn) / turn;
    }

    public double next() {
        state++;
        int secondState = state & (state >> 7) % period;
        return normalize(secondState);
    }
}
