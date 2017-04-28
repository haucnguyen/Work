import lab14lib.Generator;

/**
 * Created by Hau on 4/28/17.
 */
public class SawToothGenerator implements Generator{
    private int period;
    private int state;

    public SawToothGenerator(int period) {
        this.period = period;
        state = 0;
    }

    public double normalize(int state) {
        double turn = period / 2;
        return (state - turn) / turn;
    }

    public double next() {
        state++;
        state = state % (period - 1);
        return normalize(state);
    }

}
